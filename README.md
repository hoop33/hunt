# hunt

This is my learn-Clojure project. It searches for a person by name and email address across various social media sites.

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

You need an About.me Client ID, which you can get from the About.me site. Set it as an environment variable called ABOUTME_CLIENT_ID.

## Running

To start a web server for the application, run:

    lein ring server

## Release History

0.1 04/01/2014

* Gravatar support

0.2 04/07/2014

* About.me support

0.2.1 04/07/2014

* Move About.me client ID from a configuration file to an environment variable for Heroku deployment

## License

Copyright © 2014 FIXME
