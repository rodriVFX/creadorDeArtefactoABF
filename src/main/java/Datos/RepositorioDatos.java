package Datos;
import Datos.Repositorios.*;

public class RepositorioDatos {

    private final RepositorioPoder poderes;
    private final RepositorioObjeto objetos;
    private final RepositorioArmaMelee armasMelee;
    private final RepositorioArmaLanzable armasLanzables;
    private final RepositorioArmaProyectiles armasProyectiles;
    private final RepositorioMunicion municiones;
    private final RepositorioArmadura armaduras;
    private final RepositorioComponente componentes;
    private final RepositorioArtefacto artefacto;

    public RepositorioDatos(){
        poderes = new RepositorioPoder();
        objetos = new RepositorioObjeto();
        armasMelee = new RepositorioArmaMelee();
        armasLanzables = new RepositorioArmaLanzable();
        armasProyectiles = new RepositorioArmaProyectiles();
        municiones = new RepositorioMunicion();
        armaduras = new RepositorioArmadura();
        componentes = new RepositorioComponente();
        artefacto = new RepositorioArtefacto();

    }

    public RepositorioPoder getPoderes(){return poderes;}
    public RepositorioObjeto getObjetos(){return objetos;}
    public RepositorioArmaMelee getArmasMelee(){return armasMelee;};
    public RepositorioArmaLanzable getArmasLanzables(){return armasLanzables;};
    public RepositorioArmaProyectiles getArmasProyectiles(){return armasProyectiles;};
    public RepositorioMunicion getMuniciones(){return municiones;};
    public RepositorioArmadura getArmaduras(){return armaduras;};
    public RepositorioComponente getComponentes(){return componentes;};
    public RepositorioArtefacto getArtefacto(){return artefacto;};
}
