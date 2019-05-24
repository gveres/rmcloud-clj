(ns rmcloud-clj.client-state
  (:require [mount.core :refer [defstate]]
            [clojure.edn :as edn]
            [clojure.tools.logging :refer [info]]
            [cprop.core :refer [load-config]]
            [cprop.source :refer [from-env]]))

(defn get-token-from-dotfile []
 (let [config (load-config)] :merge [(from-env)]
  (subs
    (->>
      (slurp (str (:home config) "/.rmapi"))
      (re-seq #"^devicetoken:.*")
      first)
    13)))

(defstate api-context
  :start (hash-map :device-token (get-token-from-dotfile)))

