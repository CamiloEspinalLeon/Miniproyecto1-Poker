# Miniproyecto1-Poker

Proyecto académico de juego de cartas desarrollado en Java con JavaFX, basado en la lógica del blackjack / veintiuno. El repositorio incluye la lógica del juego, el manejo del mazo y las cartas, el sistema de apuestas, la decisión automática de la computadora y la interfaz gráfica construida con FXML.

## Integrantes

- Camilo Espinal León

## Descripción del proyecto

Este proyecto implementa una versión de blackjack para jugar contra la computadora. La aplicación permite:

- barajar y repartir cartas
- evaluar puntajes
- reconocer jugadas especiales como blackjack y veinte y medio
- gestionar apuestas
- permitir que la computadora decida si pide o no otra carta
- resolver la ronda con la lógica del ganador
- mostrar la interfaz gráfica con un diseño MVC

## Tecnologías

- Java 17
- Maven
- JavaFX
- FXML
- MVC

## Estructura del repositorio

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
│   │               ├── apuestas.png
│   │               ├── cartatabla.png
│   │               ├── fichas.png
│   │               └── jugadatabla.png
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── src.zip
└── README.md
```

## Archivos principales

- `src/main/java/co/edu/univalle/poe/mvc/BlackJackApplication.java`: punto de entrada de la aplicación JavaFX.
- `src/main/java/co/edu/univalle/poe/mvc/controller/BlackjackController.java`: lógica de interacción entre la vista y el modelo.
- `src/main/java/co/edu/univalle/poe/mvc/model/Repartir.java`: lógica principal para repartir y resolver rondas.
- `src/main/java/co/edu/univalle/poe/mvc/model/Mazo.java`: gestión del mazo de cartas.
- `src/main/java/co/edu/univalle/poe/mvc/model/Carta.java`: representación de cada carta.
- `src/main/java/co/edu/univalle/poe/mvc/model/Apuesta.java`: manejo de apuestas.
- `src/main/resources/co/edu/univalle/poe/mvc/view/blackjack-view.fxml`: vista principal de la interfaz.
- `src/main/resources/co/edu/univalle/poe/mvc/view/reglas-view.fxml`: vista de reglas.
- `pom.xml`: configuración del proyecto Maven y JavaFX.

## Requisitos

- Java 17 o superior
- Maven
- Git
- Entorno compatible con JavaFX

## Ejecución

Clonar el repositorio:

```bash
git clone https://github.com/CamiloEspinalLeon/Miniproyecto1-Poker.git
cd Miniproyecto1-Poker
```

Ejecutar con Maven Wrapper:

```bash
./mvnw clean javafx:run
```

En Windows:

```bash
mvnw.cmd clean javafx:run
```

## Cómo jugar

1. Inicia la aplicación.
2. El usuario recibe dos cartas y la computadora también.
3. El jugador puede decidir apostar y continuar según la lógica del juego.
4. La computadora juega de acuerdo con la regla de pedir cartas hasta un punto límite.
5. El sistema determina el ganador y muestra el resultado final.

## Enlace del repositorio

https://github.com/CamiloEspinalLeon/Miniproyecto1-Poker

## Enlace al commit de entrega

El historial del proyecto se conserva sin reescrituras. La entrega final corresponde al siguiente commit:

https://github.com/CamiloEspinalLeon/Miniproyecto1-Poker/commit/0336227cbac1bb6ad9a21ec880b950b8ce2afb0f

Mensaje del commit:

> Nueva función resolverRonda en repartir, para que el modelo sea quien decida el ganador

## Nota para la docente

El repositorio está disponible en GitHub para revisión con acceso público vía el enlace anterior. Si se requiere compartirlo de forma privada o con permisos adicionales, puede gestionarse desde la configuración del repositorio.
