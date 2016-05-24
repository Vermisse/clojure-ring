(ns jdbc.korma
  (:use korma.db
        korma.core))

(defdb korma-db
  (mysql {:db "info"
          :host "localhost"
          :port 3306
          :user "root"
          :password "123456"}))