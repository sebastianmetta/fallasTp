(ns fallas-tp.core
  (:gen-class))

(println "*******************************BIENVENIDO*******************************************")
(println "Sistema experto para el diagnóstico de causas de fallas en equipos de refrigeración.")
(println "************************************************************************************")

;TODO: Poner en una función
(println "Por favor, ingrese el tipo de equipo de refrigeración a diagnosticar:")
(println "1. Refrigeracion Familiar")
(println "2. Refrigeracion Comercial")
(println "3. Aire acondicionado")

(def tipoDeEquipo (read-line))
;TODO: Hacer un while para validar opcion correcta o salir.
(if (= tipoDeEquipo "1")
  (println "Seleccionado Refrigeración Famililar"))
(if (= tipoDeEquipo "2")
  (println "Seleccionado Refrigeracion Comercial"))
(if (= tipoDeEquipo "3")
  (println "Seleccionado Aire acondicionado"))