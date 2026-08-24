package GUI.ParteVentanaPrincipal;

import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;
import Datos.RepositorioDatos;
import Datos.TipoContenedorEnum;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.util.Comparator;

public class SeleccionContenedor {

    private RepositorioDatos datos = new RepositorioDatos();
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
    private Contenedor contenedor;
    private Material material;
    private Calidad calidad;

    public Node getVista(){
        VBox root = new VBox(10);

        ComboBox<TipoContenedorEnum> comboTipo = new ComboBox<>();
        for(TipoContenedorEnum tipo : TipoContenedorEnum.values()){
            comboTipo.getItems().add(tipo);
        }
        comboTipo.getItems().sort(Comparator.comparing(TipoContenedorEnum::toString));
        comboTipo.setCellFactory(param -> new ListCell<>() {
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
        comboTipo.setButtonCell(new ListCell<>() {
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
        comboTipo.setPromptText("Selecciona el tipo de contenedor");

        ComboBox<Contenedor> comboCont = new ComboBox<>();
        comboTipo.setOnAction(event -> {
            TipoContenedorEnum tipo = comboTipo.getValue();
            comboCont.getItems().clear();
            comboCont.getItems().addAll(switch(tipo){
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
            comboCont.getItems().sort(Comparator.comparing(Contenedor::getNombre));
        });
        comboCont.setCellFactory(param -> new ListCell<>(){
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
        comboCont.setButtonCell(new ListCell<>(){
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
        comboCont.setPromptText("Selecciona un contenedor");

        ComboBox<Material> comboMaterial = new ComboBox<>();
        ComboBox<Calidad> comboCalidad = new ComboBox<>();
        comboCont.setOnAction(event -> {
            contenedor = comboCont.getValue();
            comboMaterial.getItems().clear();
            comboCalidad.getItems().clear();

            if(comboTipo.getValue() != TipoContenedorEnum.TATUAJE){
                comboMaterial.getItems().addAll(datos.getArtefacto().listarMateriales().values());
            }
            else{
                comboMaterial.getItems().add(datos.getArtefacto().getMaterial("Piel"));
            }
            comboMaterial.getItems().sort(Comparator.comparing(Material::getNombre));

            if(comboTipo.getValue() != TipoContenedorEnum.TATUAJE){
                comboCalidad.getItems().addAll(datos.getArtefacto().listarCalidades().values());
            }
            comboCalidad.getItems().sort(Comparator.comparing(Calidad::getNombre));
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
        comboMaterial.setPromptText("Selecciona un material");
        comboCalidad.setPromptText("Selecciona una calidad");
        comboMaterial.setOnAction(event -> {
            material = comboMaterial.getValue();
        });
        comboCalidad.setOnAction(event -> {
            calidad = comboCalidad.getValue();
        });

        root.getChildren().addAll(
                comboTipo,
                comboCont,
                comboMaterial,
                comboCalidad
        );
        return root;
    }

    public Contenedor getContenedor(){
        return contenedor;
    }
    public Material getMaterial(){
        return material;
    }
    public Calidad getCalidad(){
        return calidad;
    }
}
