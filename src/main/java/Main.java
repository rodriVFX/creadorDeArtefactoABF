import Contenedores.ConfiguracionObjeto;
import MateriasPrimas.ConfiguracionComponente;
import MateriasPrimas.CreadorMateriaPrima;
import Poderes.ConfiguracionPoder;

void main() {
    ConfiguracionPoder cfgPoder = new ConfiguracionPoder();
    ConfiguracionObjeto cfgObjeto = new ConfiguracionObjeto();
    ConfiguracionComponente cfgComponente = new ConfiguracionComponente();
    ConfiguracionArtefacto cfgArtefacto = new ConfiguracionArtefacto();
    CreadorArtefacto creador = new CreadorArtefacto();
    CreadorMateriaPrima creadorMat = new CreadorMateriaPrima();

    Artefacto artefacto = creador.crearArtefacto(cfgObjeto.getObjeto("Anillo"), cfgArtefacto.getMaterial("Acero Negro"), cfgArtefacto.getCalidad("Calidad normal"), cfgArtefacto);

    artefacto.anadirMateriaPrima(cfgComponente.getComponente("Mandrágora"));
    artefacto.anadirMateriaPrima(creadorMat.crearInfusionZeon(500));

    System.out.println(artefacto.toString());
}