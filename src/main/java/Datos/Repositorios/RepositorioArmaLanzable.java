package Datos.Repositorios;

import Contenedores.ArmaLanzable;
import Datos.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArmaLanzable {

    public List<ArmaLanzable> listar() {
        List<ArmaLanzable> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM armas_lanzables
                ORDER BY nombre
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()){

            while (resultado.next()){
                int armaId = resultado.getInt("id");
                String nombreArma = resultado.getString("nombre");
                int dano = resultado.getInt("dano");
                int presencia = resultado.getInt("presencia");
                int modHA = resultado.getInt("modificador_ha");
                int ignoraTA = resultado.getInt("ignora_ta");
                int turno = resultado.getInt("turno");
                String fueReq = resultado.getString("fue_requerida");
                String criticoPri = resultado.getString("critico_pri");
                String criticoSec = resultado.getString("critico_sec");
                String tipo = resultado.getString("tipo");
                int entereza = resultado.getInt("entereza");
                int rotura = resultado.getInt("rotura");
                int alcance = resultado.getInt("alcance");
                int cadencia = resultado.getInt("cadencia_fue");

                lista.add(new ArmaLanzable(dano, nombreArma, presencia, getEspecialidades(armaId), modHA, ignoraTA, turno, fueReq, tipo, entereza, rotura, getEspecializaciones(armaId), alcance, cadencia, criticoPri, criticoSec));
            }
            return lista;
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo la lista de Armas Lanzables", e);
        }
    }

    private List<String> getEspecialidades(int armaId){
        List<String> especialidades = new ArrayList<>();

        String sql = """
                SELECT especialidad
                FROM especialidades_armas_lanzables
                WHERE arma_lanzable_id = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, armaId);

            try(ResultSet resultado = sentencia.executeQuery()){

                while (resultado.next()){

                    especialidades.add(resultado.getString("especialidad"));
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo las especialidades del arma", e);
        }
        return especialidades;
    }
    private List<String> getEspecializaciones(int armaId){
        List<String> especializaciones = new ArrayList<>();

        String sql = """
                SELECT especializacion
                FROM especializaciones_armas_lanzables
                WHERE arma_lanzable_id = ?
                """;
        try(Connection conexion = ConexionDB.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, armaId);

            try(ResultSet resultado = sentencia.executeQuery()){

                while (resultado.next()){

                    especializaciones.add(resultado.getString("especializacion"));
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error obteniendo las especializaciones del arma", e);
        }
        return especializaciones;
    }
}
