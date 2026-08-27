import GUI.EscenaCrearArtefacto;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Constructor de Artefactos");

        EscenaCrearArtefacto escenaCrearArtefacto = new EscenaCrearArtefacto();

        stage.setScene(escenaCrearArtefacto.crear());
        stage.show();
    }

     static void main(String[] args) {
        Application.launch(args);
    }
}