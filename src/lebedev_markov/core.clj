(ns lebedev-markov.core
  (:require [clojure.string :as str]))

(defn- update-state 
  [state word]
  (conj (subvec state 1) word))

(defn- build-index [words init-state]
  (loop
    [[word & remaining] words
     index {}
     state init-state]
    (if (nil? word)
      index
      (recur remaining
             (update-in index [state] (fnil #(conj % word) []))
             (update-state state word)))))

(defn- word-seq
  [idx state]
   (lazy-seq
     (let [words (get idx state [])
            next-word (get words (rand-int (count words)))]
        (cons next-word (word-seq idx (update-state state next-word))))))

(defn build-chain
  [words state-size]
  (let [init-state (into [] (repeat state-size nil) )
        idx (build-index words init-state)]
    (word-seq idx init-state)))

; (def chain (build-chain
;              (str/split (slurp "resources/all.txt") #"\s")
;              2))

; (str/join " " (take 100 (drop 1000 result)))
