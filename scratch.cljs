(ns user
  (:require [cljs.core.async :as async :refer [<! >! go]]
            [cljs.core.async.interop :refer-macros [<p!]]))

;; Testing IPC.

(.-versions.ping js/window)

js/window.versions

(go
  (let [x (<p! (.ping js/window.api))]
    (js/console.log x)))

(str (js/Date.))
