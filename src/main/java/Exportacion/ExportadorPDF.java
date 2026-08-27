package Exportacion;

import Artefactos.Artefacto;
import Contenedores.*;
import Contenedores.Interfaces.HaceDano;
import MateriasPrimas.MateriaPrima;
import Poderes.ModificadorPoder;
import Poderes.Poder;
import org.openpdf.text.Document;
import org.openpdf.text.Font;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportadorPDF {

    private final Font titulo = new Font(Font.HELVETICA, 24, Font.BOLD);
    private final Font encabezado = new Font(Font.HELVETICA, 16, Font.BOLD);
    private final Font texto = new Font(Font.HELVETICA, 12, Font.NORMAL);

    public void exportar(Artefacto artefacto, String ruta){
        try {
            Document documento = new Document();
            Contenedor contenedor = artefacto.getContenedor();
            //String output = "C:\\Users\\rodri\\Documents\\%s.pdf".formatted(ruta);

            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            documento.open();

            escribirCabecera(documento, artefacto.getNombre());
            escribirDatosBasicos(documento, artefacto);
            if (contenedor instanceof Arma || contenedor instanceof Municion) {
                escribirDatosArma(documento, artefacto);
            }
            escribirMateriasPrimas(documento, artefacto);
            escribirPoderes(documento, artefacto);
            escribirDescripcion(documento, artefacto);

            documento.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo crear el PDF.", e);
        }
    }

    private void escribirCabecera(Document documento, String nombre){
        documento.add(new Paragraph(nombre, titulo));
        documento.add(new LineSeparator());
    }
    private void escribirDatosBasicos(Document documento, Artefacto artefacto){
        documento.add(new Paragraph(artefacto.getContenedor().getNombre() + " (" + artefacto.getMaterial().getNombre() + " " + artefacto.getCalidad().getNombre() + ")"));
        documento.add(new LineSeparator());
    }
    private void escribirDatosArma(Document documento, Artefacto artefacto){
        Contenedor contenedor = artefacto.getContenedor();
        HaceDano haceDano = contenedor instanceof HaceDano hd ? hd : null;
        Arma arma = contenedor instanceof Arma a ? a : null;
        Equipamiento equipamiento = contenedor instanceof  Equipamiento e ? e : null;
        ArmaDistancia distancia = contenedor instanceof ArmaDistancia d ? d : null;

        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("Daño");
        tabla.addCell("Turno");
        tabla.addCell("FUE R.");
        tabla.addCell("Crítico 1");
        tabla.addCell("Crítico 2");

        if(haceDano != null) {
            tabla.addCell(String.valueOf(haceDano.getDano()));
        }
        else{tabla.addCell("-");}
        if(arma != null) {
            tabla.addCell(String.valueOf(arma.getVelocidad()));
            tabla.addCell(arma.getFuerzaRequerida());
        }
        else{
            tabla.addCell("-");
            tabla.addCell("-");
        }
        if(haceDano != null) {
            tabla.addCell(haceDano.getCriticoPrimario());
            tabla.addCell(haceDano.getCriticoSecundario());
        }
        else{
            tabla.addCell("-");
            tabla.addCell("-");
        }

        PdfPTable tabla2 = new PdfPTable(5);
        tabla2.addCell("Tipo de arma");
        tabla2.addCell("Especial");
        tabla2.addCell("Entereza");
        tabla2.addCell("Rotura");
        tabla2.addCell("Presencia");

        tabla2.addCell(artefacto.getContenedor().getTipo());
        String especialidades = String.join(", ", contenedor.getEspecialidades());
        tabla2.addCell(especialidades);
        if(equipamiento != null) {
                tabla2.addCell(String.valueOf(equipamiento.getEntereza()));
                tabla2.addCell(String.valueOf(equipamiento.getRotura()));
            }
        else{
                tabla2.addCell("-");
                tabla2.addCell("-");
            }
        tabla2.addCell(String.valueOf(artefacto.getContenedor().getPresenciaBase()));

        PdfPTable tabla3 = new PdfPTable(4);
        if(distancia != null){
            tabla3.addCell("Tipo");
            tabla3.addCell("Cadencia de fuego");
            tabla3.addCell("Recarga");
            tabla3.addCell("Alcance");

            if(contenedor instanceof ArmaLanzable lanzable){
                tabla3.addCell("Lanzable");
                tabla3.addCell(String.valueOf(lanzable.getCadenciaFuego()));
            }
            else{
                tabla3.addCell("Disparo");
                tabla3.addCell("-");
            }
            if(contenedor instanceof ArmaProyectiles disparo){
                tabla3.addCell(String.valueOf(disparo.getRecarga()));
            }
            else{
                tabla3.addCell("-");
            }
            tabla3.addCell(String.valueOf(distancia.getAlcance()));
        }

        documento.add(tabla);
        documento.add(tabla2);
        if(distancia != null){
            documento.add(tabla3);
        }
    }
    private void escribirMateriasPrimas(Document documento, Artefacto artefacto){
        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell("Fuente de poder");
        tabla.addCell("Cantidad");
        tabla.addCell("PPs");
        tabla.addCell("Nivel");

        Map<String, Integer> listaMat = new HashMap<>(contarMateriasPrimas(artefacto));
        for(MateriaPrima m : artefacto.getMateriasPrimas()){
            tabla.addCell(m.getNombre());
            tabla.addCell(String.valueOf(listaMat.get(m.getNombre())));
            tabla.addCell(String.valueOf(m.getNivelPP()));
            tabla.addCell(String.valueOf(m.getCantidadPP()));
        }
        documento.add(tabla);
    }
    private void escribirPoderes(Document documento, Artefacto artefacto){
        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("Poder");
        tabla.addCell("Variante");
        tabla.addCell("Modificadores");
        tabla.addCell("Coste");
        tabla.addCell("Nivel");

        for(Poder p : artefacto.getPoderes()){
            tabla.addCell(p.getNombre());
            tabla.addCell(p.getOpcion().getNombre());
            List<String> modificadores = new ArrayList<>();
            for(ModificadorPoder m : p.getModificadores()){
                modificadores.add(m.getNombre());
            }
            tabla.addCell(String.join(", ", modificadores));
            for(Map.Entry<Integer, Integer> m : p.getCostePP().entrySet()){
                tabla.addCell(String.valueOf(m.getKey()));
                tabla.addCell(String.valueOf(m.getValue()));
            }
        }
        documento.add(tabla);
    }
    private void escribirDescripcion(Document documento, Artefacto artefacto){
        descripcionArtefacto();
        PdfPTable tabla = new PdfPTable(2);
        for(Poder p : artefacto.getPoderes()){
            tabla.addCell(p.getNombre() + ": " + p.getOpcion().getNombre());
            tabla.addCell(p.getOpcion().getDescripcion());
        }
        documento.add(tabla);
    }

    private Map<String, Integer> contarMateriasPrimas(Artefacto artefacto){
        Map<String, Integer> listaMat = new HashMap<>();
        for(MateriaPrima m : artefacto.getMateriasPrimas()){
            listaMat.put(m.getNombre(), listaMat.getOrDefault(m.getNombre(), 0) + 1);
        }
        return listaMat;
    }
    private void descripcionArtefacto(){

    }

}
