package Datos.Repositorios;

import Contenedores.Armadura;
import Datos.ConexionDB;
import Datos.Enums.TipoDanoEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioArmadura {

    public List<Armadura> listar() {
        List<Armadura> armaduras = new ArrayList<>();

        String sql = """
                SELECT *
                FROM armaduras
                ORDER BY nombre
                """;
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()){

            while(resultado.next()){
                int armadurasId = resultado.getInt("id");
                String nombreArmadura = resultado.getString("nombre");
                int requerimiento = resultado.getInt("requerimiento");
                int penalizador = resultado.getInt("penalizador");
                int restMov = resultado.getInt("restriccion_mov");
                int entereza = resultado.getInt("entereza");
                int rotura = resultado.getInt("rotura");
                int presencia = resultado.getInt("presencia");
                String localizacion = resultado.getString("localizacion");
                String tipo = resultado.getString("tipo");
                List<String> especializaciones = new ArrayList<>();
                especializaciones.add(resultado.getString("especializaciones"));

                armaduras.add(new Armadura(nombreArmadura, presencia, getEspecialidades(armadurasId), tipo, entereza, rotura, especializaciones, getTa(armadurasId), requerimiento, penalizador, restMov, localizacion));
            }
            return armaduras;
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo la lista de armaduras", e);
        }
    }

    private List<String> getEspecialidades(int idArmadura){
        String sql = """
                SELECT especialidad
                FROM especialidades_armadura
                WHERE armaduras_id = ?
                """;

        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, idArmadura);

            try(ResultSet resultado = sentencia.executeQuery()){
                List<String> especialidades = new ArrayList<>();
                while(resultado.next()) {
                    especialidades.add(resultado.getString("especialidad"));
                }
                return especialidades;
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo las especialidades de la armadura", e);
        }
    }
    private Map<TipoDanoEnum, Integer> getTa(int idArmadura){
        String sql = """
                SELECT armaduras_id, id, tipo_TA, cantidad_TA
                FROM TA_armadura
                WHERE armaduras_id = ?
                """;

        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, idArmadura);

            try(ResultSet resultado = sentencia.executeQuery()){

                Map<TipoDanoEnum, Integer> mapaTA = new HashMap<>();
                while(resultado.next()){
                    TipoDanoEnum tipo = TipoDanoEnum.valueOf(resultado.getString("tipo_TA"));
                    mapaTA.put(tipo, resultado.getInt("cantidad_TA"));
                }

                return mapaTA;
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo las TAs de la armadura", e);
        }
    }
}