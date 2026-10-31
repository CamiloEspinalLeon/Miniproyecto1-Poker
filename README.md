# Miniproyecto1-Poker

Proyecto de juego de cartas desarrollado en Java con JavaFX, basado en las reglas de Blackjack / Veintiuno (21). El repositorio incluye la lógica del juego, manejo de mazos, cartas, apuestas, decisiones de la computadora y una interfaz gráfica con archivos FXML.

## Descripción

Este proyecto implementa una versión de casino tipo blackjack en la que el usuario juega contra la computadora. La aplicación permite:
- barajar y repartir cartas
- gestionar apuestas
- determinar jugadas válidas
- reconocer blackjack y veinte y medio
- decidir cuándo la computadora debe pedir más cartas
- resolver la ronda final con lógica del ganador

## Tecnologías utilizadas

- Java 17
- Maven
- JavaFX
- FXML
- MVC (Modelo - Vista - Controlador)

## Estructura del proyecto

```text
Miniproyecto1-Poker/
├── .idea/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── co/edu/univalle/poe/mvc/
│   │   │       ├── BlackJackApplication.java
│   │   │       ├── Launcher.java
│   │   │       ├── controller/
│   │   │       │   └── BlackjackController.java
│   │   │       └── model/
│   │   │           ├── Apuesta.java
│   │   │           ├── Carta.java
│   │   │           ├── Mazo.java
│   │   │           └── Repartir.java
│   │   └── resources/
│   │       └── co/edu/univalle/poe/mvc/
│   │           ├── view/
│   │           │   ├── blackjack-view.fxml
│   │           │   └── reglas-view.fxml
│   │           └── images/
│   │               ├── cards/
│   │               └── apuestas.png
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── src.zip
