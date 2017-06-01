(defproject web "1.0.0" ;Maven在Clojure中的实现方式
  :dependencies [[org.clojure/clojure "1.6.0"] ;设置clojure版本
                 [org.clojure/java.jdbc "0.3.6"] ;加载jdbc模块
                 [ring "1.4.0"] ;加载ring框架
                 [ring/ring-json "0.3.1"]
                 [compojure "1.2.1"] ;路由框架
                 [selmer "0.7.2"] ;HTML处理模板
                 [mysql/mysql-connector-java "5.1.29"]
                 [korma "0.3.0"]] ;MySQL工具，该工具不能用于其他数据库
  :ring {:handler main/app
         :auto-reload? true
         :auto-refresh? true}
  :main web.main ;程序入口
  :aot [web.main])
