/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SetInstrucciones;

import Logica.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author sebar
 */
public class B extends Instruccion {

    private String offset = "";
    private boolean usado = false;

    @Override
    public void ejecutar() {
    }

    public int salto(ArrayList<Instruccion> instrucciones) {
        System.out.println("Jumping");
        for (int i = 0; i < instrucciones.size(); i++) {
            Instruccion aux = instrucciones.get(i);
            if (aux.getInstruccion().contains(this.getOffset())) {
                return i;

            }
        }

        return -1;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

}
