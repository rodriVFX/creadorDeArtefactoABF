package Datos.Repositorios;
import Datos.ConexionDB;
import MateriasPrimas.Componente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioComponente {

    public List<Componente> listar () {
        List<Componente> lista = new ArrayList<>();
        String sql = """
                SELECT *
                FROM componentes_unicos
                ORDER BY nombre
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()){
            while(resultado.next()){
                int compID = resultado.getInt("id");
                String nombreComp = resultado.getString("nombre");
                int nivel = resultado.getInt("nivel_pp");
                int cantidad = resultado.getInt("cantidad_pp");

                lista.add(new Componente(nombreComp, nivel, cantidad, getEspecialidades(compID)));

            }
            return lista;
        }
        catch (Exception e) {
            throw new RuntimeException("Error al listar los componentes", e);
        }
    }

    private List<String> getEspecialidades(int compID){
        List<String> lista = new ArrayList<>();
        String sql = """
                SELECT especialidad
                FROM especialidades_componentes_unicos
                WHERE componente_id = ?
                ORDER BY especialidad
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setInt(1, compID);
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