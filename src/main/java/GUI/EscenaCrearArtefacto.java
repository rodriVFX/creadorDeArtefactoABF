package GUI;

import Artefactos.Artefacto;
import Artefactos.CreadorArtefacto;
import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;
import Datos.RepositorioDatos;
import Datos.TipoContenedorEnum;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;
import Poderes.PoderBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EscenaCrearArtefacto {

    private String nombreTipo(TipoContenedorEnum tipo) {
        return switch (tipo) {
            case OBJETO -> "Objeto";
            case ARMAMELEE -> "Arma cuerpo a cuerpo";
            case ARMALANZABLE -> "Arma lanzable";
            case ARMAPROYECTILES -> "Arma de proyectiles";
            case ESCUDO -> "Escudo";
            case ARMADURA -> "Armadura";
            case MUNICION -> "Munición";
            case TATUAJE -> "Tatuaje";
        };
    }
    RepositorioDatos datos = new RepositorioDatos();
    CreadorArtefacto creadorArtefacto = new CreadorArtefacto();
    SeleccionMateriasPrimas matPrimas = new SeleccionMateriasPrimas(datos);
    SeleccionPoderes poderes = new SeleccionPoderes(datos);

    public Scene crear() {

        Label titulo = new Label("Constructor de Artefactos");

        TextField campoNombre = new TextField();
        campoNombre.setPromptText("Nombre del artefacto");

        ComboBox<TipoContenedorEnum> comboTipoContenedor = new ComboBox<>();
        for (TipoContenedorEnum tipo : TipoContenedorEnum.values()){
            comboTipoContenedor.getItems().add(tipo);
        }
        comboTipoContenedor.getItems().sort(Comparator.comparing(TipoContenedorEnum::toString));
        comboTipoContenedor.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(TipoContenedorEnum tipo, boolean empty) {
                super.updateItem(tipo, empty);

                if (empty || tipo == null) {
                    setText(null);
                } else {
                    setText(nombreTipo(tipo));
                }
            }
        });
        comboTipoContenedor.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(TipoContenedorEnum tipo, boolean empty) {
                super.updateItem(tipo, empty);

                if (empty || tipo == null) {
                    setText(null);
                } else {
                    setText(nombreTipo(tipo));
                }
            }
        });

        comboTipoContenedor.setPromptText("Selecciona el tipo de contenedor");

        ComboBox<Contenedor> comboContenedor = new ComboBox<>();

        comboTipoContenedor.setOnAction(event-> {
                    TipoContenedorEnum tipo = comboTipoContenedor.getValue();

                    comboContenedor.getItems().clear();

                    comboContenedor.getItems().addAll(switch (tipo) {
                                case OBJETO -> datos.getObjetos().listar().values();
                                case ARMAMELEE -> datos.getArmasMelee().listar().values();
                                case ARMALANZABLE -> datos.getArmasLanzables().listar().values();
                                case ARMAPROYECTILES -> datos.getArmasProyectiles().listar().values();
                                case ESCUDO -> datos.getArmasMelee().listar().values();
                                case ARMADURA -> datos.getArmaduras().listar().values();
                                case MUNICION -> datos.getMuniciones().listar().values();
                                case TATUAJE -> datos.getTatuajes().listar().values();
                            }
                    );
            comboContenedor.getItems().sort(Comparator.comparing(Contenedor::getNombre));
                });

        comboContenedor.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(Contenedor contenedor, boolean empty){
                super.updateItem(contenedor, empty);
                if(empty || contenedor == null){
                    setText(null);
                }
                else{
                    setText(contenedor.getNombre());
                }
            }
        });
        comboContenedor.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(Contenedor contenedor, boolean empty){
                super.updateItem(contenedor, empty);
                if(empty || contenedor == null){
                    setText(null);
                }
                else{
                    setText(contenedor.getNombre());
                }
            }
        });

        comboContenedor.setPromptText("Selecciona un contenedor");

        ComboBox<Material> comboMaterial = new ComboBox<>();
        comboContenedor.setOnAction(event -> {
            comboMaterial.getItems().clear();

            if(comboTipoContenedor.getValue() != TipoContenedorEnum.TATUAJE){
                comboMaterial.getItems().addAll(datos.getArtefacto().listarMateriales().values());
            }
            else{
                comboMaterial.getItems().add(datos.getArtefacto().getMaterial("Piel"));
            }
            comboMaterial.getItems().sort(Comparator.comparing(Material::getNombre));
        });
        comboMaterial.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(Material material, boolean empty){
                super.updateItem(material, empty);
                if(empty || material == null){
                    setText(null);
                }
                else{
                    setText(material.getNombre());
                }
            }
        });
        comboMaterial.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(Material material, boolean empty){
                super.updateItem(material, empty);
                if(empty || material == null){
                    setText(null);
                }
                else{
                    setText(material.getNombre());
                }
            }
        });
        comboMaterial.setPromptText("Selecciona un material");

        ComboBox<Calidad> comboCalidad = new ComboBox<>();
        comboMaterial.setOnAction(event -> {
            comboCalidad.getItems().clear();

            if(comboTipoContenedor.getValue() != TipoContenedorEnum.TATUAJE){
                comboCalidad.getItems().addAll(datos.getArtefacto().listarCalidades().values());
            }
            comboCalidad.getItems().sort(Comparator.comparing(Calidad::getNombre));
        });
        comboCalidad.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(Calidad calidad, boolean empty){
                super.updateItem(calidad, empty);
                if(empty || calidad == null){
                    setText(null);
                }
                else{
                    setText(calidad.getNombre());
                }
            }
        });
        comboCalidad.setButtonCell(new ListCell<>(){
            @Override
            protected void updateItem(Calidad calidad, boolean empty){
                super.updateItem(calidad, empty);
                if(empty || calidad == null){
                    setText(null);
                }
                else{
                    setText(calidad.getNombre());
                }
            }
        });
        comboCalidad.setPromptText("Selecciona una calidad");

        CheckBox creadoParaArtefacto = new CheckBox("Creado específicamente para el artefacto");

        Button botonCrear = new Button("Crear artefacto");
        botonCrear.setOnAction(event ->{

            String nombre = campoNombre.getText();
            Contenedor contenedor = comboContenedor.getValue();
            Material material = comboMaterial.getValue();
            Calidad calidad = comboCalidad.getValue();
            boolean creado = creadoParaArtefacto.isSelected();


            Artefacto artefacto = creadorArtefacto.crearArtefacto(
                    nombre,
                    contenedor,
                    material,
                    calidad,
                    creado,
                    matPrimas.getMateriasSeleccionadas(),
                    poderes.getPoderesSeleccionados()
            );

            VentanaExportacion ventanaExportacion = new VentanaExportacion(artefacto);

            ventanaExportacion.mostrar(
                    (Stage) botonCrear.getScene().getWindow()
            );
        });

        VBox root = new VBox(10);

        root.getChildren().addAll(
                titulo,
                campoNombre,
                comboTipoContenedor,
                comboContenedor,
                comboMaterial,
                comboCalidad,
                creadoParaArtefacto,
                matPrimas.getVista(),
                poderes.getVista(),
                botonCrear
        );

        return new Scene(root, 900, 700);
    }
}
