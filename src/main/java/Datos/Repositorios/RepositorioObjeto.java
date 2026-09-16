package Datos.Repositorios;

import Contenedores.Objeto;
import Datos.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioObjeto {


    public Objeto getObjeto(String nombre){
        String sql = """
                SELECT id, nombre, presencia
                FROM objetos
                WHERE nombre = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
        PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);
            try(ResultSet resultado = sentencia.executeQuery()){
                if(!resultado.next()){
                    return null;
                }
                int objetoID = resultado.getInt("id");
                String objetoNombre = resultado.getString("nombre");
                int presencia = resultado.getInt("presencia");

                return new Objeto(objetoNombre, presencia, getEspecialidades(objetoID));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el objeto: " + nombre, e);
        }
    }
    public Map<String, Objeto> listar(){
        Map<String, Objeto> lista = new HashMap<>();
        String sql = """
                SELECT nombre
                FROM objetos
                ORDER BY nombre
                """;
        try(Connection conexion = ConexionDB.conectar();
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery()){
            while(resultado.next()) {
                String nombreObj = resultado.getString("nombre");
                lista.put(nombreObj, getObjeto(nombreObj));
            }
            return lista;
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la lista de objetos", e);
        }
    }

    private List<String> getEspecialidades(int objetoID){
        List<String> lista = new ArrayList<>();
        String sql = """
                SELECT especialidad
                FROM especialidades_objetos
                WHERE objeto_id = ?
                ORDER BY especialidad
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setInt(1, objetoID);

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