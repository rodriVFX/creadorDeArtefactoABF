package Poderes;

import java.util.List;

public class CreadorPoder {

    public Poder crearPoder(PoderBase base, OpcionPoder opcion, List<ModificadorPoder> modificadores){

        return new Poder(base, opcion, modificadores);
    }
}
