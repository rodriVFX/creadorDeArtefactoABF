package Datos.Repositorios;

import Contenedores.Tatuaje;
import Datos.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioTatuajes {

    public Tatuaje getTatuaje(String nombre){
        String sql = """
                SELECT id, nombre, presencia
                FROM tatuajes
                WHERE nombre = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
        PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);
            try(ResultSet resultado = sentencia.executeQuery()){
                if(!resultado.next()){
                    return null;
                }
                int tatuajeID = resultado.getInt("id");
                String tatuajeNombre = resultado.getString("nombre");
                int presencia = resultado.getInt("presencia");

                return new Tatuaje(tatuajeNombre, presencia, getEspecialidades(tatuajeID));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el tatuaje: " + nombre, e);
        }
    }
    public Map<String, Tatuaje> listar(){
        Map<String, Tatuaje> lista = new HashMap<>();
        String sql = """
                SELECT nombre
                FROM tatuajes
                ORDER BY nombre
                """;
        try(Connection conexion = ConexionDB.conectar();
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery()){
            while (resultado.next()){
                String tatuaje = resultado.getString("nombre");
                lista.put(tatuaje, getTatuaje(tatuaje));
            }
            return lista;
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la lista de tatuajes", e);
        }
    }

    private List<String> getEspecialidades(int tatuajeID){
        List<String> lista = new ArrayList<>();
        String sql = """
                SELECT especialidad
                FROM especialidades_tatuajes
                WHERE tatuajes_id = ?
                ORDER BY especialidad
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setInt(1, tatuajeID);

            try(ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    String especialidad = resultado.getString("especialidad");
                    lista.add(especialidad);
                }
                return lista;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar las especialidades", e);
        }
    }
}
