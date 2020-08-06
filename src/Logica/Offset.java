/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author sebar
 */
public class Offset extends Instruccion {

    
    private ArrayList<Instruccion> bloque = new ArrayList();
    private boolean retorno = false;
    
    
    @Override
    public void ejecutar() {
    }

    public ArrayList<Instruccion> getBloque() {
        return bloque;
    }

    public void addBloque(Instruccion id) {
        bloque.add(id);
    }

    public boolean isRetorno() {
        return retorno;
    }

    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }
    
    

}
