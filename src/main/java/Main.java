import Contenedores.Contenedor;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

void main() {
    AsistenteCreacionArtefacto asistente = new AsistenteCreacionArtefacto();

    Contenedor contenedor = asistente.elegirContenedor();
    MateriaPrima materiaPrima = asistente.elegirMateriasPrimas();
    Poder poder = asistente.elegirPoderes();

    System.out.println("Poder: " + poder.getNombre() + ": " + poder.getOpcion().getNombre() + ".\n" + "Modificadores: " + poder.getModificadores().toString());
    System.out.println(contenedor.getNombre() + ", " + contenedor.getEspecialidades() + ", " + contenedor.getPresenciaBase());
    System.out.println(materiaPrima.getNombre() + ", " + materiaPrima.getPuntosPoder() + ", " + materiaPrima.getReglasEspeciales());
}