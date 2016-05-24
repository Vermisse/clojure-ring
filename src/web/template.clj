(ns web.template
  (:use ring.util.response
        [selmer.parser :as parser]))

;关闭模版缓存
(parser/cache-off!)

;设置模板路径
(parser/set-resource-path! (clojure.java.io/resource "templates"))

(defn- try-render [response]
  (let [body (:body response)]
    (if (map? body)
      (let [[model template] [(:model body) (:template body)]]
        (if (and (map? model) (string? template))
          (parser/render-file template model))))))

(defn wrap-template-response
  "Middleware that converts responses with a map for a body into a
  templating string response."
  [handler]
  (fn [request]
    (let [response (handler request)
          render-result (try-render response)]
      (if (nil? render-result)
        response
        (let [templ-response (assoc response :body render-result)]
          (if (contains? (:headers response) "Content-Type")
            templ-response
            (content-type templ-response "text/html;charset=utf-8")))))))
