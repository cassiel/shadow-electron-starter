(ns app.renderer.core
  (:require [reagent.dom :as rdom]
            [re-frame.core :as re-frame]
            [app.renderer.events :as events]
            [app.renderer.views :as views]
            [app.renderer.config :as config]))

(enable-console-print!)

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app-container")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn start! []
  ;; I guess since this isn't within ^:dev/after-load so DB changes/edits aren't refreshed.
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
