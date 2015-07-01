(ns fallas-tp.core
  (:gen-class))

(def opcion_salir 0)

;Tipo de equipos.
(def tE_RF 1)
(def tE_RC 2)
(def tE_AA 3)

;Tipos de fallas.
(def tF_EL 1)
(def tF_ME 2)

;Fallas.
(def f_EnfMucho 1)
(def f_EnfPoco 2)
(def f_FugaCorr 3)
(def f_Ruidos 4)
(def f_Transpira 5)
(def f_FormaEscarcha 6)
(def f_AltoConsumo 7)
(def f_MotoNoArranca 8)
(def f_MotoNoArrancaCortaProt 9)
(def f_MotoArrancaCortaProt 10)
(def f_Gotea 11)
(def f_HieloEvaporador 12)

(def base {
           
[1 1 1] [
["Error en la conexión electrica " "Puede ocurrir que haya cables deteriorados en contacto que produzan un puente entre componentes produciendo un enfriamento continuo en el equipo. " "Verificar estado del circuito electrico del sistema"]
["Termostato no desconecta " "Un termostato dañado puede ocurrir que no desconecte haciendo que el motocompresor funcione constantemente y enfrie mucho " "El termostato debe ser reemplazado"]
["Termostato regulado en la posicion maxima " "Un termostato regulado en posicion maxima puede generar que el equipo enfrie demasiado. " "Regular la posicion de la perilla del termostato para evitar que enfrie mucho"]
["Termostato con bulbo suelto " "En casos en que el bulbo se encuentre suelto puede comar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato con bulbo fuera de la posicion original " "En casos en que el bulbo se encuentre fuera de la posicion correcta puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "El termostato inadecuado puede trabajar sobre un rango de temperaturas inadecuado al sistema, produciendo que el equipo enfrie mucho. " "El termostato trabaja en un rango de temperaturas inadecuado al sistema, debe ser reemplazado por el correspondiente al equipo."]
]

[1 2 1] [
["Lampara interna no apaga " "Transmision de calor de la lampara en gabinete " "Revisar conexión electrica/revisar que la puerta desactive el sensor de lampara/sensor dañado"]
["Termostato regulado en la posicion minima " "Perilla del termostato en posicion minima " "Aumentar a una posicion mayor"]
["Termostato con bulbo fuera de la posicion original " "El bulbo se encuentra en una posicion incorrecto en la cual le lleva mas frio del que es debido " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "El termostato trabaja en un rango de temperaturas inadecuado al sistema " "Debe ser reemplazado por el correspondiente al equipo."]
]

[1 3 1] [
["Mala aislacion de los cables " "La aislacion de algun cable se encuentra dañana " "Revisar la asilacion de la conexión electrica"]
["Cabelado o componentes electricos en contacto con partes metalicas " "La aislacion de algun cable se encuentra dañana o componente en contacto con parte metalica del equipo " "Revisar la asilacion de la conexión electrica y componentes"]
["Componente con fuga de corriente (termostato, portalamparas, etc) " " componente en contacto con parte metalica del equipo " "Revisar posicion de los componentes"]
["Motocompresor con fuga de corriente " "Motocompresor con daño en aislacion de las bobinas " "Reemplazar compresor "]
]

[1 4 1] ["Termostato generando ruido " "Ruido generado por vibracion " "Revisar fijacion del mismo y sus componentes (capilar y bulbo)"]

[1 5 1] [
["Termostato no desconecta " "El termostato supera el frio que soporta la aislacion termica " "dañado el termostato, debe ser reemplazado"]
["Termostato con bulbo suelto " "El bulbo suelto produce que el sistema no corte superando el frio que soporta la aislacion termica " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato con bulbo fuera de la posicion original " "El bulbo suelto produce que el sistema no corte superando el frio que soporta la aislacion termica " "colocar el bulbo en la posicion original (final del evaporador)"]
]

[1 6 1] ["Sistema de descongelado deteriorado  " "Se procude excesiva acumulacion de hielo en la parte interna del gabinete " " Verificar conexión electrica y estado de componenetes (temporizador, fusible, resistencia, comando, etc) "]

[1 7 1] [
["Error en la conexión electrica " "Daño enla conexión electrica " "Verificar conexión electrica y aislacion de la misma"]
["Lampara interna no apaga " "Consumo continua de corriente en lampara " "Revisar conexión electrica/revisar que la puerta desactive el sensor de lampara/sensor dañado"]
["Termostato no desconecta " "Consumo continua de corriente del motocompresor " "dañado el termostato, debe ser reemplazado"]
["Termostato regulado en la posicion maxima " "El termostato en posicion maxima genera que el motocompresor trabaje continuamente " "Regular la poscion de la perilla del termostato para evitar que enfrie mucho"]
["Termostato con bulbo suelto " "En casos en que el bulbo se encuentre suelto puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "En casos en que el bulbo se encuentre fuera de la posicion correcta puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Motocompresor con alto amperaje (corriente elevada) " "Problema electrico de tension en el toma corriente o sistema electrico de motocompresor dañado " "Verificar tension en toma corriente (reestablecer a 220v)/Si el sistema electrico del motocompresor esta dañado debe reemplazarse"]
]

[1 8 1] [
["Falta de tension en el tomacorriente " "Una falla o mala instalacion del tomacorriente puede generar que el mismo no tenga corriente y como consecuencia el equipo no funcione " "Verificar el funcionamiento del tomacorriente"]
["Cable de linea o cableado interrumpido " "Una interrupcion en el cable de la linea y cableado del sistema puede impedir la llegada de corriente al equipo. " "Verificar el cableado de la linea y del equipo."]
["Error en la conexión electrica " "Un error en la conexión electrica produce que el equipo no encienda " "Verificar que la conexión electrica sea correcta, en tal caso corregir el error."]
["Componentes electrciso que no permiten el paso de corriente al motocompresor " "Un componente defectuoso no permite el paso de corriente al motocompresor " "Verificar componentes electricos"]
["Termostato desconectado " "Termostato desconectado " "Revisar conexión electrico del termostato"]
["Termostato sin pasaje de corriente por los contactos " "Contactos deteriorados o sulfatados en el termostato " "Limpiar contactos de componentes"]
["Protector termico defectuoso " "El protector termico dañado impide el paso de corriente al motocompresor " "Reemplazar el protector termico"]
["Relé de arranque " "El relé de arranca defectuoso impide el arranque del motocompresor " "Chequear funcionamiento del rele y sustitucion en caso de daño"]
["Bobinados del motor del motocrompresor quemados o en corto " "Bobinado dañado impide el funcionamiento del motocompresor " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
]

[1 9 1] [
["Muy baja tension " "La baja tension de linea produce un alto consumo de corriente que es detectado por el protector termico y este corta el paso de corriente protegiendo al motocompresor " "Verificar la tension en la linea y reestablecerla a 220v"]
["Protector térmico incorrecto " "El protector termico incorrecto al sistema " "Sustituir el protector termico por el correcto"]
["Relé de arranque " "Al estar defectuoso el rele de arranque no permite que el motocompresor arranque generando un alto consumo de corriente que es detectato por el protector termico " "Verificar el estado del rele de arranque y sistituirlo en caso de daño"]
["Capacitor con arranque incorrecto " "El capacitor de arranque incorrecto no genera la suficiente potencia para que arranque el motocompresor " "Reemplazar el capacitor por el que el sistema necesita"]
["Capacitor con arranque abierto " "El capacitor de arranque abierto o dañado no permite el paso de corriente " "Reemplazare el capacitor dañado"]
["Motocompresor conectado a un voltaje diferente a lo especificado " "El voltaje incorrecto impide que el motocompresor no arranque produciendose un alto consumo de corriente que es detectado por el protector termico " "Verificar y corregir voltaje en la linea"]
["Bobinados del motor del motocrompresor quemados o en corto " "Bobinado dañado impide el funcionamiento del motocompresor " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
]

[1 10 1] [
["Muy baja tension " "La baja tension es compensada por un alto consumo de corriente detectado por el protector termico interrumpìendo el paso de corriente. " "Verificar la tension en la linea y reestablecerla a 220v"]
["Protector térmico incorrecto " "Un protector termico incorrecto deja pasar una corriente menor a la que requiere el sistema " "Reemplazar protector termico por el adecuado al sistema "]
["Relé de arranque " "Un rele de arranque defectuoso o incorrecto produce un mayor consumo en el motocompresor que es detectado por el protector termico " "Verificar y reemaplazar en caso de daño el relé de arranque"]
["Motocompresor conectado a un voltaje diferente a lo especificado " "El voltaje incorrecto deja al motocompresor arrancar pero produciendose un alto consumo de corriente que es detectado por el protector termico " "Verificar y corregir voltaje en la linea"]
["Bobinados del motor del motocrompresor quemados o en corto " "Bobinado dañado produce un funcionamiento inadecuado del motocompresor elevando el consumo de corriente que es detectado por el protector termico. " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
["Motocompresor con alto amperaje (corriente elevada) " "Problema electrico de tension en el toma corriente o sistema electrico de motocompresor dañado " "Verificar tension en toma corriente (reestablecer a 220v)/Si el sistema electrico del motocompresor esta dañado debe reemplazarse"]
]
 [1 1 2] [
["Obstrucción parcial de la cañería " "Humedad, polvillo o caño acotado produce una obstruccion parcial de la cañeria que produce un bajo rendimiento del sistema de refrigeracion " "Revisar cañeria del sistema dañada del sistema y verificar temperaturas de condesador y filtro para detectar una falsa evaporacion"]
["Obstrucción del tubo capilar por humedad " "Si el sistema esta contaminado con humedad esta se condensa congelandose en el tubo capilar produciendo una obstruccion del mismo " "Realizar una deshidratacion del sistema mediante bomba de vacio para eliminar la humedad. Luego debe recargase el sistema con gas por completo"]
["Condensador sucio o con falta de circulación de aire " "Condensador sucio o con falta de circulación de aire impide el intercambio de calor del condensador con el ambiente  " "Limpiar el condensador o cambiar la ubicación del equipo"]
["Hermeticidad inadecuada de la puerta y/o gabinete " "La hermeticidad inadecuada de la puerta y/o el gabinete genera aumento de temperatura en el gabinete " "Chequear que el burlete este sellando correctamente el gabinete y en caso de daño reemplazarlo. Es posible que deba regularse la poscion de la puerta"]
["Localización inadecuada del equipo " " La falta de circulación de aire impide el intercambio de calor del condensador con el ambiente produciendo un bajo rendimiento del sistema de refrigeracion " "Cambiar la ubicación del equipo"]
["Gabinete utilizado en exceso y/o excesivas aperturas de puertas " "El exceso y/o excesivas aperturas de puertas aumenta la temperatura del gabinete. " "Comunicar al usuario el uso inadecuado de dicho exceso."]
["Agua en la aislación (lana de vidrio) " "Es producida por un deterioro en la asilacion termica lo cual reduce el enfriamiento del gabinete tratando de enfriar el agua que esta en dicha aislacion termica " "Reemplazar la aislancion termica"]
["Deterioro o falta de aislación térmica " "Es producida por un deterioro en la asilacion termica lo cual reduce el enfriamiento del gabinete retirando calor del ambiente ya que no posee aislacion termica " "Reemplazar la aislancion termica"]
["Exceso de carga de fluido refrigerante (aumento de las presiones de baja y alta) " "El exceso de gas produce un muy bajo rendimiento del sistema " "Verificar (quitar) las presiones de gas del sistema"]
["Falta de fluido refrigerante (verificar sobrecalentamiento) " "La falta de gas produce un muy bajo rendimiento del sistema " "Verificar (cargar) las presiones de gas del sistema"]
["Motocompresor inadecuado al sistema " "Un motocompresor inadecuado al sistema produce un muy bajo rendimiento " "Verificar compatibilidad del motocompresor con el sistema"]
["Motocompresor con baja capacidad (gastado) " "Un motocompresor con baja capacidad (gastado) genera un bajo rendimiento " "Verificar que las presiones del sistema respondan adecuadamente. En caso de ser asi reemplazar el motocompresor"]
]
           
[1 4 2] [
["Condensador mal instalado – caños metálicos en contacto " "Un condensador mal instalado o con caños metálicos en contacto generan ruidos por vibracion " "Verificar contactos metalico para evitar vibraciones"]
["Nivelación incorrecta del equipo o de la base del mocompresor " "La nivelación incorrecta del equipo o de la base del mocompresor produce vibracion interna en el motocompresor " "Verificar el nivel correcto del equipo y del motocompresor, corregir de ser necesario"]
["Ruidos provocados por otros componentes (estructura del equipo floja o mal colocada, falta de tornillos, etc) " "Un mal ensamblado de las piezas del equipo o falta de ajuste a sus piezas puede generar ruidos y vibraciones en el equipo. " "Chequear el correcto ensamble del equipo, falta de tornillos o piezas, etc."]
["Expansión de fluido refrigerante en el evaporador (generalmente normal) " "Por movilizar el equipo de manera inadecuada el aceite del motocompresor puede quedar atrapado en el evaporador generando ruidos " "Hacer una limpieza interna del sistema de refrigeracion usando gas de limpieza posteriormente haciendo una deshidratacion con vacio y carga de gas"]
["Colocación inadecuada del motocompresor " "Colocación inadecuada del motocompresor genera vibracion. " "Verificar nivel y fijacion del mismo y corregirlo."]
["Motocompresor con ruido interno " "Deterioro interno en la fijacion de sus componentes genera vibraciones " "Cambiar motocompresor"]
]
           
[1 5 2] [
["Humedad relativa del aire muy elevada (superior al 85%) " "La humedad del ambiente se trasporta automaticamente a los lugares mas frios, en este caso la parte externa del gabinete. " "Controlar humedad del ambiente y verificar aislacion termica del gabinete."]
["Agua en la aislación (lana de vidrio) " "Es producida por un deterioro en la asilacion termica " "Reemplazar la aislancion termica"]
["Deterioro o falta de aislación térmica " "Es producida por un deterioro en la asilacion termica lo cual reduce el enfriamiento del gabinete retirando calor del ambiente ya que no posee aislacion termica " "Reemplazar la aislancion termica"]
]
           
[1 6 2] [
["Hermeticidad inadecuada de la puerta y/o gabinete " "La humedad del ambiente se trasporta al interior del gabinete ya que la hermiticidad del mismo esta deteriorada " "Verificar y reemplazar en caso correspondiente el burlete"]
["Gabinete utilizado en exceso y/o excesivas aperturas de puertas " "La humedad del ambiente se trasporta al interior del gabinete por el excesivo uso de aperturas de puerta " "Comunicar al usuario el uso inadecuado de dicho exceso."]
]
           
[1 7 2] [
["Condensador sucio o con falta de circulación de aire " "El condensador sucio o con falta de circulación de aire produce que la presion de alta del sistema sea mas elevada que la normal generando un mayor consumo de corriente " "Limpiar el condensador o cambiar la ubicación del equipo"]
["Hermeticidad inadecuada de la puerta y/o gabinete " "El exceso de corriente consumida es producido por la hermiticidad inadecuada del gabinete forzando al motocompresor " "Verificar hermiticidad de puerta y gabinete."]
["Localización inadecuada del equipo " " La falta de circulación de aire impide el intercambio de calor del condensador con el ambiente produciendo un alto consumo de corritente para llegar a su regimen deseado " "Cambiar la ubicación del equipo"]
["Gabinete utilizado en exceso y/o excesivas aperturas de puertas " "La apertura excesiva de la puerta del gabinete no permite que el equipo llegue a temperatura correspondiente. " "Comunicar al usuario el uso inadecuado de dicho exceso."]
["Agua en la aislación (lana de vidrio) " "Es producida por un deterioro en la aislacion termica gastando energia tratando de enfriar el agua que contiene la misma " "Reemplazar la aislancion termica"]
["Deterioro o falta de aislación térmica " "Es producida por un deterioro en la asilacion termica lo cual reduce el enfriamiento del gabinete retirando calor del ambiente ya que no posee aislacion termica " "Reemplazar la aislancion termica"]
["Exceso de carga de fluido refrigerante (aumento de las presiones de baja y alta) " "El exceso de gas produce un consumo mas elevado de corriente que lo normal " "Verificar presiones de gas en el sistema."]
["Motocompresor inadecuado al sistema " "Un motocompresor inadecuado al sistema genera un consumo elevado de corriente " "Reemplazar el motocrompresor por el adecuado"]
["Motocompresor con baja capacidad (gastado) " "Un motocompresor gastado o deteriorado genera un consumo elevado de corriente " "Reemplazar el motocrompresor"]
]

[1 9 2] ["Motocompresor deteriorado mecánicamente " "Un motocompresor dañado mecanicamente que no arranca genera un consumo elevado de corriente que es detectado por el protector termico (motocompresor clavado) " "Reemplazar el motocrompresor"]

[1 10 2] [
["Obstrucción parcial de la cañería " "Un obstruccion parcial de la cañeria genera un exceso en la presion de alta aumentando el consumo de corriente que es detectado por el protecto termico " "Realizar una deshidratacion del sistema mediante bomba de vacio para eliminar la humedad. Luego debe recargase el sistema con gas por completo"]
["Condensador sucio o con falta de circulación de aire " "El condensador sucio o con falta de circulación de aire produce que la presion de alta del sistema sea mas elevada que la normal generando un mayor consumo de corriente que es detectado por el protecto termico " "Limpiar el condensador o cambiar la ubicación del equipo"]
["Localización inadecuada del equipo " " La falta de circulación de aire impide el intercambio de calor del condensador con el ambiente produciendo un alto consumo de corritente que es detectado por el protector termico " "Cambiar la ubicación del equipo"]
["Exceso de carga de fluido refrigerante (aumento de las presiones de baja y alta) " "El exceso de gas produce un consumo mas elevado de corriente que lo normal que es detectado por el protector  " "Verificar presiones de gas en el sistema."]
["Motocompresor inadecuado al sistema " "Un motocompresor inadecuado al sistema genera un consumo elevado de corriente que es detectado por el protector termico " "Reemplazar el motocrompresor por el adecuado"]
["Motocompresor deteriorado mecánicamente " "Un motocompresor dañado mecanicamente genera un consumo elevado de corriente que es detectado por el protector termico " "Reemplazar el motocrompresor"]
]

[2 1 1] [
["Error en la conexión electrica " "Puede ocurrir que haya cables deteriorados en contacto que produzan un puente entre componenetes " "Verificar estado del circuito electrico del sistema"]
["Termostato no desconecta " "Un termostato dañado puede ocurrir que no desconecte haciendo que el motocompresor funcione constantemente y enfrie mucho " "El termostato debe ser reemplazado"]
["Termostato regulado en la posición máxima " "Un termostato regulado en posicion maxima puede generar que el equipo enfrie demasiado. " "Regular la posicion de la perilla del termostato para evitar que enfrie mucho"]
["Termostato con bulbo fuera de la posición original " "En casos en que el bulbo se encuentre fuera de la posicion correcta puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "El termostato inadecuado puede trabajar sobre un rango de temperaturas inadecuado al sistema, produciendo que el equipo enfrie mucho. " "El termostato trabaja en un rango de temperaturas inadecuado al sistema, debe ser reemplazado por el correspondiente al equipo."]
["Presostato de baja mal regulado " "El presostato mal regulado hace que el equipo genere mas frio de lo necesario " "Regular el presostato en torno a la temperatura deseada"]
["Termostato regulado en la posición mínima " "Perilla del termostato en posicion minima " "Aumentar a una posicion mayor"]
["Termostato con bulbo fuera de la posición original " "El bulbo se encuentra en una posicion incorrecto en la cual le lleva mas frio del que es debido " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "El termostato trabaja en un rango de temperaturas inadecuado al sistema " "Debe ser reemplazado por el correspondiente al equipo."]
["Presostato de baja mal regulado " "El presostato mal regulado hace que el equipo genere mas frio de lo necesario " "Regular el presostato en torno a la temperatura deseada"]
["Forzador del evaporador (si posee) no funciona " "El forzador (ventilador) del evaporador dañado impide que el frio se distribuya en el gabinete " "Chaquear la conexión electrica del forzador, y en caso de detectar que el mismo se encuentra dañado reemplazarlo por uno nuevo"]
]

[2 3 1] [
["Mala aislacion de los cables " "La aislacion de algun cable se encuentra dañana " "Revisar la asilacion de la conexión electrica"]
["Cableado o componentes eléctricos en contacto con partes metálicas " "La aislacion de algun cable se encuentra dañana o componente en contacto con parte metalica del equipo " "Revisar la asilacion de la conexión electrica y componentes"]
["Componente con Fuga de corriente (termostato, portalámparas, etc) " " componente en contacto con parte metalica del equipo " "Revisar posicion de los componentes"]
]

[2 6 1] [
["Sistema de descongelado deteriorado (temporizador, fusible, resistencia, etc) " "Se procude excesiva acumulacion de hielo en la parte interna del gabinete " " Verificar conexión electrica y estado de componenetes (temporizador, fusible, resistencia, comando, etc) "]
["Forzador del evaporador (si posee) no funciona " "Si el forzador del evaporador no funciona se acumula el frio produciendo este una escarcha " "Chaquear la conexión electrica del forzador, y en caso de detectar que el mismo se encuentra dañado reemplazarlo por uno nuevo"]
]

[2 7 1] [
["Error en la conexión electrica " "Un error en la conexión electrica produce un alto consumo de corriente " "Verificar conexión electrica y aislacion de la misma"]
["Termostato no desconecta " "Consumo continua de corriente del motocompresor " "dañado el termostato, debe ser reemplazado"]
["Termostato regulado en la posición máxima " "El termostato en posicion maxima genera que el motocompresor trabaje continuamente " "Regular la poscion de la perilla del termostato para evitar que enfrie mucho"]
["Termostato con bulbo suelto " "En casos en que el bulbo se encuentre suelto puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "En casos en que el bulbo se encuentre fuera de la posicion correcta puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Motocompresor con alto amperaje (corriente elevada) " "Problema electrico de tension en el toma corriente o sistema electrico de motocompresor dañado " "Verificar tension en toma corriente (reestablecer a 220v)/Si el sistema electrico del motocompresor esta dañado debe reemplazarse"]
]

[2 8 1] [
["Falta de tensión en la alimentación principal del equipo " "Una falla o mala instalacion del tomacorriente puede generar que el mismo no tenga corriente y como consecuencia el equipo no funcione " "Verificar el funcionamiento del tomacorriente"]
["Cable de línea o cableado interrumpido " "Una interrupcion en el cable de la linea y cableado del sistema puede impedir la llegada de corriente al equipo. " "Verificar el cableado de la linea y del equipo."]
["Error en la conexión electrica " "Un error en la conexión electrica produce que el equipo no encienda " "Verificar que la conexión electrica sea correcta, en tal caso corregir el error."]
["Componentes eléctricos que no permiten el paso de corriente al motocompresor " "Un componente defectuoso no permite el paso de corriente al motocompresor " "Verificar componentes electricos"]
["Presostato de baja o alta desconectado " "El circuito de conexión electrcia se encuentra interrumpido ya que el presostato se encuentra desconectado " "Verificar la correcta conexión del presostato."]
["Protector térmico dañado " "El protector termico dañado impide el paso de corriente al motocompresor " "Reemplazar el protector termico"]
["Relé amperométrico de arranque defectuoso " "El relé de arranca defectuoso impide el arranque del motocompresor " "Chequear funcionamiento del rele y sustitucion en caso de daño"]
["Bobinados del motor del motocompresor defectuosos " "Bobinado dañado impide el funcionamiento del motocompresor " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
]

[2 9 1] [
["Muy baja tensión " "La baja tension de linea produce un alto consumo de corriente que es detectado por el protector termico y este corta el paso de corriente protegiendo al motocompresor " "Verificar la tension en la linea y reestablecerla a 220v"]
["Protector térmico incorrecto " "El protector termico incorrecto al sistema " "Sustituir el protector termico por el correcto"]
["Relé amperométrico de arranque defectuoso " "Al estar defectuoso el rele de arranque no permite que el motocompresor arranque generando un alto consumo de corriente que es detectato por el protector termico " "Verificar el estado del rele de arranque y sistituirlo en caso de daño"]
["Capacitor de arranque incorrecto " "El capacitor de arranque incorrecto no genera la suficiente potencia para que arranque el motocompresor " "Reemplazar el capacitor por el que el sistema necesita"]
["Capacitor de arranque abierto " "El capacitor de arranque abierto o dañado no permite el paso de corriente " "Reemplazare el capacitor dañado"]
["Motocompresor conectado a un voltaje diferente a lo especificado " "El voltaje incorrecto impide que el motocompresor no arranque produciendose un alto consumo de corriente que es detectado por el protector termico " "Verificar y corregir voltaje en la linea"]
["Bobinados del motor del motocompresor defectuosos " "Bobinado dañado impide el funcionamiento del motocompresor " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
]

[2 10 1] [
["Muy baja tensión " "La baja tension es compensada por un alto consumo de corriente detectado por el protector termico interrumpìendo el paso de corriente. " "Verificar la tension en la linea y reestablecerla a 220v"]
["Protector térmico incorrecto " "Un protector termico incorrecto deja pasar una corriente menor a la que requiere el sistema " "Reemplazar protector termico por el adecuado al sistema "]
["Relé amperométrico de arranque defectuoso " "Un rele de arranque defectuoso o incorrecto produce un mayor consumo en el motocompresor que es detectado por el protector termico " "Verificar y reemaplazar en caso de daño el relé de arranque"]
["No abre el relé voltimétrico y se recalienta el capacitor electrolítico " "Al no abrir el rele voltimetrico se recalienta el capacitor electrolitico y hace que el motocompresor funcione solo con las bobinas de trabajo, mientras que las bobinas de arranque no se acomplan forzando el trabajo del motocompresor, por ende se produce un mayor consumo que hace que el protector termino salte. " "Reemplazar el rele voltimetrico"]
["Capacitor de marcha (trabajo) deteriorado o con capacidad incorrecta " "el capacitor de trabajo deteriorado produce un desfasaje erroneo en los campos magneticos de las bobinas del motocompresor produciendose un mayor coinsumo que es detectado por el protector termico " "Reemplazar el capacitor de trabajo."]
["Motocompresor conectado a un voltaje diferente a lo especificado " "El voltaje incorrecto deja al motocompresor arrancar pero produciendose un alto consumo de corriente que es detectado por el protector termico " "Verificar y corregir voltaje en la linea"]
["Bobinados del motor del motocompresor defectuosos " "Bobinado dañado produce un funcionamiento inadecuado del motocompresor elevando el consumo de corriente que es detectado por el protector termico. " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
["Motocompresor con alto amperaje (corriente elevada) " "Problema electrico de tension en el toma corriente o sistema electrico de motocompresor dañado " "Verificar tension en toma corriente (reestablecer a 220v)/Si el sistema electrico del motocompresor esta dañado debe reemplazarse"]
]

[2 2 2] [
["Obstrucción parcial de la cañería " "Humedad, polvillo o caño acotado produce una obstruccion parcial de la cañeria que produce un bajo rendimiento del sistema de refrigeracion " "Revisar cañeria del sistema dañada del sistema y verificar temperaturas de condesador y filtro para detectar una falsa evaporacion"]
["Obstrucción de la válvula de expansión " " Al existir una obstruccion en la valvula de expansion se produce un congelamiento en la misma generando insuficiencia de frio en el evaporador. " "Reemplazar la valvula de expansion"]
["Condensador sucio o con falta de circulación de aire " "Condensador sucio o con falta de circulación de aire impide el intercambio de calor del condensador con el ambiente  " "Limpiar el condensador o cambiar la ubicación del equipo"]
["Forzador del condensador en mal estado (aumento de la presión de alta) " "Al no funcionar el forzador del condensador este no puede retirar calor hacia el ambiente en forma correcta, produciendo un aumento de la temperatura y presion por ende no llega cantidad de gas suficiente en el evaporador como para generar el frio suficiente. " "Verificar el correcto funcionamiento del forzador y en caso de deterioro reemplazarlo"]
["Hermeticidad inadecuada de la puerta y/o gabinete " "La hermeticidad inadecuada de la puerta y/o el gabinete genera aumento de temperatura en el gabinete " "Chequear que el burlete este sellando correctamente el gabinete y en caso de daño reemplazarlo. Es posible que deba regularse la poscion de la puerta"]
["Localización inadecuada del equipo " " La falta de circulación de aire impide el intercambio de calor del condensador con el ambiente produciendo un bajo rendimiento del sistema de refrigeracion " "Cambiar la ubicación del equipo"]
["Gabinete utilizado en exceso y/o excesivas aperturas de puertas " "El exceso y/o excesivas aperturas de puertas aumenta la temperatura del gabinete. " "Comunicar al usuario el uso inadecuado de dicho exceso."]
["Deterioro o falta de aislación térmica " "Es producida por un deterioro en la asilacion termica lo cual reduce el enfriamiento del gabinete retirando calor del ambiente ya que no posee aislacion termica " "Reemplazar la aislancion termica"]
["Exceso de carga de fluido refrigerante (aumento de las presiones de baja y alta) " "El exceso de gas produce un muy bajo rendimiento del sistema " "Verificar (quitar) las presiones de gas del sistema"]
["Falta de fluido refrigerante (verificar sobrecalentamiento) " "La falta de gas produce un muy bajo rendimiento del sistema " "Verificar (cargar) las presiones de gas del sistema"]
["Sistema contaminado (aumento de la presión de alta) " "si el sistema se encuentra contaminado el gas pierde sus propiedades termodinamicas generando presiones incorrectas, en consecuencia el evaporador tambien tiene una presion de alta y por ende una presion y temperatura alta como para retirar frio. " "Reemplazar la carga completa de gas previa limpieza del circuito y vacio del mismo."]
["Motocompresor inadecuado al sistema " "Un motocompresor inadecuado al sistema produce un muy bajo rendimiento " "Verificar compatibilidad del motocompresor con el sistema"]
["Motocompresor con baja capacidad (gastado) " "Un motocompresor con baja capacidad (gastado) genera un bajo rendimiento " "Verificar que las presiones del sistema respondan adecuadamente. En caso de ser asi reemplazar el motocompresor"]
["Forzador del evaporador en mal estado " "El forzador (ventilador) del evaporador dañado impide que el frio se distribuya en el gabinete " "Chaquear la conexión electrica del forzador, y en caso de detectar que el mismo se encuentra dañado reemplazarlo por uno nuevo"]
["Formación de hielo en el evaporador " "La formacion de hielo en el evaporador se produce por falta de gas en el circuito " "Verificar la carga de gas del equipo y en caso de falta del mismo completarla con la cantidad correspondiente."]
["Suciedad en el filtro de refrigerante " "Si el filtro se encuentra sucio se genera una obstruccion produciendo una falla en las presiones de alta y baja y en consecuencia el equipo enfrio poco. " "Reemplazar el filtro de la valvula de expansion"]
["Bulbo de la válvula de expansión roto (excesivo sobrecalentamiento) " "El bulbo es el encargado de introducir el líquido refrigerante " "Reemplazar bulbo"]
["Bulbo de la válvula de expansión suelto (excesivo sobrecalentamiento) " "El bulbo es el encargado de introducir el líquido refrigerante " "Corregir colocación bulbo"]
]

[2 4 2] [
["Condensador mal instalado – caños metálicos en contacto " "Un condensador mal instalado o con caños metálicos en contacto generan ruidos por vibracion " "Verificar contactos metalico para evitar vibraciones"]
["Desbalanceo del forzador " "Si el forzador no se encuentra correctamente fijado o tiene algun desbalanceo en su mecanismo puede generar ruidos en el equipo " "Verificar el estado y correcto funcionameinto del forzador, en caso de deterioro reempolazarlo."]
["Nivelación incorrecta del equipo o de la base del mocompresor " "La nivelación incorrecta del equipo o de la base del mocompresor produce vibracion interna en el motocompresor " "Verificar el nivel correcto del equipo y del motocompresor, corregir de ser necesario"]
["Colocación inadecuada del motocompresor " "Colocación inadecuada del motocompresor genera vibracion. " "Verificar nivel y fijacion del mismo y corregirlo."]
["Motocompresor con ruido interno " "Deterioro interno en la fijacion de sus componentes genera vibraciones " "Cambiar motocompresor"]
["Falta de aceite en el motocompresor " "La falta de aceite en el compresor hace que los mecanismos no se lubriquen correctammente produciendo ruidos. " "Agregar aceite al motocompresor."]
]

[2 10 2] [
["Obstrucción parcial de la cañería " "Un obstruccion parcial de la cañeria genera un exceso en la presion de alta aumentando el consumo de corriente que es detectado por el protecto termico " "Realizar una deshidratacion del sistema mediante bomba de vacio para eliminar la humedad. Luego debe recargase el sistema con gas por completo"]
["Condensador sucio o con falta de circulación de aire " "El condensador sucio o con falta de circulación de aire produce que la presion de alta del sistema sea mas elevada que la normal generando un mayor consumo de corriente que es detectado por el protecto termico " "Limpiar el condensador o cambiar la ubicación del equipo"]
["Forzador del condensador en mal estado (aumento de la presión de alta) " "Al no funcionar el forzador del condensador este no puede retirar calor hacia el ambiente en forma correcta, produciendo un aumento de la temperatura y presion, asi como un consumo elevado de corriente que es detectado por el protector termico produciendo un corte de proteccion. " "Verificar el correcto funcionamiento del forzador y en caso de deterioro reemplazarlo"]
["Localización inadecuada del equipo " " La falta de circulación de aire impide el intercambio de calor del condensador con el ambiente produciendo un alto consumo de corritente que es detectado por el protector termico " "Cambiar la ubicación del equipo"]
["Exceso de carga de fluido refrigerante (aumento de las presiones de baja y alta) " "El exceso de gas produce un consumo mas elevado de corriente que lo normal que es detectado por el protector  " "Verificar presiones de gas en el sistema."]
["Sistema contaminado (aumento de la presión de alta) " "Si el sistema se encuentra contaminado el gas pierde sus propiedades termodinamicas generando presiones incorrectas,  lo cual produce un exceso de trabajo generando un alto consumo de corriente que es detectado por el protector termico generando un corte. " "Reemplazar la carga completa de gas previa limpieza del circuito y vacio del mismo."]
["Motocompresor inadecuado al sistema " "Un motocompresor inadecuado al sistema genera un consumo elevado de corriente que es detectado por el protector termico " "Reemplazar el motocrompresor por el adecuado"]
["Motocompresor deteriorado mecánicamente " "Un motocompresor dañado mecanicamente genera un consumo elevado de corriente que es detectado por el protector termico " "Reemplazar el motocrompresor"]
]

[3 1 1] [
["Error en la conexión electrica " "Puede ocurrir que haya cables deteriorados en contacto que produzan un puente entre componentes produciendo un enfriamento continuo en el equipo. " "Verificar estado del circuito electrico del sistema"]
["Termostato no desconecta " "Un termostato dañado puede ocurrir que no desconecte haciendo que el motocompresor funcione constantemente y enfrie mucho " "El termostato debe ser reemplazado"]
["Termostato regulado en la posición máxima " "Un termostato regulado en posicion maxima puede generar que el equipo enfrie demasiado. " "Regular la posicion de la perilla del termostato para evitar que enfrie mucho"]
["Termostato con bulbo suelto " "En casos en que el bulbo se encuentre suelto puede comar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato con bulbo fuera de la posición original " "En casos en que el bulbo se encuentre fuera de la posicion correcta puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "El termostato inadecuado puede trabajar sobre un rango de temperaturas inadecuado al sistema, produciendo que el equipo enfrie mucho. " "El termostato trabaja en un rango de temperaturas inadecuado al sistema, debe ser reemplazado por el correspondiente al equipo."]
["Plaqueta electrónica deteriorada (sensores de temperatura, etc) " "Un deterioro en la plaqueta electronica puede generar que alguno de sus componentes no funcione correctamente, como puede ser el sensor de temperatura, trayendo como consecuencia exceso o escasez de frio " "Controlar estado de la placa electronica y en caso de detectar falla sustituirla por una nueva."]
]

[3 2 1] [
["Termostato regulado en la posición mínima " "Perilla del termostato en posicion minima " "Aumentar a una posicion mayor"]
["Termostato con bulbo fuera de la posición original " "El bulbo se encuentra en una posicion incorrecto en la cual le lleva mas frio del que es debido " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "El termostato trabaja en un rango de temperaturas inadecuado al sistema " "Debe ser reemplazado por el correspondiente al equipo."]
["Plaqueta electrónica deteriorada (sensores de temperatura, etc) " "Un deterioro en la plaqueta electronica puede generar que alguno de sus componentes no funcione correctamente, como puede ser el sensor de temperatura, trayendo como consecuencia exceso o escasez de frio " "Controlar estado de la placa electronica y en caso de detectar falla sustituirla por una nueva."]
]

[3 3 1] [
["Mala aislación de los cables " "La aislacion de algun cable se encuentra dañana " "Revisar la asilacion de la conexión electrica"]
["Cableado o componentes eléctricos en contacto con partes metálicas " "La aislacion de algun cable se encuentra dañana o componente en contacto con parte metalica del equipo " "Revisar la asilacion de la conexión electrica y componentes"]
["Componente con Fuga de corriente (termostato, portalámparas, etc) " " componente en contacto con parte metalica del equipo " "Revisar posicion de los componentes"]
]

[3 6 1] [
["Forzador del evaporador (si posee) no funciona " "Si el forzador del evaporador no funciona se acumulará el frio produciendo escarcha en la parte interna del gabinete " "Chaquear la conexión electrica del forzador, y en caso de detectar que el mismo se encuentra dañado reemplazarlo por uno nuevo"]
["Termostato no desconecta " "Un termostato dañado puede ocurrir que no desconecte haciendo que el motocompresor funcione constantemente y enfrie mucho produciendo escarcha en la parte interna del gabinete " "El termostato debe ser reemplazado"]
["Error en la conexión electrica " "Un error en la conexión electrica puede ocasionar que componentes trabajen continuamente generando un exceso de frio y en consecuencia escarcha en el gabiente " "Verificar estado del circuito electrico del sistema"]
["Plaqueta electrónica deteriorada (sensores de temperatura, etc) " "Un deterioro en la plaqueta electronica puede generar que alguno de sus componentes no funcione correctamente, como puede ser el sensor de temperatura, trayendo como consecuencia exceso de frio y formacion de escarcha en la parte interna del gabinete " "Controlar estado de la placa electronica y en caso de detectar falla sustituirla por una nueva."]
]

[3 7 1] [
["Error en la conexión electrica " "Daño enla conexión electrica " "Verificar conexión electrica y aislacion de la misma"]
["Termostato no desconecta " "Consumo continua de corriente del motocompresor " "dañado el termostato, debe ser reemplazado"]
["Termostato regulado en la posición máxima " "El termostato en posicion maxima genera que el motocompresor trabaje continuamente " "Regular la poscion de la perilla del termostato para evitar que enfrie mucho"]
["Termostato con bulbo suelto " "En casos en que el bulbo se encuentre suelto puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Termostato inadecuado " "En casos en que el bulbo se encuentre fuera de la posicion correcta puede tomar una temperatura incorrecta de corte generar un exceso de enfriamiento " "colocar el bulbo en la posicion original (final del evaporador)"]
["Forzador del evaporador (si posee) no funciona " "Si el forzador del evaporador no funciona se produce un exceso de trabajo del equipo generando un alto consumo de corriente " "Chaquear la conexión electrica del forzador, y en caso de detectar que el mismo se encuentra dañado reemplazarlo por uno nuevo"]
["Motocompresor con alto amperaje (corriente elevada) " "Problema electrico de tension en el toma corriente o sistema electrico de motocompresor dañado " "Verificar tension en toma corriente (reestablecer a 220v)/Si el sistema electrico del motocompresor esta dañado debe reemplazarse"]
["Plaqueta electrónica deteriorada (sensores de temperatura, etc) " "Un deterioro en la plaqueta electronica puede generar que alguno de sus componentes no funcione correctamente, como puede ser el sensor de temperatura, trayendo como consecuencia un alto consumo de corriente " "Controlar estado de la placa electronica y en caso de detectar falla sustituirla por una nueva."]
]

[3 8 1] [
["Falta de tensión en la alimentación principal del equipo " "Una falla o mala instalacion del tomacorriente puede generar que el mismo no tenga corriente y como consecuencia el equipo no funcione " "Verificar el funcionamiento del tomacorriente"]
["Cable de línea o cableado interrumpido " "Una interrupcion en el cable de la linea y cableado del sistema puede impedir la llegada de corriente al equipo. " "Verificar el cableado de la linea y del equipo."]
["Error en la conexión electrica " "Un error en la conexión electrica produce que el equipo no encienda " "Verificar que la conexión electrica sea correcta, en tal caso corregir el error."]
["Componentes eléctricos que no permiten el paso de corriente al motocompresor " "Un componente defectuoso no permite el paso de corriente al motocompresor " "Verificar componentes electricos"]
["Termostato desconectado " "Termostato desconectado " "Revisar conexión electrico del termostato"]
["Termostato sin pasaje de corriente por los contactos " "Contactos deteriorados o sulfatados en el termostato " "Limpiar contactos de componentes"]
["Protector térmico abierto " "El protector termico dañado impide el paso de corriente al motocompresor " "Reemplazar el protector termico"]
["Bobinados del motor del motocompresor defectuosos " "Bobinado dañado impide el funcionamiento del motocompresor " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
["Plaqueta electrónica deteriorada (sensores de temperatura, etc) " "Un deterioro en la plaqueta electronica puede generar que alguno de sus componentes no funcione correctamente, impidiendo que el motocompresor arranque. " "Controlar estado de la placa electronica y en caso de detectar falla sustituirla por una nueva."]
["Control remoto deteriorado o con pilas descargadas " "Si el control remoto se encuentra dañado o con pilas descargadas el equipo no recibira señales del mismo, con lo cual no entrará en funcionamiento " "Corroborar estado de las pilas y correcto funcionamiento del contro remoto"]
["Receptor de la señal deteriorado " "En caso que el receptor de señal se encuentre deteriorado no podra recibir la señal del control remoto, con lo cual el equipo no se pondra en funcionamiento " "Verificar el funcionamiento del receptor de señal y en caso de deterioro reemplazarlo"]
]

[3 9 1] [
["Muy baja tensión " "La baja tension de linea produce un alto consumo de corriente que es detectado por el protector termico y este corta el paso de corriente protegiendo al motocompresor " "Verificar la tension en la linea y reestablecerla a 220v"]
["Protector térmico defectuoso " "El protector termico incorrecto al sistema " "Sustituir el protector termico por el correcto"]
["Capacitor de arranque incorrecto " "El capacitor de arranque incorrecto no genera la suficiente potencia para que arranque el motocompresor " "Reemplazar el capacitor por el que el sistema necesita"]
["Capacitor de arranque deteriorado " "El capacitor de arranque abierto o dañado no permite el paso de corriente " "Reemplazare el capacitor dañado"]
["Capacitor de marcha (trabajo) deteriorado o con capacidad incorrecta " "el capacitor de trabajo deteriorado produce un desfasaje erroneo en los campos magneticos de las bobinas del motocompresor produciendose un mayor coinsumo que es detectado por el protector termico " "Reemplazar el capacitor de trabajo."]
["Motocompresor conectado a un voltaje diferente a lo especificado " "El voltaje incorrecto impide que el motocompresor no arranque produciendose un alto consumo de corriente que es detectado por el protector termico " "Verificar y corregir voltaje en la linea"]
["Bobinados del motor del motocompresor defectuosos " "Bobinado dañado impide el funcionamiento del motocompresor " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
]

[3 10 1] [
["Muy baja tensión " "La baja tension es compensada por un alto consumo de corriente detectado por el protector termico interrumpìendo el paso de corriente. " "Verificar la tension en la linea y reestablecerla a 220v"]
["Protector térmico defectuoso " "Un protector termico incorrecto deja pasar una corriente menor a la que requiere el sistema " "Reemplazar protector termico por el adecuado al sistema "]
["No abre el relé voltimétrico y se recalienta el capacitor electrolítico " "Al no abrir el rele voltimetrico se recalienta el capacitor electrolitico y hace que el motocompresor funcione solo con las bobinas de trabajo, mientras que las bobinas de arranque no se acomplan forzando el trabajo del motocompresor, por ende se produce un mayor consumo que hace que el protector termino salte. " "Reemplazar el rele voltimetrico"]
["Capacitor de marcha (trabajo) deteriorado o con capacidad incorrecta " "el capacitor de trabajo deteriorado produce un desfasaje erroneo en los campos magneticos de las bobinas del motocompresor que es detectado por el protector termico " "Reemplazar el capacitor de trabajo."]
["Motocompresor conectado a un voltaje diferente a lo especificado " "El voltaje incorrecto deja al motocompresor arrancar pero produciendose un alto consumo de corriente que es detectado por el protector termico " "Verificar y corregir voltaje en la linea"]
["Bobinados del motor del motocompresor defectuosos " "Bobinado dañado produce un funcionamiento inadecuado del motocompresor elevando el consumo de corriente que es detectado por el protector termico. " "Chequear las bobinas del motocompresor con un tester para verificar daño del mismo. Reemplazar el motocompresor"]
["Motocompresor con alto amperaje (corriente elevada) " "Problema electrico de tension en el toma corriente o sistema electrico de motocompresor dañado " "Verificar tension en toma corriente (reestablecer a 220v)/Si el sistema electrico del motocompresor esta dañado debe reemplazarse"]
]

[3 2 2] [
["Obstrucción parcial de la cañería " "Humedad, polvillo o caño acotado produce una obstruccion parcial de la cañeria que produce un bajo rendimiento del sistema de refrigeracion " "Revisar cañeria del sistema dañada del sistema y verificar temperaturas de condesador y filtro para detectar una falsa evaporacion"]
["Condensador sucio o con falta de circulación de aire " "Condensador sucio o con falta de circulación de aire impide el intercambio de calor del condensador con el ambiente  " "Limpiar el condensador o cambiar la ubicación del equipo"]
["Forzador del condensador en mal estado (aumento de la presión de alta) " "Al no funcionar el forzador del condensador este no puede retirar calor hacia el ambiente en forma correcta, produciendo un aumento de la temperatura y presion por ende no llega cantidad de gas suficiente en el evaporador como para generar el frio suficiente. " "Verificar el correcto funcionamiento del forzador y en caso de deterioro reemplazarlo"]
["Localización inadecuada del equipo " " La falta de circulación de aire impide el intercambio de calor del condensador con el ambiente produciendo un bajo rendimiento del sistema de refrigeracion " "Cambiar la ubicación del equipo"]
["Deterioro o falta de aislación térmica " "Es producida por un deterioro en la asilacion termica lo cual reduce el enfriamiento del gabinete retirando calor del ambiente ya que no posee aislacion termica " "Reemplazar la aislancion termica"]
["Equipo subdimensionado (pequeño) para el ambiente a climatizar " "Un equipo que no posea las calorias necesarias para el tipo de ambiente en el que fue instalado no logrará enfriar lo deseado. " "Verificar que el equipo sea el indicado para ese tipo de ambiente"]
["Exceso de carga de fluido refrigerante (aumento de las presiones de baja y alta) " "El exceso de gas produce un muy bajo rendimiento del sistema " "Verificar (quitar) las presiones de gas del sistema"]
["Sistema contaminado (aumento de la presión de alta) " "si el sistema se encuentra contaminado el gas pierde sus propiedades termodinamicas generando presiones incorrectas, en consecuencia el evaporador tambien tiene una presion de alta y por ende una presion y temperatura alta como para retirar frio. " "Reemplazar la carga completa de gas previa limpieza del circuito y vacio del mismo."]
["Motocompresor inadecuado al sistema " "Un motocompresor inadecuado al sistema produce un muy bajo rendimiento " "Verificar compatibilidad del motocompresor con el sistema"]
["Motocompresor con baja capacidad (gastado) " "Un motocompresor con baja capacidad (gastado) genera un bajo rendimiento " "Verificar que las presiones del sistema respondan adecuadamente. En caso de ser asi reemplazar el motocompresor"]
["Falta de fluido refrigerante (verificar sobrecalentamiento) " "La falta de gas produce un muy bajo rendimiento del sistema " "Verificar (cargar) las presiones de gas del sistema"]
["Forzador del evaporador en mal estado " "El forzador (ventilador) del evaporador dañado impide que el frio se distribuya en el gabinete " "Chaquear la conexión electrica del forzador, y en caso de detectar que el mismo se encuentra dañado reemplazarlo por uno nuevo"]
["Formación de hielo en el evaporador " "La formacion de hielo en el evaporador se produce por falta de gas en el circuito " "Verificar la carga de gas del equipo y en caso de falta del mismo completarla con la cantidad correspondiente."]
["Suciedad en el filtro de refrigerante " "Si el filtro se encuentra sucio se genera una obstruccion produciendo una falla en las presiones de alta y baja y en consecuencia el equipo enfrio poco. " "Reemplazar el filtro de la valvula de expansion"]
]

[3 4 2] [
["Condensador mal instalado caños metálicos en contacto." "Un condensador mal instalado o con caños metálicos en contacto generan ruidos por vibracion " "Verificar contactos metalico para evitar vibraciones"]
["Desbalanceo del forzador" "Un forzador mal balanceado genera ruidos por vibración." "Balancee el forzador."]
["Nivelación incorrecta del equipo o de la base del mocompresor " "La nivelación incorrecta del equipo o de la base del mocompresor produce vibracion interna en el motocompresor " "Verificar el nivel correcto del equipo y del motocompresor, corregir de ser necesario"]
["Ruidos provocados por otros componentes estructura del equipo floja o mal colocada, falta de tornillos, etc" "Un mal ensamblado de las piezas del equipo o falta de ajuste a sus piezas puede generar ruidos y vibraciones en el equipo. " "Chequear el correcto ensamble del equipo, falta de tornillos o piezas, etc."]
["Motocompresor con ruido interno " "Deterioro interno en la fijacion de sus componentes genera vibraciones " "Cambiar motocompresor"]
["Falta de aceite en el motocompresor " "La falta de aceite en el compresor hace que los mecanismos no se lubriquen correctammente produciendo ruidos. " "Agregar aceite al motocompresor."]
]

[3 11 2] [
["Equipo mal nivelado " "El equipo carece de la nivelación recomendada " "Nivelar el equipo según recomendación del fabricante."]
["Obstrucción o estrangulación del desagüe de la unidad interior " "Una obstrucción en el desague evita que el equipo libere los excedentes de líquidos por las vías apropiadas. " "Corregir obstrucción o reemplazar mecanismo de desague."]
]

[3 12 2] [
["Obstrucción parcial de la cañería " "Humedad, polvillo o caño acotado produce una obstruccion parcial de la cañeria produciendo exceso de frio en el evaporador y formacion de escarcha " "Revisar cañeria del sistema dañada del sistema y verificar temperaturas de condesador y filtro para detectar una falsa evaporacion"]
["Falta de fluido refrigerante (verificar sobrecalentamiento) " "La falta de gas produce excesivo frio en el evaporador generando hielo en el evaporador " "Verificar (cargar) las presiones de gas del sistema"]
["Forzador del evaporador en mal estado " "El forzador (ventilador) del evaporador dañado impide que el frio se distribuya generando la formacion de hielo en el evaporador " "Chaquear la conexión electrica del forzador, y en caso de detectar que el mismo se encuentra dañado reemplazarlo por uno nuevo"]
]

[3 7 2] [
["Condensador sucio o con falta de circulación de aire " "El condensador sucio o con falta de circulación de aire produce que la presion de alta del sistema sea mas elevada que la normal generando un mayor consumo de corriente " "Limpiar el condensador o cambiar la ubicación del equipo"]
["Forzador del condensador en mal estado (aumento de la presión de alta) " " " ""]
["Localización inadecuada del equipo " " La falta de circulación de aire impide el intercambio de calor del condensador con el ambiente produciendo un alto consumo de corritente para llegar a su regimen deseado " "Cambiar la ubicación del equipo"]
["Deterioro o falta de aislación térmica " "Es producida por un deterioro en la asilacion termica lo cual reduce el enfriamiento del gabinete retirando calor del ambiente ya que no posee aislacion termica " "Reemplazar la aislancion termica"]
["Exceso de carga de fluido refrigerante (aumento de las presiones de baja y alta) " "El exceso de gas produce un consumo mas elevado de corriente que lo normal " "Verificar presiones de gas en el sistema."]
["Sistema contaminado (aumento de la presión de alta) " "Si el sistema se encuentra contaminado el gas pierde sus propiedades termodinamicas generando presiones incorrectas, en consecuencia el evaporador tambien tiene una presion de alta y por ende una presion y temperatura alta como para retirar frio, lo cual produce un exceso de trabajo generando un alto consumo de corriente. " "Reemplazar la carga completa de gas previa limpieza del circuito y vacio del mismo."]
["Falta de fluido refrigerante (verificar sobrecalentamiento) " "La falta de gas produce un muy bajo rendimiento del sistema produciendo un alto consumo de corriente " "Verificar (cargar) las presiones de gas del sistema"]
["Motocompresor inadecuado al sistema " "Un motocompresor inadecuado al sistema genera un consumo elevado de corriente " "Reemplazar el motocrompresor por el adecuado"]
]

[3 9 2] ["Motocompresor deteriorado mecánicamente " "Un motocompresor dañado mecanicamente que no arranca genera un consumo elevado de corriente que es detectado por el protector termico (motocompresor clavado) " "Reemplazar el motocrompresor"]

[3 10 2] [
["Obstrucción parcial de la cañería " "Un obstruccion parcial de la cañeria genera un exceso en la presion de alta aumentando el consumo de corriente que es detectado por el protecto termico " "Realizar una deshidratacion del sistema mediante bomba de vacio para eliminar la humedad. Luego debe recargase el sistema con gas por completo"]
["Condensador sucio o con falta de circulación de aire " "El condensador sucio o con falta de circulación de aire produce que la presion de alta del sistema sea mas elevada que la normal generando un mayor consumo de corriente que es detectado por el protecto termico " "Limpiar el condensador o cambiar la ubicación del equipo"]
["Forzador del condensador en mal estado (aumento de la presión de alta) " "Al no funcionar el forzador del condensador este no puede retirar calor hacia el ambiente en forma correcta, produciendo un aumento de la temperatura y presion, asi como un consumo elevado de corriente que es detectado por el protector termico produciendo un corte de proteccion. " "Verificar el correcto funcionamiento del forzador y en caso de deterioro reemplazarlo"]
["Localización inadecuada del equipo " " La falta de circulación de aire impide el intercambio de calor del condensador con el ambiente produciendo un alto consumo de corritente que es detectado por el protector termico " "Cambiar la ubicación del equipo"]
["Exceso de carga de fluido refrigerante (aumento de las presiones de baja y alta) " "El exceso de gas produce un consumo mas elevado de corriente que lo normal que es detectado por el protector  " "Verificar presiones de gas en el sistema."]
["Sistema contaminado (aumento de la presión de alta) " "Si el sistema se encuentra contaminado el gas pierde sus propiedades termodinamicas generando presiones incorrectas, en consecuencia el evaporador tambien tiene una presion de alta y por ende una presion y temperatura alta como para retirar frio, lo cual produce un exceso de trabajo generando un alto consumo de corriente. " "Reemplazar la carga completa de gas previa limpieza del circuito y vacio del mismo."]
["Motocompresor inadecuado al sistema " "Un motocompresor inadecuado al sistema genera un consumo elevado de corriente que es detectado por el protector termico " "Reemplazar el motocrompresor por el adecuado"]
["Motocompresor deteriorado mecánicamente " "Un motocompresor dañado mecanicamente genera un consumo elevado de corriente que es detectado por el protector termico " "Reemplazar el motocrompresor"]
]

})

;TODO: Descomentar para la entrega el metodo main
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
	(println (str f_Gotea ". otea agua hacia el interior del equipo."))
	(println (str f_HieloEvaporador ". Se forma hielo en el evaporador."))
	(println (str opcion_salir ". Salir del sistema."))
	(def falla (read-line))
	(println (str "Opción seleccionada: " falla))
 
 (println "Las posibles causas y soluciones al problema son:")
 
 (defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))
 
 (base [(parse-int tipoDeEquipo) (parse-int tipoDeFalla) (parse-int falla)])
;)