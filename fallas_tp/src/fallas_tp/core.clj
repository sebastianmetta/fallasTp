(ns fallas-tp.core
  (:gen-class)
  (use fallas-tp.rules)
  (use fallas-tp.utilities)
  )

(def opcion_salir 0)

(def tEquipo_opcionesValidas [tE_RF tE_RC tE_AA])

;(defn -main [& args]
	(println "*******************************BIENVENIDO*******************************************")
	(println "Sistema experto para el diagnóstico de fallas en equipos de refrigeración.")
	(println "************************************************************************************")

	(println "Por favor, ingrese el tipo de equipo de refrigeración a diagnosticar:") 
	(println (str tE_RF ". Refrigeracion Familiar."))
	(println (str tE_RC ". Refrigeracion Comercial."))
	(println (str tE_AA ". Aire acondicionado."))
  (println (str opcion_salir ". Salir del sistema."))
  (def tipoDeEquipo (read-line))
	(println (str "Opción seleccionada: " tipoDeEquipo))
	
   (println "")
   (println "Por favor, ingrese el tipo de falla del equipo:") 
	(println (str tF_EL ". Falla eléctrica."))
	(println (str tF_ME ". Falla mecánica."))
	(println (str opcion_salir ". Salir del sistema."))
	(def tipoDeFalla (read-line))
   (println (str "Opción seleccionada: " tipoDeFalla))

  (println "")
	(println "Por favor, ingrese el tipo de falla del equipo:")
  (println (str f_EnfMucho ". El equipo enfría mucho."))
	(println (str f_EnfPoco ". El equipo enfría poco."))
	(println (str f_FugaCorr ". El equipo presenta fuga de corriente eléctrica."))
	(println (str f_Ruidos ". El equipo realiza ruidos anormales."))
	(println (str f_Transpira ". El equipo transpira en la parte externa del gabinete."))
	(println (str f_FormaEscarcha ". El equipo forma escarcha en la parte interna del gabinete."))
	(println (str f_AltoConsumo ". El equipo genera un alto consumo de corriente eléctrica."))
	(println (str f_MotoNoArranca ". El motocompresor del equipo no inicia."))
	(println (str f_MotoNoArrancaCortaProt ". El motocompresor del equipo no inicia y el protector térmico se corta."))
	(println (str f_MotoArrancaCortaProt ". El motocompresor del equipo inicia y el protector térmico se corta."))
	(println (str f_Gotea ". Gotea agua hacia el interior del equipo."))
	(println (str f_HieloEvaporador ". Se forma hielo en el evaporador."))
  (println (str opcion_salir ". Salir del sistema."))
	(def falla (read-line))
	(println (str "Opción seleccionada: " falla))
 
  ;Validación de las opciones ingresadas por el usuario
  ;TODO: Revisar.
  ;(sequence-contains? tEquipo_opcionesValidas tipoDeEquipo)
  
  (println "Las posibles causas y soluciones al problema son:")
  (def result (reglas [(parse-int tipoDeEquipo) (parse-int falla) (parse-int tipoDeFalla)]))
  
  (let [idv (map vector (iterate inc 0) result)]
  (doseq [[index value] idv] (println value)))
  
;)