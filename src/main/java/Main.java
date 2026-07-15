import Contenedores.Contenedor;

void main() {
    AsistenteCreacionArtefacto asistente = new AsistenteCreacionArtefacto();

    Contenedor contenedor = asistente.elegirContenedor();
    System.out.println(contenedor.getNombre() + ", " + contenedor.getEspecialidades() + ", " + contenedor.getPresenciaBase());
}