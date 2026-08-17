package GUI;

import javafx.stage.Stage;

public class VentanaPrincipal {
    private final Stage stage;

    public VentanaPrincipal(Stage stage){
        this.stage = stage;
    }

    public void mostrar(){
        stage.setTitle("Constructor de Artefactos");
        stage.setScene(new EscenaCrearArtefacto().crear());
        stage.show();
    }
}
