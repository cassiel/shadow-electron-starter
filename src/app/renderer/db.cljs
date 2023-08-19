(ns app.renderer.db)

(def default-db
  {:name "re-frame",
   :versions {:node ((.-versions.node  js/window))}
   :count 0})
