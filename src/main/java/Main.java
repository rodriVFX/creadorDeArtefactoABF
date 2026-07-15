import Contenedores.Contenedor;
import MateriasPrimas.MateriaPrima;

void main() {
    AsistenteCreacionArtefacto asistente = new AsistenteCreacionArtefacto();

    Contenedor contenedor = asistente.elegirContenedor();
    System.out.println(contenedor.getNombre() + ", " + contenedor.getEspecialidades() + ", " + contenedor.getPresenciaBase());

    MateriaPrima materiaPrima = asistente.elegirMateriasPrimas();
    System.out.println(materiaPrima.getNombre() + ", " + materiaPrima.getPuntosPoder() + ", " + materiaPrima.getReglasEspeciales());
}