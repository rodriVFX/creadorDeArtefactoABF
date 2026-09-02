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

    public PoderBase getPoder(String nombre){
        String sql = """
                SELECT id, nombre, faceta, descripcion
                FROM poder_base
                WHERE nombre = ?
                """;
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setString(1, nombre);

            try (ResultSet resultado = sentencia.executeQuery()){
                if(!resultado.next()){
                    return null;
                }
                int poderBaseId = resultado.getInt("id");
                String nombrePoder = resultado.getString("nombre");
                String faceta = resultado.getString("faceta");
                String descripcion = resultado.getString("descripcion");

                List<OpcionPoder> opciones = obtenerOpciones(conexion, poderBaseId);
                List<ModificadorPoder> modificadores = obtenerModificadores(conexion, poderBaseId);
                List<TipoContenedorEnum> contenedores = obtenerContenedores(conexion, poderBaseId);

                return new PoderBase(nombrePoder, faceta, descripcion, contenedores, opciones, modificadores);
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo el poder: " + nombre, e);
        }
    }

    public Map<String, PoderBase> listar() {
        Map<String, PoderBase> poderes = new HashMap<>();

        String sql = """
                SELECT nombre
                FROM poder_base
                """;
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()){

            while(resultado.next()){
                    String nombrePoder = resultado.getString("nombre");
                    poderes.put(nombrePoder, getPoder(nombrePoder));
            }
            return poderes;
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo la lista de poderes", e);
        }
    }
    public Set<String> listarFacetas(){
        Set<String> facetas = new HashSet<>();

        String sql = """
                SELECT DISTINCT faceta
                FROM poder_base
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