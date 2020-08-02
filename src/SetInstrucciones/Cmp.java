/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SetInstrucciones;

import Logica.Instruccion;
import Logica.Registro;

/**
 *
 * @author sebar
 */
public class Cmp extends Instruccion {

    public Registro igual = new Registro();
    public Registro mayor = new Registro();

    @Override
    public void ejecutar() {
        int inmd = 0;
        int rg2 = 0;
        int resultado = 0;
        int rg1 = this.getRd().getValor();
        if (this.getR1() != null) {
            rg2 = this.getR1().getValor();
            resultado = rg1 - rg2;

        } else {
            inmd = this.getInmediato();
            resultado = rg1 - inmd;
            this.rd.setValor(resultado);

        }

        if (resultado == 0) {
            this.getIgual().setValor(1);
            this.getMayor().setValor(0);

        } else if (resultado > 0) {
            this.getIgual().setValor(0);
            this.getMayor().setValor(1);

        }

    }

    public Registro getIgual() {
        return igual;
    }

    public void setIgual(Registro igual) {
        this.igual = igual;
    }

    public Registro getMayor() {
        return mayor;
    }

    public void setMayor(Registro mayor) {
        this.mayor = mayor;
    }

}
