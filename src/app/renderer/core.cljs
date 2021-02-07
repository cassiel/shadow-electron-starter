(ns app.renderer.core
  (:require [reagent.core :refer [atom]]
            [reagent.dom :as rd]))

(enable-console-print!)

(defonce state (atom 0))

(defn root-component []
  [:center 
  [:div
   [:h2 "We have awesome glowing text!"]
   [:div.logos
    [:img.electron {:src "img/electron-logo.png"}]
    [:img.cljs {:src "img/cljs-logo.svg"}]
    [:img.reagent {:src "img/reagent-logo.png"}]]
   [:p]
   [:button
    {:on-click #(swap! state inc)}
    (str "Clicked " @state " times")]]])

(defn ^:dev/after-load start! []
  (rd/render
   [root-component]
   (js/document.getElementById "app-container")))
