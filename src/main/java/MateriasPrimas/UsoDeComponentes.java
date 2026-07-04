package MateriasPrimas;

public class UsoDeComponentes extends MateriaPrima{

    public UsoDeComponentes(Componente componente) {
        super(componente.getPuntosPoder(), componente.getReglasEspeciales(), componente.getNombre());
    }
}
