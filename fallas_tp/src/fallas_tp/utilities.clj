(ns fallas-tp.utilities)

  (defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))
  
  (defn sequence-contains? 
  "true si la secuencia contiene el elemento"
  [secuencia elemento]  
  (some #(= elemento %) secuencia))
  
  (defn salir-sistema []
    "Finaliza el programa"
    (println "Programa finalizado.")
    (System/exit 0)
    )
