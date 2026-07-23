import Artefactos.Artefacto;
import Artefactos.ValidadorArtefacto;

void main() {
    AsistenteCreacionArtefacto asistente = new AsistenteCreacionArtefacto();
    ValidadorArtefacto validador = new ValidadorArtefacto();
    ExportadorPDF exportador = new ExportadorPDF();

    Artefacto artefacto = asistente.crearArtefacto();
    validador.validarArtefacto(artefacto);
    exportador.exportar(artefacto, artefacto.getNombre());
}