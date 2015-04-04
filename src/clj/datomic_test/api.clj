(ns datomic-test.api
	(:require
		[tailrecursion.castra :refer [defrpc]]
		[datomic.api :as d]))

(def db-uri "datomic:free://localhost:4334/leaderboard")
; (d/delete-database db-uri)
; (d/create-database db-uri)
(def conn (d/connect db-uri))

(def db (d/db conn))
(def test-results (d/q '[:find ?e ?v :where [?e :db/doc ?v]] db))

(defrpc get-state []
	{:random (vec test-results)})
