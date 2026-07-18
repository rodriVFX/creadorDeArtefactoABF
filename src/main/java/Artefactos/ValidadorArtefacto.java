package Artefactos;

import Datos.Calculadora;
import Datos.TipoContenedorEnum;
import Poderes.Poder;

import java.util.*;

public class ValidadorArtefacto {


    Calculadora calc = new Calculadora();

    public void validarArtefacto(Artefacto artefacto){
        validadorCompatibilidad(artefacto);
        validarPresencia(calc.calcularPresenciaInicial(artefacto.getContenedor(), artefacto.getMaterial(), artefacto.getCalidad(), artefacto.getCreado()), calc.calcularPresenciaRequerida(artefacto.getPoderes()));
        validarPPs(artefacto);
    }

    private void validadorCompatibilidad(Artefacto artefacto) {
        if (!artefacto.getPoderes().isEmpty()) {
            if (!compatibilidadArmas(artefacto)) {
                throw new IllegalArgumentException("Alguno de los poderes no es compatible con el contenedor seleccionado.");
            }
        }
    }
    private void validarPresencia(int presenciaInicial, int presenciaRequerida){
        if(presenciaRequerida > presenciaInicial){
            throw new IllegalArgumentException("La presencia del objeto es demasiado baja.");
        }
    }
    private void validarPPs(Artefacto artefacto){
        for(Map.Entry<Integer, Integer> p : calc.calcularPPRestantes(artefacto).entrySet()){
            if(p.getValue() < 0){
                throw new IllegalArgumentException("No hay PPs suficientes para los poderes seleccionados.");
            }
        }
    }

    private boolean compatibilidadArmas(Artefacto artefacto) {
        TipoContenedorEnum tipoContenedor = TipoContenedorEnum.valueOf(artefacto.getContenedor().getTipo().toUpperCase());
        List<TipoContenedorEnum> tiposPermitidos = new ArrayList<>();
        List<Integer> permitidos = new ArrayList<>();
        boolean permitido = false;
        for (Poder p : artefacto.getPoderes()) {
            tiposPermitidos.addAll(p.getBase().getContenedoresPermitidos());
            if (tiposPermitidos.contains(tipoContenedor)) {
                permitidos.add(1);
            } else {
                permitidos.add(0);
            }
        }
        if (permitidos.contains(0)) {
            permitido = true;
        }
        return permitido;
    }

}