(ns minority.core)

(defn flip [choice]
  (if (= choice "a")
    "b" "a"))

(defn score-agents [old-system]
  (let [agents (:agents old-system)
        a-choices
        (mapcat (fn [ag]
                  (if (= (:choice ag) "a")
                    (list "a")))
                agents)
        b-choices
        (mapcat (fn [ag]
                  (if (= (:choice ag) "b")
                    (list "b")))
                agents)
        winner (if (< (.size a-choices)
                      (.size b-choices))
                 "a" "b")]
    {:agents (pmap (fn [ag] (new-agent ag)) agents)
     :scores (cons (if (= winner "a")
                     (.size a-choices)
                     (.size b-choices))
                   (:scores old-system))}))

(defn new-agent [old-agent]
  (let [new-choice (flip (:choice old-agent))]
    {:name (:name old-agent)
     :choice new-choice
     :history (cons
               {:won? nil
                :new-choice new-choice}
               (take 5 (:history old-agent)))}))

(def agents [ {:name "one"
               :choice "a"
               :history []
               }
              {
               :name "two"
               :choice "b"
               :history []
               }
              {:name "three"
               :choice "a"
               :history []
               } ])

(def initial-system {:agents agents
                     :scores []})
