package Datos.Repositorios;

import Datos.ConexionDB;
import Datos.Material;
import Datos.Calidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class RepositorioArtefacto {


    public Material getMaterial(String nombre){
        String sql = """
                SELECT nombre, modificador
                FROM materiales
                WHERE nombre = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);
            try(ResultSet resultado = sentencia.executeQuery()){
                if(!resultado.next()){
                    return null;
                }
                String nombreMat = resultado.getString("nombre");
                int modMat = resultado.getInt("modificador");
                return new Material(nombreMat, modMat);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Error al cargar el material: " + nombre, e);
        }
    }
    public Calidad getCalidad (String nombre){
        String sql = """
                SELECT nombre, modificador
                FROM calidades
                WHERE nombre = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);
            try(ResultSet resultado = sentencia.executeQuery()){
                if(!resultado.next()){
                    return null;
                }
                String nombreCal = resultado.getString("nombre");
                int modCal = resultado.getInt("modificador");
                return new Calidad(nombreCal, modCal);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Error al cargar la calidad: " + nombre, e);
        }
    }

    public Map<String, Calidad> listarCalidades (){
        Map<String, Calidad> lista = new HashMap<>();
        String sql = """
                SELECT nombre
                FROM calidades
                ORDER BY id
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()){
            while(resultado.next()){
                String nombreCal = resultado.getString("nombre");
                lista.put(nombreCal, getCalidad(nombreCal));
            }
            return lista;
        }
        catch (Exception e){
            throw new RuntimeException("Error al listar las calidades", e);
        }
    }
    public Map<String, Material> listarMateriales () {
        Map<String, Material> lista = new HashMap<>();
        String sql = """
                SELECT nombre
                FROM materiales
                ORDER BY id
                """;
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                String nombreMat = resultado.getString("nombre");
                lista.put(nombreMat, getMaterial(nombreMat));
            }
            return lista;
        }
        catch (Exception e) {
            throw new RuntimeException("Error al listar los materiales", e);
        }
    }
}