package Exportacion;

import Artefactos.Artefacto;
import Contenedores.*;
import Contenedores.Interfaces.HaceDano;
import MateriasPrimas.MateriaPrima;
import Poderes.ModificadorPoder;
import Poderes.Poder;
import org.openpdf.text.*;
import org.openpdf.text.Font;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.pdf.draw.LineSeparator;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportadorPDF {

    private final Font titulo = new Font(Font.HELVETICA, 24, Font.BOLD);
    private final Font encabezado = new Font(Font.HELVETICA, 16, Font.BOLD);
    private final Font texto = new Font(Font.HELVETICA, 12, Font.NORMAL);
    private final Font textoMarcado = new Font(Font.HELVETICA, 12, Font.BOLD);

    public void exportar(Artefacto artefacto, String ruta, String descripcion){
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
            descripcionArtefacto(documento, descripcion);

            documento.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo crear el PDF.", e);
        }
    }

    private void escribirCabecera(Document documento, String nombre){
        Paragraph texto = new Paragraph(nombre, titulo);
        texto.setAlignment(Element.ALIGN_CENTER);
        texto.setSpacingAfter(10);
        documento.add(texto);
        documento.add(new LineSeparator(0.5f, 60, Color.black, Element.ALIGN_CENTER, 0));
    }
    private void escribirDatosBasicos(Document documento, Artefacto artefacto){
        StringBuilder sb = new StringBuilder();
        if(artefacto.getContenedor().getTipo() != null){
            sb.append(artefacto.getContenedor().getTipo()).append(": ");
        }
        Paragraph texto = new Paragraph(sb + artefacto.getContenedor().getNombre() + " (" + artefacto.getMaterial().getNombre() + " " + artefacto.getCalidad().getNombre() + ")", encabezado);
        texto.setSpacingAfter(10);
        documento.add(texto);
        documento.add(new LineSeparator(1, 60, Color.black, Element.ALIGN_CENTER, 0));
    }
    private void escribirDatosArma(Document documento, Artefacto artefacto){
        Contenedor contenedor = artefacto.getContenedor();
        HaceDano haceDano = contenedor instanceof HaceDano hd ? hd : null;
        Arma arma = contenedor instanceof Arma a ? a : null;
        Equipamiento equipamiento = contenedor instanceof  Equipamiento e ? e : null;
        ArmaDistancia distancia = contenedor instanceof ArmaDistancia d ? d : null;

        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell(new Paragraph("Daño", encabezado));
        tabla.addCell(new Paragraph("Turno", encabezado));
        tabla.addCell(new Paragraph("FUE R.", encabezado));
        tabla.addCell(new Paragraph("Crítico 1", encabezado));
        tabla.addCell(new Paragraph("Crítico 2", encabezado));

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
        tabla2.addCell(new Paragraph("Tipo de arma", encabezado));
        tabla2.addCell(new Paragraph("Especial", encabezado));
        tabla2.addCell(new Paragraph("Entereza", encabezado));
        tabla2.addCell(new Paragraph("Rotura", encabezado));
        tabla2.addCell(new Paragraph("Presencia", encabezado));

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
            tabla3.addCell(new Paragraph("Tipo", encabezado));
            tabla3.addCell(new Paragraph("Cadencia de fuego", encabezado));
            tabla3.addCell(new Paragraph("Recarga", encabezado));
            tabla3.addCell(new Paragraph("Alcance", encabezado));

            if(contenedor instanceof ArmaLanzable lanzable){
                tabla3.addCell(new Paragraph("Lanzable", encabezado));
                tabla3.addCell(String.valueOf(lanzable.getCadenciaFuego()));
            }
            else{
                tabla3.addCell(new Paragraph("Disparo", encabezado));
                tabla3.addCell(new Paragraph("-", encabezado));
            }
            if(contenedor instanceof ArmaProyectiles disparo){
                tabla3.addCell(String.valueOf(disparo.getRecarga()));
            }
            else{
                tabla3.addCell(new Paragraph("-", encabezado));
            }
            tabla3.addCell(String.valueOf(distancia.getAlcance()));
        }

        anadirTituloSeccion(documento, "Datos del arma:");
        tabla.setSpacingAfter(5);
        tabla2.setSpacingAfter(5);
        tabla3.setSpacingAfter(5);
        documento.add(tabla);
        documento.add(new LineSeparator(1, 60, Color.white, Element.ALIGN_CENTER, 0));
        documento.add(tabla2);
        if(distancia != null){
            documento.add(new LineSeparator(1, 60, Color.white, Element.ALIGN_CENTER, 0));
            documento.add(tabla3);
        }
    }
    private void escribirMateriasPrimas(Document documento, Artefacto artefacto){
        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell(new Paragraph("Fuente de poder", encabezado));
        tabla.addCell(new Paragraph("Cantidad", encabezado));
        tabla.addCell(new Paragraph("PPs", encabezado));
        tabla.addCell(new Paragraph("Nivel", encabezado));

        Map<String, Integer> listaMat = new HashMap<>(contarMateriasPrimas(artefacto));
        for(MateriaPrima m : rehacerListaMat(artefacto)){
            tabla.addCell(m.getNombre());
            tabla.addCell(String.valueOf(listaMat.get(m.getNombre())));
            tabla.addCell(String.valueOf(m.getNivelPP()));
            tabla.addCell(String.valueOf(m.getCantidadPP()));
        }
        tabla.setSpacingBefore(5);
        tabla.setSpacingAfter(10);
        documento.add(tabla);
    }
    private void escribirPoderes(Document documento, Artefacto artefacto){
        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell(new Paragraph("Poder", encabezado));
        tabla.addCell(new Paragraph("Variante", encabezado));
        tabla.addCell(new Paragraph("Modificadores", encabezado));
        tabla.addCell(new Paragraph("Coste", encabezado));
        tabla.addCell(new Paragraph("Nivel", encabezado));

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
        tabla.setSpacingAfter(5);
        documento.add(tabla);
    }
    private void descripcionPoder(Document documento, Artefacto artefacto){
        PdfPTable tabla = new PdfPTable(2);
        for(Poder p : artefacto.getPoderes()){
            tabla.addCell(p.getNombre() + ": " + p.getOpcion().getNombre());
            tabla.addCell(p.getOpcion().getDescripcion());
        }
        documento.add(tabla);
    }
    private void descripcionArtefacto(Document documento, String descripcion){
        documento.add(new LineSeparator(1, 60, Color.black, Element.ALIGN_CENTER, 0));
        Paragraph des = new Paragraph(descripcion, texto);
        PdfPTable tabla = new PdfPTable(1);
        PdfPCell celda = new PdfPCell(des);
        celda.setBorderWidth(2);
        tabla.addCell(celda);
        tabla.setSpacingAfter(5);
        documento.add(des);
    }

    private Map<String, Integer> contarMateriasPrimas(Artefacto artefacto){
        Map<String, Integer> listaMat = new HashMap<>();
        for(MateriaPrima m : artefacto.getMateriasPrimas()){
            listaMat.put(m.getNombre(), listaMat.getOrDefault(m.getNombre(), 0) + 1);
        }
        return listaMat;
    }
    private List<MateriaPrima> rehacerListaMat(Artefacto artefacto){
        List<MateriaPrima> listaMaterias = new ArrayList<>();
        for(MateriaPrima m : artefacto.getMateriasPrimas()){
            if (!listaMaterias.contains(m)) {
                listaMaterias.add(m);
            }
        }
        return listaMaterias;
    }
    private void anadirTituloSeccion(Document documento, String titulo){
        documento.add(new Paragraph(titulo, encabezado));
        documento.add(new LineSeparator(1, 60, Color.white, Element.ALIGN_CENTER, 0));
    }

}
