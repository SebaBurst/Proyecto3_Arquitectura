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
public class Sub extends Instruccion {

    @Override
    public void ejecutar() {
        int inmd = 0;
        int rg2 = 0;
        int rg1 = this.getR1().getValor();
        if (this.getR2() != null) {
            rg2 = this.getR2().getValor();
            int resultado = rg1 - rg2;
            this.rd.setValor(resultado);

        } else {
            inmd = this.getInmediato();
            int resultado = rg1 - inmd;
            this.rd.setValor(resultado);

        }

    }

}
