(ns app.renderer.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::versions
 (fn [db]
   (:versions db)))

(re-frame/reg-sub
 ::count
 (fn [db]
   (:count db)))
