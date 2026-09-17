package Datos.Repositorios;

import Contenedores.Municion;
import Datos.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositorioMunicion {

    public List<Municion> listar() {
        List<Municion> lista = new ArrayList<>();
        String sql = """
                SELECT *
                FROM municiones
                ORDER BY nombre
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()){
            while(resultado.next()){
                int municionID = resultado.getInt("id");
                String municionNombre = resultado.getString("nombre");
                int presencia = resultado.getInt("presencia");
                int dano = resultado.getInt("dano");
                String criticoPri = resultado.getString("critico_pri");
                String criticoSec = resultado.getString("critico_sec");
                String tipo = resultado.getString("tipo");
                int entereza = resultado.getInt("entereza");
                int rotura = resultado.getInt("rotura");

                lista.add(new Municion(municionNombre, presencia, getEspecialidades(municionID), entereza, rotura, getEspecializaciones(municionID), dano, criticoPri, criticoSec, tipo));
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
