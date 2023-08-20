(ns app.main.core
  (:require ["electron" :refer [app BrowserWindow ipcMain crashReporter]]
            ["path" :as path]
            [cljs.core.async :as async :refer [<! >! go go-loop]]
            [cljs.core.async.interop :refer-macros [<p!]]))

(enable-console-print!)
(set! *warn-on-infer* false)

(def main-window (atom nil))

(defn- init-browser []
  (js/console.log "init-browser")
  (reset! main-window (BrowserWindow.
                       (clj->js {:width 800
                                 :height 600
                                 :webPreferences {:preload (.join path js/__dirname "preload.js")}})))
  ; Path is relative to the compiled js file (main.js in our case)
  (.loadURL ^js/electron.BrowserWindow @main-window (str "file://" js/__dirname "/public/index.html"))
  (.on ^js/electron.BrowserWindow @main-window "closed" #(reset! main-window nil)))

(defn- start-date-process []
  ;; TODO: proper tear-down of this on hot reload.
  (go-loop [n 0]
    (when-let [m @main-window]
      ;; Can only send JS-native data packets:
      (.send (.-webContents m) "date" #js [n (str (js/Date.))]))

    (<! (async/timeout 1000))
    (recur (inc n))))


(defn payload []
  (clj->js (conj (range 10) "pong"))  )

(defn main []
  ; CrashReporter can just be omitted
  (.start crashReporter
          (clj->js
           {:companyName "MyAwesomeCompany"
            :productName "MyAwesomeApp"
            :submitURL "https://example.com/submit-url"
            :autoSubmit false}))

  (.on app "window-all-closed" #(when-not (= js/process.platform "darwin")
                                  (.quit app)))
  (.on app "ready" #(do
                      (init-browser)
                      (start-date-process)
                      ;; Can't pass CLJS-native structures.
                      ;; Make payload dynamic (hot-reload):
                      (.handle ipcMain "ping" (fn [] (payload))))))
