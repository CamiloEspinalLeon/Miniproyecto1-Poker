package co.edu.univalle.poe.mvc.controller;

import co.edu.univalle.poe.mvc.model.Carta;
import co.edu.univalle.poe.mvc.model.Repartir;
import javafx.fxml.FXML;

//imports para el boton de reglas/ la nueva ventana
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//imports para el botón de salir
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.LinkedList;

public class BlackjackController {

    private static final String RUTA_CARTAS = "/co/edu/univalle/poe/mvc/images/cards/";

    // --- Elementos de la vista (los ids están en el FXML) ---
    @FXML private HBox hboxCompu;
    @FXML private HBox hboxJugador;
    @FXML private ImageView compuCarta1, compuCarta2;
    @FXML private ImageView jugadorCarta1, jugadorCarta2, jugadorCarta3, jugadorCarta4;
    @FXML private Button btnIniciar, btnPedir, btnPlantar;
    @FXML private Label lblEstado;
    @FXML private Button btnReglas;

    private ImageView[] casillasCompu;
    private ImageView[] casillasJugador;
    private int hijosCompu;      // cuántos hijos tiene cada HBox al abrir la ventana
    private int hijosJugador;
    private Image reverso;       // blank.png
    private Repartir ronda;      // el modelo de la partida actual

    /** JavaFX la llama sola después de cargar el FXML. */
    @FXML
    public void initialize() {
        lblEstado.setWrapText(true);
        casillasCompu = new ImageView[]{compuCarta1, compuCarta2};
        casillasJugador = new ImageView[]{jugadorCarta1, jugadorCarta2, jugadorCarta3, jugadorCarta4};
        hijosCompu = hboxCompu.getChildren().size();
        hijosJugador = hboxJugador.getChildren().size();
        reverso = new Image(getClass().getResourceAsStream(RUTA_CARTAS + "blank.png"));

        btnPedir.setDisable(true);
        btnPlantar.setDisable(true);
    }

    // Botones

    @FXML
    private void onIniciarRonda() {
        ronda = new Repartir();                       // mazo nuevo + 2 cartas al jugador

        mostrarMano(hboxCompu, casillasCompu, hijosCompu, new LinkedList<>());   // compu sin cartas (blank)
        mostrarMano(hboxJugador, casillasJugador, hijosJugador, ronda.getMano());

        btnIniciar.setDisable(true);
        btnPedir.setDisable(false);
        btnPlantar.setDisable(false);
        lblEstado.setText("Tu turno. Puntaje: " + ronda.getPuntajeJugador());

        if (ronda.getPuntajeJugador() == 21) {        // 21 -> el turno termina solo
            onPlantarse();
        }
    }
    /** Botón SALIR. */
    @FXML
    private void onSalir() {
        if (confirmarSalida()) {
            Platform.exit();          // termina la aplicación
        }
    }

    //Pregunta al usuario si quiere cerrar el juego.
    // Es public porque también la usa HelloApplication para la X de la ventana.
    public boolean confirmarSalida() {
        ButtonType si = new ButtonType("Sí, cerrar", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.initOwner(hboxCompu.getScene().getWindow());   // la alerta queda sobre el juego
        alerta.setTitle("Cerrar juego");
        alerta.setHeaderText("¿Seguro que quieres cerrar el juego?");
        alerta.setContentText("La partida actual no se guardará.");
        alerta.getButtonTypes().setAll(si, cancelar);

        return alerta.showAndWait().orElse(cancelar) == si;
    }

    @FXML
    private void mostrarReglas(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/univalle/poe/mvc/view/reglas-view.fxml"));
            Stage ventanaReglas = new Stage();
            ventanaReglas.setScene(new Scene(loader.load()));
            ventanaReglas.setTitle("Reglas del Blackjack");
            ventanaReglas.initOwner(btnReglas.getScene().getWindow());   // pertenece a la ventana del juego
            ventanaReglas.setResizable(false);
            ventanaReglas.showAndWait();                                 // al cerrarla, se sigue donde se estaba
        } catch (IOException e) {
            lblEstado.setText("No se pudo abrir la ventana de reglas.");
            e.printStackTrace();
        }
    }


    @FXML
    private void onPedirCarta() {
        if (ronda == null || !ronda.pedirCarta()) {
            return;
        }
        mostrarMano(hboxJugador, casillasJugador, hijosJugador, ronda.getMano());

        int puntaje = ronda.getPuntajeJugador();
        if (puntaje > 21) {
            lblEstado.setText("Te pasaste con " + puntaje + ". ¡Pierdes!");
            terminarRonda();
        } else if (puntaje == 21) {
            onPlantarse();
        } else {
            lblEstado.setText("Tu turno. Puntaje: " + puntaje);
        }
    }

    @FXML
    private void onPlantarse() {
        if (ronda == null) {
            return;
        }

        ronda.Plantar();                                   // 1. el computador recibe sus 2 cartas
        mostrarMano(hboxCompu, casillasCompu, hijosCompu, ronda.getManoCompu());

        int jugador = ronda.getPuntajeJugador();
        int compu = ronda.getPuntajeCompu();
        String resultado;

        if (compu > 21 || jugador > compu) {               // 2. ahora sí se compara
            resultado = "¡GANASTE!";
        } else if (jugador < compu) {
            resultado = "PERDISTE";
        } else {
            resultado = "EMPATE";
        }

        lblEstado.setText("Te plantaste con " + jugador
                + ". Computadora: " + compu + ". " + resultado);
        terminarRonda();
    }

    // ===================== Ayudas =====================

    /** Deja la pantalla lista para otra ronda. */
    private void terminarRonda() {
        btnPedir.setDisable(true);
        btnPlantar.setDisable(true);
        btnIniciar.setDisable(false);
    }

    /**
     * Dibuja una mano en su HBox: primero la deja en blanco y luego pone
     * la imagen de cada carta. Si la mano tiene más cartas que casillas,
     * crea ImageViews nuevos justo después de la última casilla.
     */
    private void mostrarMano(HBox hbox, ImageView[] casillas, int hijosOriginales, LinkedList<Carta> mano) {
        // 1. Quitar las casillas extra que se hayan creado antes
        while (hbox.getChildren().size() > hijosOriginales) {
            hbox.getChildren().remove(casillas.length);
        }

        //2. En blanco

        for (ImageView casilla : casillas) {
            casilla.setImage(reverso);
        }
        // 3. Poner las cartas
        int i = 0;
        for (Carta carta : mano) {
            Image imagen = new Image(getClass().getResourceAsStream(RUTA_CARTAS + carta.getNombreImagen()));
            if (i < casillas.length) {
                casillas[i].setImage(imagen);
            } else {
                ImageView nueva = new ImageView(imagen);
                nueva.setFitHeight(150);
                nueva.setFitWidth(200);
                nueva.setPreserveRatio(true);
                HBox.setMargin(nueva, new Insets(20));
                hbox.getChildren().add(i, nueva);
            }
            i++;
        }
    }
}
