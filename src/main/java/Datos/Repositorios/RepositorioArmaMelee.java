package Datos.Repositorios;

import Contenedores.ArmaMelee;
import Datos.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioArmaMelee {

    public ArmaMelee getArmaMelee(String nombre){
        String sql = """
                SELECT id, nombre, dano, modificador_ha, ignora_ta, turno, fue_requerida, critico_pri, critico_sec, tipo, entereza, rotura, presencia
                FROM armas_melee
                WHERE nombre = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);

            try(ResultSet resultado = sentencia.executeQuery()){
                if(!resultado.next()){
                    return null;
                }
                int armaId = resultado.getInt("id");
                String nombreArma = resultado.getString("nombre");
                int dano = resultado.getInt("dano");
                int modHA = resultado.getInt("modificador_ha");
                int ignoraTA = resultado.getInt("ignora_ta");
                int turno = resultado.getInt("turno");
                String fueReq = resultado.getString("fue_requerida");
                String criticoPri = resultado.getString("critico_pri");
                String criticoSec = resultado.getString("critico_sec");
                String tipo = resultado.getString("tipo");
                int entereza = resultado.getInt("entereza");
                int rotura = resultado.getInt("rotura");
                int presencia = resultado.getInt("presencia");

                return new ArmaMelee(nombreArma, presencia, getEspecialidades(armaId), dano, modHA, ignoraTA, turno, fueReq, criticoPri, criticoSec, tipo, entereza, rotura, getEspecializaciones(armaId));
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo el arma: " + nombre, e);
        }
    }

    public Map<String, ArmaMelee> listar() {
        Map<String, ArmaMelee> lista = new HashMap<>();
        String sql = """
                SELECT nombre
                FROM armas_melee
                ORDER BY nombre
                """;
        try(Connection conexion = ConexionDB.conectar();
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery()){
            while(resultado.next()){
                String nombre = resultado.getString("nombre");
                lista.put(nombre, getArmaMelee(nombre));
            }
            return lista;
        }
        catch (Exception e){
            throw new RuntimeException("No se ha podido cargar la lista de armas", e);
        }
    }

    private List<String> getEspecialidades(int idArma){
        List<String> especialidades = new ArrayList<>();

        String sql = """
                SELECT especialidad
                FROM especialidades_armas_melee
                WHERE arma_melee_id = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, idArma);

            try (ResultSet resultado = sentencia.executeQuery()){
                while(resultado.next()){
                    especialidades.add(resultado.getString("especialidad"));
                }
                return especialidades;
            }
        }
        catch (Exception e){
            throw new RuntimeException("No se han podido cargar las especialidades", e);
        }
    }
    private List<String> getEspecializaciones(int idArma){
        List<String> especializaciones = new ArrayList<>();

        String sql = """
                SELECT especializacion
                FROM especializaciones_armas_melee
                WHERE arma_melee_id = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, idArma);

            try (ResultSet resultado = sentencia.executeQuery()){
                while(resultado.next()){
                    especializaciones.add(resultado.getString("especializacion"));
                }
                return especializaciones;
            }
        }
        catch (Exception e){
            throw new RuntimeException("No se han podido cargar las especializaciones", e);
        }
    }

}