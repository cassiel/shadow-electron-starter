(ns app.main.core
  (:require ["electron" :refer [app BrowserWindow ipcMain crashReporter]]
            ["path" :as path]))

(enable-console-print!)

(def main-window (atom nil))

(defn init-browser []
  (js/console.log "init-browser")
  (reset! main-window (BrowserWindow.
                       (clj->js {:width 800
                                 :height 600
                                 :webPreferences {:preload (.join path js/__dirname "preload.js")
                                                  :nodeIntegration true}})))
  ; Path is relative to the compiled js file (main.js in our case)
  (.loadURL ^js/electron.BrowserWindow @main-window (str "file://" js/__dirname "/public/index.html"))
  (.on ^js/electron.BrowserWindow @main-window "closed" #(reset! main-window nil)))

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
                      (.handle ipcMain "ping" (fn [] "pong")))))
