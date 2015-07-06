(ns fallas-tp.core
  (:gen-class)
  (use fallas-tp.rules)
  (use fallas-tp.utilities)
  )

(def opcion-salir "0")

;Se definen las opciones para validar el input del usuario.
(def tEquipo_opcionesValidas [opcion-salir (str tE_RF) (str tE_RC) (str tE_AA)])
(def tFalla_opcionesValidas [opcion-salir (str tF_EL) (str tF_ME)])
(def falla_opcionesValidas [opcion-salir (str f_EnfMucho) (str f_EnfPoco) (str f_FugaCorr) (str f_Ruidos) (str f_Transpira) (str f_FormaEscarcha) (str f_AltoConsumo) (str f_MotoNoArranca) (str f_MotoNoArrancaCortaProt) (str f_MotoArrancaCortaProt) (str f_Gotea) (str f_HieloEvaporador)])


(defn pedir-usuario-tipo-equipo []
  "Devuelve la opción seleccionada por el usuario del tipo de equipo"
	(println "Por favor, ingrese el tipo de equipo de refrigeración a diagnosticar:") 
  (println (str tE_RF ". Refrigeracion Familiar."))
  (println (str tE_RC ". Refrigeracion Comercial."))
  (println (str tE_AA ". Aire acondicionado."))
  (println (str opcion-salir ". Salir del sistema."))
  (read-line)
)

(defn pedir-usuario-tipo-falla []
  "Devuelve la opción seleccionada por el usuario del tipo de falla"
  (println "Por favor, ingrese el tipo de falla del equipo:") 
	(println (str tF_EL ". Falla eléctrica."))
	(println (str tF_ME ". Falla mecánica."))
	(println (str opcion-salir ". Salir del sistema."))
	(read-line)
)

(defn pedir-usuario-falla []
  "Devuelve la opción seleccionada por el usuario de la falla"
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
  (println (str opcion-salir ". Salir del sistema."))
	(read-line)
  )

(defn mostrar-resultados [tipoDeEquipo falla tipoDeFalla] 
  "Imprime por pantalla el resultado de evaluar las reglas"
  (println "***************************************************************************************")
	(println "A continuacion se presentan las posibles causas de la falla, su descripción y solución:")
	(println "***************************************************************************************")
  (def result (reglas [(parse-int tipoDeEquipo) (parse-int falla) (parse-int tipoDeFalla)]))

  (let [idv (map vector (iterate inc 0) result)]
    (doseq [[index value] idv]
      (let [idv1 (map vector (iterate inc 0) value)]
        (doseq [[index1 value1] idv1]
          (case index1
            0 (println (str "Causa: " value1))
            1 (println (str "Motivo: " value1))
            2 (println (str "Solucion: " value1)))
          )
          (println "")
          )
      )
    )
  )



(defn flujo-principal []
 (println "*******************************BIENVENIDO*******************************************")
	(println "Sistema experto para el diagnóstico de fallas en equipos de refrigeración.")
	(println "************************************************************************************")
 (def tipo-de-equipo (pedir-usuario-tipo-equipo))
 (while (not (sequence-contains? tEquipo_opcionesValidas tipo-de-equipo))
   (println "La opción ingresada no es válida, reintentando...")
   (def tipo-de-equipo (pedir-usuario-tipo-equipo))
   )
 (if (= opcion-salir tipo-de-equipo) (salir-sistema))
  
 (println "")
 (def tipo-de-falla (pedir-usuario-tipo-falla))
  (while (not (sequence-contains? tFalla_opcionesValidas tipo-de-falla))
    (println "La opción ingresada no es válida, reintentando...")
    (def tipo-de-falla (pedir-usuario-tipo-falla))
    )
 (if (= opcion-salir tipo-de-falla) (salir-sistema))
    
 (println "")
 (def falla (pedir-usuario-falla))
  (while (not (sequence-contains? falla_opcionesValidas falla))
    (println "La opción ingresada no es válida, reintentando...")
    (def falla (pedir-usuario-falla))
    )
  (if (= opcion-salir falla) (salir-sistema))
 
 (def result (reglas [(parse-int tipo-de-equipo) (parse-int falla) (parse-int tipo-de-falla)]))
 (if (empty? result) 
    (println "No se cuentan con posibles causas a la falla en la base de datos.")
    (mostrar-resultados tipo-de-equipo falla tipo-de-falla)
 )
 
 (println "¿Desea reiniciar el proceso (s/n)?")
 (def rta (read-line))
 (if (or (= rta "S") (= rta "s")) (flujo-principal) (salir-sistema ))
)

(defn -main [& args]
 (flujo-principal)
 )