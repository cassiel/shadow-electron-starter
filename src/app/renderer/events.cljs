(ns app.renderer.events
  (:require
   [re-frame.core :as re-frame]
   [app.renderer.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::increment
 (fn [db _]
   (update db :count inc)))
