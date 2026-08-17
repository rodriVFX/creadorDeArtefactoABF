import GUI.EscenaCrearArtefacto;
import GUI.VentanaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Constructor de Artefactos");

        EscenaCrearArtefacto escenaCrearArtefacto = new EscenaCrearArtefacto();

        stage.setScene(escenaCrearArtefacto.crear());
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}