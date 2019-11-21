# ORGA PRÁCTICA 4-GRUPO 5: VIBORITA SERIAL :video_game::snake: 
## Explicación de Puerto Paralelo en Modo Serial 🔧  

El puerto paralelo funciona para la comunicación entre dispositivos electrónicos, permitiendo tanto la transmisión y recepción de bytes por pines específicos distribuidos paralelamente.

Se simuló un funcionamiento serial utilizando unicamente un pin para la transmisión de datos, un pin para la recepción de datos y un pin cuyo funcionamiento fue la transmisión constante de :heavy_plus_sign: HIGH y :heavy_minus_sign: LOW con el fin de simular un :clock3: clock u oscilador.

### Distribución de pines :traffic_light:
* **Pines 1, 14, 17 y 16:** Pines de control (No utilizados durante el desarollo de la práctica).
* **Pines 25, 24, 23, 23, 21, 20, 19 y 18**: Tierras o conexiones a masa (Cabe aclarar que se utilizan al alimentar un circuito junto a otra fuente externa, en los casos cuyo circuito se alimente con los puertos USB del Equipo no será necesario).
* **Pines 2, 3, 4, 5, 6, 7, 8, y 9**: Pines de Datos, utilizados para la transmisión de datos del software al circuito controlador de la matriz de leds proyectora. El puerto es capaz de enviar un byte completo de forma paralela distribuido por los pines de acuerdo a su ponderado, siendo el pin 2 la representación del bit menos significativo y siendo el pin 9 el bit más significativo del byte trasmitido.
* **Pines 15, 13, 12, 10 y 11**: Pines de Estado, utilizados para la recepción de datos de un control externo de juego. El puerto es capaz de recibir los 5 bits más significativos de un byte, siendo el pin 15 el menos significativo y asimismo el pin 11 el más significativo, tomando los 3 bits menos significativos como 0.

### Pulls para recepción de datos :trident:
La recepción de datos podrá cambiar su valor de entrada respecto a la configuración que utilizemos, en la mayoria de puertos de estado se configura un pull down con el fin de enviar :heavy_plus_sign: HIGH mediante un pulso y conectarlo a :heavy_minus_sign: LOW al no enviarse nada. Los Pull Up se utilizan unicamente para el pin 11, cuyo funcionamiento trabaja con lógica negativa.

## Construido con 🛠️

* :bookmark_tabs: [NetBeans] - Entorno de Desarrollo Integrado (IDE) utilizado.
* :hotsprings: [Java] - Lenguaje de Programación utilizado.
* :book: [Parport] - Librería para la comunicación del software con el puerto paralelo, tanto para la transmisión como la recepción de bytes.  

## Autores ✒️

* **Cristian Suy** - *[Coordinador] 201700918* - [CSuy](https://github.com/CSuy)
* **Yelstin de León** - *201602836* - [airton47](https://github.com/airton47)
* **Ricardo Pérez** - *201700524* - [Ricardo16X](https://github.com/Ricardo16X)
* **Byron Gómez** - *201700544* - [ByrCas](https://github.com/ByrCas)
* **Andrea Saenz** - *201503484* - [andreadsaenz](https://github.com/andreadsaenz)
* **Juan Pablo Alvarado** - *201700511* - [201700511](https://github.com/201700511)
