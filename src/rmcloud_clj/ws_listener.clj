(ns rmcloud-clj.ws-listener
  (:require [rmcloud-clj.client-state :refer [api-context]]
            [gniazdo.core :as ws]
            [clojure.data.json :as json]))

(defn- process-message [on-receive message]
  (let [edned (json/read-str message :key-fn keyword)]
    (on-receive edned)))

(defn subscribe-notifications [on-receive]
 (let [ws-url (rmcloud-clj.core/service-discovery "notifications" 1)]
   (ws/connect
    "wss://5zt5-notifications-production.cloud.remarkable.engineering/notifications/ws/json/1"
    :headers {"Authorization" (str "Bearer " (rmcloud-clj.core/refresh-token (rmcloud-clj.client-state/get-token-from-dotfile)))}
    :on-receive #(process-message on-receive %))))

(defn unsubscribe-notifications [notif-channel]
  (ws/close notif-channel))
