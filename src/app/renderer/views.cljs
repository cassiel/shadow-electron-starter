(ns app.renderer.views
  (:require [re-frame.core :as re-frame]
            [app.renderer.subs :as subs]
            [app.renderer.events :as events]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        _count (re-frame/subscribe [::subs/count])]
    [:center
     [:div
      [:h2 "We have awesome glowing text!"]
      [:div.logos
       [:img.electron {:src "img/electron-logo.png"}]
       [:img.cljs {:src "img/cljs-logo.svg"}]
       [:img.reagent {:src "img/reagent-logo.png"}]]
      [:p]
      [:button
       {:on-click #(re-frame/dispatch [::events/increment])}
       (str "Clicked " @_count " times")]]]))
