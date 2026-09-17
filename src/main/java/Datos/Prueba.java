package Datos;

public class Prueba {

    public static void main(String[] args) {

        RepositorioDatos datos = new RepositorioDatos();
        System.out.println(datos.getComponentes().listar());
    }
}