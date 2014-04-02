(ns hunt.routes.home
  (:use compojure.core)
  (:require [hunt.views.layout :as layout]
            [hunt.util :as util]
            [clojure.string :as string]))

(defn gravatar-img-url [email]
  (if (nil? email) nil
    (str "http://www.gravatar.com/avatar/" (util/md5 (string/trim (string/lower-case email))))))

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
