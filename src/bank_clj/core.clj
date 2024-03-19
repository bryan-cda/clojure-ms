(ns bank-clj.core
  (:require [io.pedestal.http.route :as route]
    [io.pedestal.http :as http]))

(defn index [request]
  {:status 200 :body (str "welcome " (get-in request [:query-params :name] "world!"))})

(def routes (route/expand-routes #{["/index" :get index :route-name :index]}))

(def server-config {::http/routes routes
                    ::http/port 9999
                    ::http/type :jetty
                    ::http/join? false})

(http/start (http/create-server server-config))
