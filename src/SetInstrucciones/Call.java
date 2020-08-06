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
public class Call extends Instruccion {

    private String offset = "";
    private boolean usado = false;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    @Override
    public void ejecutar() {
        System.out.println("Ring Ring..... Llamando");


    }

}
