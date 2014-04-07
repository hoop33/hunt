(ns hunt.aboutme
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.walk :as walk]
            [hunt.util :as util]))

(defn aboutme [email]
  (cond 
    (nil? email) nil

    :else
    (do
      (def response (client/get (str "https://api.about.me/api/v2/json/users/search?client_id=" ((util/load-config "config-aboutme.edn") :client-id) "&email=" email)))
      (def body (json/read-str (get response :body)))
      (walk/keywordize-keys (nth (get body "result") 0 nil)))))

