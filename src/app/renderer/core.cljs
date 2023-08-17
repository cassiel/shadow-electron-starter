(ns app.renderer.core
  (:require [com.stuartsierra.component :as component]
            [net.cassiel.lifecycle :refer [starting stopping]]
            [reagent.dom :as rdom]
            [re-frame.core :as re-frame]
            [app.renderer.events :as events]
            [app.renderer.views :as views]
            [app.renderer.config :as config]
            [app.renderer.dummy :as dummy]))

(enable-console-print!)

;; Token instance of a Sierra Component map - not actually connected to anything.

(defonce S (atom (component/system-map :dummy (dummy/map->DUMMY {}))))

;; These two top-levelled so we can call them from the JS console:

(defn start []
  (swap! S component/start))

(defn stop []
  (swap! S component/stop))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/before-load teardowm []
  (stop))

(defn ^:dev/after-load start-and-mount-root []
  (re-frame/clear-subscription-cache!)
  (start)
  (let [root-el (.getElementById js/document "app-container")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn start! []
  ;; I guess since this isn't within ^:dev/after-load so DB changes/edits aren't refreshed.
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (start-and-mount-root))
