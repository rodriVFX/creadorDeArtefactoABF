import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import Contenedores.*;
import MateriasPrimas.ConfiguracionComponente;
import Poderes.ConfiguracionPoder;

public class AsistenteCreacionArtefacto {

    private final ConfiguracionObjeto objetos = new ConfiguracionObjeto();
    private final ConfiguracionArmaMelee armasMelee = new ConfiguracionArmaMelee();
    private final ConfiguracionArmaLanzable armasLanzables = new ConfiguracionArmaLanzable();
    private final ConfiguracionArmaProyectiles armasProyectil = new ConfiguracionArmaProyectiles();
    private final ConfiguracionMunicion municiones = new ConfiguracionMunicion();
    private final ConfiguracionArmadura armaduras = new ConfiguracionArmadura();
    ConfiguracionPoder cfgPoder = new ConfiguracionPoder();
    ConfiguracionComponente cfgComponente = new ConfiguracionComponente();
    ConfiguracionArtefacto cfgArtefacto = new ConfiguracionArtefacto();

    public Contenedor elegirContenedor(){
        Scanner obj = new Scanner(System.in);
        StringBuilder sb1 = new StringBuilder();
        System.out.println(sb1.append("Selecciona el tipo de contenedor: \n")
                .append("1. Objeto\n").append("2. Arma cuerpo a cuerpo\n")
                .append("3. Arma lanzable\n").append("4. Arma de proyectiles\n")
                .append("5. Munición\n").append("6. Armadura"));
        int input1 = obj.nextInt();

        List<? extends Contenedor> lista = new ArrayList<>(switch(input1){
            case 1 -> objetos.getObjetos().values();
            case 2 -> armasMelee.getArmasMelee().values();
            case 3 -> armasLanzables.getArmasLanzables().values();
            case 4 -> armasProyectil.getArmasProyectiles().values();
            case 5 -> municiones.getMuniciones().values();
            case 6 -> armaduras.getArmaduras().values();
            default -> throw new IllegalArgumentException("El número introducido no es correcto.");
        });
        System.out.println(lista);
        return (Contenedor) lista;
    }
}
