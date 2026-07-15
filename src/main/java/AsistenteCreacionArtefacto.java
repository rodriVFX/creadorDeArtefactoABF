import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Datos.RepositorioDatos;
import Datos.Repositorios.*;
import Contenedores.*;

public class AsistenteCreacionArtefacto {

    private RepositorioDatos datos = new RepositorioDatos();
    private final Scanner obj = new Scanner(System.in);

    public Contenedor elegirContenedor(){
        System.out.println("""
                Selecciona el tipo de contenedor:
                1. Objeto
                2. Arma cuerpo a cuerpo
                3. Arma lanzable
                4. Arma de proyectiles
                5. Munición
                6. Armadura
                """);
        int input1 = obj.nextInt();
        if(input1 < 1 || input1 > 6){throw new IllegalArgumentException("Número incorrecto");}

        List<? extends Contenedor> lista = new ArrayList<>(switch(input1){
            case 1 -> datos.getObjetos().listar().values();
            case 2 -> datos.getArmasMelee().listar().values();
            case 3 -> datos.getArmasLanzables().listar().values();
            case 4 -> datos.getArmasProyectiles().listar().values();
            case 5 -> datos.getMuniciones().listar().values();
            case 6 -> datos.getArmaduras().listar().values();
            default -> throw new IllegalArgumentException("El número introducido no es correcto.");
        });
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i+1) + ". " + lista.get(i).getNombre());
        }
        int input2 = obj.nextInt();
        return lista.get(input2 - 1);
    }
}