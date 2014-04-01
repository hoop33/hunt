(ns hunt.routes.home
  (:use compojure.core)
  (:require [hunt.views.layout :as layout]
            [hunt.util :as util]
            [clojure.string :as string]))

(defn md5
  "Generate a md5 checksum for the given string"
  [token]
  (let [hash-bytes
         (doto (java.security.MessageDigest/getInstance "MD5")
               (.reset)
               (.update (.getBytes token)))]
       (.toString
         (new java.math.BigInteger 1 (.digest hash-bytes)) ; Positive and the size of the number
         16))) ; Use base16 i.e. hex

(defn gravatar-img-url [email]
  (if (nil? email) nil
    (str "http://www.gravatar.com/avatar/" (md5 (string/trim (string/lower-case email))))))

(defn home-page [& [name email error]]
  (layout/render
    "home.html" {:error error
                 :name name
                 :email email
                 :gravatar-img-url (gravatar-img-url email)
                 }))

(defn about-page []
  (layout/render "about.html"))

(defn hunt [name email]
  (cond

    (empty? name)
    (home-page name email "We need the name")

    (empty? email)
    (home-page name email "We need the email address")

    :else
    (do
      (home-page name email))))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/" [name email] (hunt name email))
  (GET "/about" [] (about-page)))
