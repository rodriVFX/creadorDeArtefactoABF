package Datos.Enums;

public enum ListaFacetasPoder {
    GENERAL, OFENSIVA, DEFENSIVA, PROTECCION, MAGICA, PSIQUICA, CONJURACION, CONJUROS, MEJORAS, DOMINE, ESOTERICA;

    @Override
    public String toString() {
        return switch (this) {
            case GENERAL -> "Generales de Calidad";
            case OFENSIVA -> "Ofensiva";
            case DEFENSIVA -> "Defensiva";
            case PROTECCION -> "Protección";
            case MAGICA -> "Potenciación Mágica";
            case PSIQUICA -> "Potenciación Psíquica";
            case CONJURACION -> "Potenciación en la Conjuración";
            case CONJUROS -> "Conjuros Innatos";
            case MEJORAS -> "Mejoras";
            case DOMINE -> "Domine";
            case ESOTERICA -> "Esotérica";
        };
    }
}