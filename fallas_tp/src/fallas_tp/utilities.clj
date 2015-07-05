(ns fallas-tp.utilities)

  (defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))
  
  (defn sequence-contains? 
  "true si secuencia contiene elemento"
  [secuencia elemento]  
  (some #(= elemento %) secuencia))
  
