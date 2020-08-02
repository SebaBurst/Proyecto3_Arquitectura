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
public class Mov extends Instruccion {
    @Override
    public void ejecutar() {
        if (this.r1 != null) {
            this.rd.setValor(r1.getValor());
        } else {
            this.rd.setValor(this.getInmediato());

        }

    }
}
