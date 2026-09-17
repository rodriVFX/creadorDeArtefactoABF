package Datos.Repositorios;

import Contenedores.Objeto;
import Datos.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositorioObjeto {


    public List<Objeto> listar(){
        List<Objeto> lista = new ArrayList<>();
        String sql = """
                SELECT *
                FROM objetos
                ORDER BY nombre
                """;
        try(Connection conexion = ConexionDB.conectar();
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery()){
            while(resultado.next()) {
                int objetoID = resultado.getInt("id");
                String objetoNombre = resultado.getString("nombre");
                int presencia = resultado.getInt("presencia");

                lista.add(new Objeto(objetoNombre, presencia, getEspecialidades(objetoID)));
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