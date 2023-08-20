(ns app.renderer.date-handler
  (:require [com.stuartsierra.component :as component]
            [net.cassiel.lifecycle :refer [starting stopping]]
            [re-frame.core :as re-frame]
            [app.renderer.events :as events]))

(defrecord DATE_HANDLER [installed?]
  Object
  (toString [this] "DATE_HANDLER")

  component/Lifecycle
  (start [this]
    (starting this
              :on installed?
              :action #(letfn [(callback [event value]
                                 (do (js/console.log value)
                                     (re-frame/dispatch [::events/date (fnext value)])))]
                         (.handleDate js/window.api callback)
                         (assoc this
                                :callback callback
                                :installed? true))))

  (stop [this]
    (stopping this
              :on installed?
              :action #(do
                         (.removeHandleDate js/window.api (:callback this))
                         (assoc this
                                :callback nil
                                :installed? false)))))
