(ns web.main
  (:require
    [ring.adapter.jetty :as jetty]
    [ring.middleware.cookies :as cookies]
    [ring.middleware.params :as params]
    [ring.middleware.keyword-params :as keyword-params]
    [ring.middleware.json :as json]
    [ring.middleware.file :as file]
    [ring.middleware.resource :as resource]
    [ring.middleware.stacktrace :as stacktrace]
    [web.template :as template]
    [web.handler :as handler])
  (:gen-class))

;入口，启动Jetty服务器
(defn -main[& args]
  (jetty/run-jetty
    (->
      handler/routes
      (resource/wrap-resource (clojure.java.io/resource "resources"))
      template/wrap-template-response
      json/wrap-json-response
      json/wrap-json-body
      stacktrace/wrap-stacktrace-web
      keyword-params/wrap-keyword-params
      cookies/wrap-cookies
      params/wrap-params)
    {:host "localhost"
     :port 9000}))