(ns minority.core)

(defn flip [choice]
  (if (= choice "a")
    "b" "a"))

(defn new-agent [old-agent]
  (let [new-choice (flip (:choice old-agent))]
    {:name (:name old-agent)
     :choice new-choice
     :history (cons new-choice (:history old-agent))}))

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

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
