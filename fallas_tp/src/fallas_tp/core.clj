(ns fallas-tp.core
  (:gen-class))

(println "Bienvenido al sistema experto para el diagnóstico de causas ante fallas en equipos de refrigeración.")
(println "Ingrese el tipo de equipo de refrigeración a diagnosticar:")
(println "1. Refrigeracion Familiar")
(println "2. Refrigeracion Comercial")
(println "3. Aire acondicionado")
(println ":")

(def tipoDeEquipo (read-line))

(if (= tipoDeEquipo "1")
  (println "Seleccionado Refrigeración Famililar"))
(if (= tipoDeEquipo "2")
  (println "Seleccionado Refrigeracion Comercial"))
(if (= tipoDeEquipo "3")
  (println "Seleccionado Aire acondicionado"))