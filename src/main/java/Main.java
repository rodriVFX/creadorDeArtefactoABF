import Artefactos.Artefacto;
import Artefactos.ValidadorArtefacto;
import Contenedores.Contenedor;
import Datos.Calidad;
import Datos.Material;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

void main() {
    AsistenteCreacionArtefacto asistente = new AsistenteCreacionArtefacto();
    ValidadorArtefacto validador = new ValidadorArtefacto();

    Artefacto artefacto = asistente.crearArtefacto();
    validador.calcularPPRestantes(artefacto);
    System.out.println(artefacto.toString());
}