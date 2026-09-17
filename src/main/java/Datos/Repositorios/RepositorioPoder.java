package Datos.Repositorios;

import Datos.ConexionDB;
import Datos.Enums.TipoContenedorEnum;
import Poderes.ModificadorPoder;
import Poderes.OpcionPoder;
import Poderes.PoderBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class RepositorioPoder {

    public List<PoderBase> listar(String faceta) {
        List<PoderBase> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM poder_base
                WHERE faceta = ?
                ORDER BY nombre
                """;
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, faceta);

            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {
                    int poderBaseId = resultado.getInt("id");
                    String nombrePoder = resultado.getString("nombre");
                    String facetaPoder = resultado.getString("faceta");
                    String descripcion = resultado.getString("descripcion");

                    List<OpcionPoder> opciones = obtenerOpciones(conexion, poderBaseId);
                    List<ModificadorPoder> modificadores = obtenerModificadores(conexion, poderBaseId);
                    List<TipoContenedorEnum> contenedores = obtenerContenedores(conexion, poderBaseId);

                    lista.add(new PoderBase(nombrePoder, facetaPoder, descripcion, contenedores, opciones, modificadores));

                }
                return lista;
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo la lista de poderes", e);
        }
    }
    public List<String> listarFacetas(){
        List<String> facetas = new ArrayList<>();

        String sql = """
                SELECT DISTINCT faceta
                FROM poder_base
                ORDER BY faceta
                """;
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()){

            while(resultado.next()){
                String facetaPoder = resultado.getString("faceta");
                facetas.add(facetaPoder);
            }
            return facetas;
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo la lista de poderes", e);
        }
    }

    private List<OpcionPoder> obtenerOpciones(Connection conexion, int poderBaseId){

        List<OpcionPoder> opciones = new ArrayList<>();

            String sql = """
                    SELECT nombre, coste_pp, nivel_pp, descripcion
                    FROM opcion_poder
                    WHERE poder_base_id = ?
                    ORDER BY nombre
                    """;

            try (PreparedStatement sentencia = conexion.prepareStatement(sql)){

                sentencia.setInt(1, poderBaseId);

                try (ResultSet resultado = sentencia.executeQuery()){

                    while (resultado.next()){

                        OpcionPoder opcion = new OpcionPoder(
                                resultado.getString("nombre"),
                                resultado.getInt("coste_pp"),
                                resultado.getInt("nivel_pp"),
                                resultado.getString("descripcion")
                        );

                        opciones.add(opcion);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Error obteniendo las opciones del poder", e);
            }

        return opciones;
    }
    private List<ModificadorPoder> obtenerModificadores(Connection conexion, int poderBaseId){

        List<ModificadorPoder> modificadores = new ArrayList<>();

        String sql = """
                    SELECT nombre, mod_coste_pp, mod_nivel_pp, descripcion
                    FROM modificadores_poder
                    WHERE poder_base_id = ?
                    ORDER BY nombre
                    """;

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, poderBaseId);

            try (ResultSet resultado = sentencia.executeQuery()){

                while (resultado.next()){

                    ModificadorPoder modificador = new ModificadorPoder(
                            resultado.getString("nombre"),
                            resultado.getInt("mod_coste_pp"),
                            resultado.getInt("mod_nivel_pp"),
                            resultado.getString("descripcion")
                    );

                    modificadores.add(modificador);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo los modificadores del poder", e);
        }

        return modificadores;
    }
    private List<TipoContenedorEnum> obtenerContenedores(Connection conexion, int poderBaseId){

        List<TipoContenedorEnum> contenedores = new ArrayList<>();

        String sql = """
                    SELECT contenedor
                    FROM contenedores_poder
                    WHERE poder_base_id = ?
                    ORDER BY contenedor
                    """;

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, poderBaseId);

            try (ResultSet resultado = sentencia.executeQuery()){

                while (resultado.next()){
                    contenedores.add(TipoContenedorEnum.valueOf(resultado.getString("contenedor")));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo los contenedores compatibles con el poder", e);
        }

        return contenedores;
    }
}