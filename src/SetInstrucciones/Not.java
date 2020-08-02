/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SetInstrucciones;

import Logica.Instruccion;

/**
 *
 * @author sebar
 */
public class Not extends Instruccion {

    @Override
    public void ejecutar() {
        int inmd = 0;
        int rg2 = 0;
        if (this.getR1() != null) {
            rg2 = this.getR1().getValor();
            int resultado = ~rg2;
            this.rd.setValor(resultado);

        } else {
            inmd = this.getInmediato();
            int resultado = ~inmd;
            this.rd.setValor(resultado);

        }

    }

}
