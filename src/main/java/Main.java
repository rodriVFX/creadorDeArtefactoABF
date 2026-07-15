import Contenedores.Contenedor;
import MateriasPrimas.MateriaPrima;
import Poderes.Poder;

void main() {
    AsistenteCreacionArtefacto asistente = new AsistenteCreacionArtefacto();

    Poder poder = asistente.elegirPoderes();

    Contenedor contenedor = asistente.elegirContenedor();
    System.out.println(contenedor.getNombre() + ", " + contenedor.getEspecialidades() + ", " + contenedor.getPresenciaBase());

    MateriaPrima materiaPrima = asistente.elegirMateriasPrimas();
    System.out.println(materiaPrima.getNombre() + ", " + materiaPrima.getPuntosPoder() + ", " + materiaPrima.getReglasEspeciales());
}