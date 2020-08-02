/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author sebar
 */
public abstract class Instruccion {

    
    
    public String instruccion;
    public Etiqueta etiqueta;
    public Registro rd;
    public Registro r2;
    public Registro r1;
    public int inmediato;

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }
    

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Registro getRd() {
        return rd;
    }

    public void setRd(Registro rd) {
        this.rd = rd;
    }

    public Registro getR2() {
        return r2;
    }

    public void setR2(Registro r2) {
        this.r2 = r2;
    }

    public Registro getR1() {
        return r1;
    }

    public void setR1(Registro r1) {
        this.r1 = r1;
    }

    public int getInmediato() {
        return inmediato;
    }

    public void setInmediato(int inmediato) {
        this.inmediato = inmediato;
    }
    
    public abstract void ejecutar();

}
