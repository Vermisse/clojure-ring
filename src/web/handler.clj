(ns web.handler
  (:use [compojure.core :only [GET POST PUT DELETE defroutes]]
        [compojure.route :only [not-found]]
        korma.db
        korma.core
        [ring.util.response :only [response redirect]]
        [selmer.parser :as parser]
        [jdbc.info :as info]))

;关闭模版缓存
(parser/cache-off!)

;设置模板路径
(parser/set-resource-path! (clojure.java.io/resource "templates"))

;路由函数
(defroutes routes
  (GET "/" request
       (response {:template "index.html"
                  :model {:title "Clojure Demo"
                          :name "Clojure框架Demo"
                          :info (get-info)}}))
  
  (POST "/" [](fn [request]
                (let [params (:params request)]
                  (create-info {:id (str (System/currentTimeMillis))
                                :info_key (:key params)
                                :info_value (:value params)})
                  (redirect "/"))))
  
  (GET "/:id"[id]
          (do
            (delete-info id)
            (redirect "/")))

  (not-found "<h1>page not found!</h1>"))