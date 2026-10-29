package co.edu.univalle.poe.mvc;

import co.edu.univalle.poe.mvc.controller.BlackjackController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BlackJackApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BlackJackApplication.class.getResource("view/blackjack-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        BlackjackController controller = fxmlLoader.getController();

        // La X de la ventana: si el usuario cancela, consume() evita que se cierre
        stage.setOnCloseRequest(event -> {
            if (!controller.confirmarSalida()) {
                event.consume();
            }
        });


        stage.setTitle("21 Blackjack");
        stage.setScene(scene);
        stage.show();
    }
}
