(ns jdbc.info
  (:use korma.db
        korma.core
        jdbc.korma))

(declare info) ;定义
(defentity info) ;实体

;查询数据
(defn get-info[]
  (select info
          (order :id :desc)))

;插入数据
(defn create-info[i]
  (insert info
          (values i)))

;删除数据
(defn delete-info[id]
  (delete info
          (where {:id id})))

;批量初始化数据
(defn init-info[]
  (if (empty? (get-info))
    (let [cur (System/currentTimeMillis)
         info [{:id (str cur) :info_key "Java" :info_value "System.out.println(\"\")"},
               {:id (str (+ cur 1)) :info_key "Scala" :info_value "println \"\""},
               {:id (str (+ cur 2)) :info_key "Clojure" :info_value "(println \"\")"}]]
     (dorun
        (map create-info info)))))

(init-info)