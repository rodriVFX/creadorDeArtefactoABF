import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Datos.RepositorioDatos;
import Datos.Repositorios.*;
import Contenedores.*;
import MateriasPrimas.CreadorMateriaPrima;
import MateriasPrimas.MateriaPrima;

public class AsistenteCreacionArtefacto {

    private final RepositorioDatos datos = new RepositorioDatos();
    private final CreadorMateriaPrima creadorMat = new CreadorMateriaPrima();
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
        if(input1 < 1 || input1 > 6){throw new IllegalArgumentException("Opción no válida");}

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

    public MateriaPrima elegirMateriasPrimas(){
        System.out.println("""
                Selecciona el modo de obtención de PP:
                1. Componentes únicos
                2. Infusión de Zeón
                3. Sacrificio de Objetos
                4. Sacrificio de POD
                5. Sacrificio de Vidas
                """);
        int input1 = obj.nextInt();
        if(input1 < 1 || input1 > 5){throw new IllegalArgumentException("Opción no válida.");}

        return switch(input1){
            case 1 ->elegirComponente();
            case 2 ->cfgInfusionZeon();
            case 3 ->cfgSacrificioDeObjetos();
            case 4 ->cfgSacrificioDePoder();
            case 5 ->cfgSacrificioDeVida();
            default -> throw new IllegalArgumentException("Error al seleccionar el modo de obtención de PP.");
        };
    }

    private MateriaPrima elegirComponente(){
        List<? extends MateriaPrima> lista = new ArrayList<>(datos.getComponentes().listar().values());
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i+1) + ". " + lista.get(i).getNombre());
        }
        int input2 = obj.nextInt();
        return lista.get(input2 - 1);
    }
    private MateriaPrima cfgInfusionZeon(){
        System.out.println("Introduce la cantidad de Zeón.");
        int input1 = obj.nextInt();
        return creadorMat.crearInfusionZeon(input1);
    }
    private MateriaPrima cfgSacrificioDeObjetos(){
        System.out.println("¿Cuántos poderes tiene el objeto a sacrificar?");
        int nPoderes = obj.nextInt();
        if(nPoderes < 1){throw new IllegalArgumentException("Opción no válida.");}

        int[] nivelesPP = new int[nPoderes];
        int[] cantidadesPP = new int[nPoderes];

        for (int i = 0; i < nPoderes; i++) {
            System.out.println("Introduce el " + (i+1) + "º nivel de PP:");
            int nivelPP = obj.nextInt();
            nivelesPP[i] = nivelPP;

            System.out.println("Introduce la " + (i+1) + "º cantidad de PP:");
            int cantidadPP = obj.nextInt();
            cantidadesPP[i] = cantidadPP;
        }

        System.out.println("""
                ¿Quieres conservar el nivel de PP?
                1. Si
                2. No
                """);
        int conservarNivel = obj.nextInt();
        if(conservarNivel < 1 || conservarNivel > 2){throw new IllegalArgumentException("Opción no válida.");}
        boolean conservaNivel = conservarNivel == 1;

        return creadorMat.crearSacrificioDeObjetos(nivelesPP, cantidadesPP, conservaNivel);
    }
    private MateriaPrima cfgSacrificioDePoder(){
        System.out.println("Introduce tu presencia:");
        int presencia = obj.nextInt();
        System.out.println("Introduce el valor de POD a sacrificar:");
        int poder = obj.nextInt();

        return creadorMat.crearSacrificioDePoder(poder, presencia);
    }
    private MateriaPrima cfgSacrificioDeVida(){
        System.out.println("Introduce la cantidad de vidas a sacrificar:");
        int nVidas = obj.nextInt();
        System.out.println("Introduce la presencia de cada vida:");
        int presencia = obj.nextInt() * nVidas;
        System.out.println("Introduce la Natura del sacrificio:");
        int natura = obj.nextInt();
        System.out.println("Introduce la Gnosis del sacrificio:");
        int gnosis = obj.nextInt();
        System.out.println("""
                ¿Es un sacrificio voluntario?
                1. Si
                2. No
                """);
        int voluntario = obj.nextInt();
        if(voluntario < 1 || voluntario > 2){throw new IllegalArgumentException("Opción no válida.");}
        System.out.println("""
                ¿La vida sacrificada es sobrenatural?
                1. Si
                2. No
                """);
        int sobrenatural = obj.nextInt();
        if(sobrenatural < 1 || sobrenatural > 2){throw new IllegalArgumentException("Opción no válida.");}
        System.out.println("""
                ¿La vida sacrificada es artificial?
                1. Si
                2. No
                """);
        int artificial = obj.nextInt();
        if(artificial < 1 || artificial > 2){throw new IllegalArgumentException("Opción no válida.");}

        return creadorMat.crearSacrificioDeVidas(presencia, natura, gnosis, voluntario==1, sobrenatural==1, artificial==1);
    }
}