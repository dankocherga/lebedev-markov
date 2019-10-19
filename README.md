# Lebedev Markov

Markov chain implementation to generate text from Artemy Levedev livejournal posts.   
Some results: https://t.me/markov_lebedev

# How to run

From the lein repl:

```
(def state-size 2)
(def words (str/split (slurp "resources/all.txt") #"\s"))
(def chain (build-chain words state-size))

(str/join " " (take 100 (drop 100 chain))))
```
