(ns app.renderer.views
  (:require [re-frame.core :as re-frame]
            [app.renderer.subs :as subs]
            [app.renderer.events :as events]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        versions (re-frame/subscribe [::subs/versions])
        date (re-frame/subscribe [::subs/date])
        _count (re-frame/subscribe [::subs/count])]
    [:center
     [:div
      [:h2 (str "Hello " @name)]
      [:h2 (str "Node " (:node @versions))]
      [:h2 (fnext @date)]

      [:div.logos
       [:img.electron {:src "img/electron-logo.png"}]
       [:img.cljs {:src "img/cljs-logo.svg"}]
       [:img.reagent {:src "img/reagent-logo.png"}]]

      [:p]
      [:button
       {:on-click #(re-frame/dispatch [::events/increment])}
       (str "Clicked " @_count " times")]]]))
