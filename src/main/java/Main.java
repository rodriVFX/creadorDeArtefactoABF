import Artefactos.Artefacto;
import Artefactos.ValidadorArtefacto;

void main() {
    AsistenteCreacionArtefacto asistente = new AsistenteCreacionArtefacto();
    ValidadorArtefacto validador = new ValidadorArtefacto();

    Artefacto artefacto = asistente.crearArtefacto();
    validador.validarArtefacto(artefacto);
    System.out.println(artefacto.toString());
}