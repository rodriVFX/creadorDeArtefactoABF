package Datos.Repositorios;

import Datos.ConexionDB;
import Datos.Material;
import Datos.Calidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Calidad> listarCalidades (){
        List<Calidad> lista = new ArrayList<>();
        String sql = """
                SELECT *
                FROM calidades
                ORDER BY id
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()){
            while(resultado.next()){
                String nombreCal = resultado.getString("nombre");
                int mod = resultado.getInt("modificador");
                lista.add(new Calidad(nombreCal, mod));
            }
            return lista;
        }
        catch (Exception e){
            throw new RuntimeException("Error al listar las calidades", e);
        }
    }
    public List<Material> listarMateriales () {
        List<Material> lista = new ArrayList<>();
        String sql = """
                SELECT *
                FROM materiales
                ORDER BY id
                """;
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                String nombreMat = resultado.getString("nombre");
                int mod = resultado.getInt("modificador");
                lista.add(new Material(nombreMat, mod));
            }
            return lista;
        }
        catch (Exception e) {
            throw new RuntimeException("Error al listar los materiales", e);
        }
    }
}