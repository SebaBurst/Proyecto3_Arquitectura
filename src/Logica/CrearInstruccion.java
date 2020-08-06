/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import SetInstrucciones.Add;
import SetInstrucciones.And;
import SetInstrucciones.Asr;
import SetInstrucciones.B;
import SetInstrucciones.Cmp;
import SetInstrucciones.Div;
import SetInstrucciones.Lsl;
import SetInstrucciones.Lsr;
import SetInstrucciones.Mod;
import SetInstrucciones.Mov;
import SetInstrucciones.Mul;
import SetInstrucciones.Nop;
import SetInstrucciones.Not;
import SetInstrucciones.Or;
import SetInstrucciones.Ret;
import SetInstrucciones.Sub;

/**
 *
 * @author sebar
 */
public class CrearInstruccion {

    public static Instruccion creaInstruccion(String nombre) {
        switch (nombre) {
            case "add":
                return new Add();
            case "sub":
                return new Sub();
            case "mul":
                return new Mul();
            case "div":
                return new Div();
            case "mov":
                return new Mov();
            case "mod":
                return new Mod();
            case "cmp":
                return new Cmp();
            case "and":
                return new And();
            case "or":
                return new Or();
            case "not":
                return new Not();
            case "lsl":
                return new Lsl();
            case "lsr":
                return new Lsr();
            case "asr":
                return new Asr();
            case "nop":
                return new Nop();
            case "ld":
                break;
            case "st":
                break;
            case "beq":
                break;
            case "bgt":
                break;
            case "b":
                return new B();
            case "call":
                break;
            case "ret":
                return new Ret();
            default:
                break;
        }

        return null;

    }

}
