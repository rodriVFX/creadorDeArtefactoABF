import Contenedores.ConfiguracionObjeto;
import MateriasPrimas.ConfiguracionComponente;
import MateriasPrimas.CreadorMateriaPrima;
import Poderes.ConfiguracionPoder;
import Poderes.CreadorPoder;
import Poderes.PoderBase;

void main() {
    ConfiguracionPoder cfgPoder = new ConfiguracionPoder();
    ConfiguracionObjeto cfgObjeto = new ConfiguracionObjeto();
    ConfiguracionComponente cfgComponente = new ConfiguracionComponente();
    ConfiguracionArtefacto cfgArtefacto = new ConfiguracionArtefacto();
    CreadorArtefacto creador = new CreadorArtefacto();
    CreadorMateriaPrima creadorMat = new CreadorMateriaPrima();
    CreadorPoder creadorPoder = new CreadorPoder();

    Artefacto artefacto = creador.crearArtefacto(cfgObjeto.getObjeto("Anillo"), cfgArtefacto.getMaterial("Acero Negro"), cfgArtefacto.getCalidad("Calidad normal"), cfgArtefacto);

    artefacto.anadirMateriaPrima(cfgComponente.getComponente("Mandrágora"));
    artefacto.anadirMateriaPrima(creadorMat.crearInfusionZeon(500));

    PoderBase poderes = cfgPoder.getPoder("Daño Incrementado");
    artefacto.anadirPoder(creadorPoder.crearPoder(poderes, poderes.getOpciones().getFirst(), poderes.getModificadores()));

    System.out.println(artefacto.toString());
    AsistenteCreacionArtefacto ia = new AsistenteCreacionArtefacto();
    ia.elegirContenedor();
}