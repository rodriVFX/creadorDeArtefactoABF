package Datos.Repositorios;

import Contenedores.Municion;
import Datos.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioMunicion {

    public Municion getMunicion(String nombre) {
        String sql = """
                SELECT id, nombre, presencia, dano, critico_pri, critico_sec, tipo, entereza, rotura
                FROM municiones
                WHERE nombre = ?
                """;
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, nombre);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (!resultado.next()) {
                    return null;
                }
                int municionID = resultado.getInt("id");
                String municionNombre = resultado.getString("nombre");
                int presencia = resultado.getInt("presencia");
                int dano = resultado.getInt("dano");
                String criticoPri = resultado.getString("critico_pri");
                String criticoSec = resultado.getString("critico_sec");
                String tipo = resultado.getString("tipo");
                int entereza = resultado.getInt("entereza");
                int rotura = resultado.getInt("rotura");

                return new Municion(municionNombre, presencia, getEspecialidades(municionID), entereza, rotura, getEspecializaciones(municionID), dano, criticoPri, criticoSec, tipo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la munición: " + nombre, e);
        }
    }

    public Map<String, Municion> listar() {
        Map<String, Municion> lista = new HashMap<>();
        String sql = """
                SELECT nombre
                FROM municiones
                ORDER BY nombre
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()){
            while(resultado.next()){
                String municion = resultado.getString("nombre");
                lista.put(municion, getMunicion(municion));
            }
            return lista;
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la lista de municiones", e);
        }
    }

    private List<String> getEspecialidades(int municionID){
        List<String> lista = new ArrayList<>();
        String sql = """
                SELECT especialidad
                FROM especialidades_municiones
                WHERE municion_id = ?
                ORDER BY especialidad
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setInt(1, municionID);

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
    private List<String> getEspecializaciones(int municionID){
        List<String> lista = new ArrayList<>();
        String sql = """
                SELECT especializacion
                FROM especializaciones_municiones
                WHERE municion_id = ?
                ORDER BY especializacion
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setInt(1, municionID);

            try(ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    String especializacion = resultado.getString("especializacion");
                    lista.add(especializacion);
                }
                return lista;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar las especializaciones", e);
        }
    }
}
