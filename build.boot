#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.1"

(set-env!
  :project      'datomic-test
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[[tailrecursion/boot.task   "2.2.4"]
                  [tailrecursion/hoplon      "5.10.25"]
                  ; [com.datomic/datomic-pro "0.9.4766"]
                  [com.datomic/datomic-free "0.9.5153" :exclusions [joda-time]]
                  ; [com.datomic/datomic-free "0.9.4766"]
                  ]
  :out-path     "resources/public"
  :src-paths    #{"src/hl" "src/cljs" "src/clj"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require '[tailrecursion.hoplon.boot :refer :all]
         '[tailrecursion.castra.task :as c])

(deftask development
  "Build datomic-test for development."
  []
  (comp (watch) (hoplon {:prerender false}) (c/castra-dev-server 'datomic-test.api)))

(deftask dev-debug
  "Build datomic-test for development with source maps."
  []
  (comp (watch) (hoplon {:pretty-print true
                         :prerender false
                         :source-map true}) (c/castra-dev-server 'datomic-test.api)))

(deftask production
  "Build datomic-test for production."
  []
  (hoplon {:optimizations :advanced}))
