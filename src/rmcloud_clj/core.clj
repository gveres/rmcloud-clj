(ns rmcloud-clj.core
  (:require [clj-http.client :as client]
            [clojure.tools.logging :as log]))

;;auth-api

(defn get-rm-token
  [code]
  (->>
    (client/post
     "https://my.remarkable.com/token/json/2/device/new"
     {:form-params
      {:code code
       :deviceDesc "desktop-windows"
       :deviceID (.toString (java.util.UUID/randomUUID))}
      :content-type :json})
    (:body)))

(defn refresh-token
  [token]
  (->> (client/post
        "https://my.remarkable.com/token/json/2/user/new"
        {:oauth-token token})
       (:body)))

(defn service-discovery
  [service api-version]
  (client/get
    (str "https://service-manager-production-dot-remarkable-production.appspot.com/service/json/1/" service)
    {:query-params
     {
      :environment "production"
      :group "auth0|5a68dc51cb30df3877a1d7c4"
      :apiVer api-version}
     :as :json}))

;; document storage
(defn list-items
  ([token doc-id]
   (let [document-storage-endpoint (->> (service-discovery "document-storage" 2) :body :Host)]
    (client/get
      (str "https://" document-storage-endpoint "/document-storage/json/2/docs")
      {:oauth-token token
       :as :json
       :query-params {:doc doc-id
                      :withBlob true}})))
  ([token]
   (list-items token nil)))

(defn retrieve-put-url
  [token version doc-id]
  (let [document-storage-endpoint (->> (service-discovery "document-storage" 2) :body :Host)]
   (client/put
     (str "https://" document-storage-endpoint "/document-storage/json/2/upload/request")
     {:oauth-token token
      :content-type :json
      :as :json
      :save-request? true
      :form-params [{:ID doc-id
                     :Version version}]})))

(comment TODOS)
;;  TODO: read from .rmapi if exists?
;;  TODO: add error handling

