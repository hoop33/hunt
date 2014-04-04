(ns hunt.gravatar
  (:require [hunt.util :as util]
            [clojure.string :as string]))

(defn gravatar-img-url [email]
  (if (nil? email) nil
    (str "http://www.gravatar.com/avatar/" (util/md5 (string/trim (string/lower-case email))))))

