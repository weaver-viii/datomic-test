(ns datomic-test.rpc
	(:require-macros
		[tailrecursion.javelin :refer [defc defc= cell=]])
	(:require
	 [tailrecursion.javelin :refer []]
	 [tailrecursion.castra :refer [mkremote]]))

(defc state {:random nil})
(defc error nil)
(defc loading [])

(defc= random-number (get state :random))

(def get-state
	(mkremote 'datomic-test.api/get-state state error loading))

(defn init []
	(cell= (print error))
	(get-state)
	(js/setInterval get-state 1000))
