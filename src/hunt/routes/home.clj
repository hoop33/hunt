(ns hunt.routes.home
  (:use compojure.core)
  (:require [hunt.views.layout :as layout]
            [hunt.gravatar :as gravatar]))

(defn home-page [& [name email error]]
  (layout/render
    "home.html" {:error error
                 :name name
                 :email email
                 :gravatar-img-url (gravatar/gravatar-img-url email)
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
