package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Prueba {

    public static void main(String[] args) {

        try (Connection conexion = ConexionDB.conectar();
             Statement statement = conexion.createStatement();
             ResultSet resultado = statement.executeQuery(
                     "SELECT nombre FROM poder_base")) {

            while (resultado.next()) {
                System.out.println(resultado.getString("nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
