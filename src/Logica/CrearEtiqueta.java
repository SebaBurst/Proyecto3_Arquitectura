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
public class CrearEtiqueta {

    public static Boolean crear(Etiqueta label, String nombre) {
        boolean existe = false;
        switch (nombre) {
            case "add":
                label.setNombre(nombre);
                label.setOPC("00000");
                existe = true;
                break;
            case "sub":
                label.setNombre(nombre);
                label.setOPC("00001");
                existe = true;
                break;
            case "mul":
                label.setNombre(nombre);
                label.setOPC("00010");
                existe = true;
                break;
            case "div":
                label.setNombre(nombre);
                label.setOPC("00011");
                existe = true;
                break;
            case "mov":
                label.setNombre(nombre);
                label.setOPC("01001");
                existe = true;
                break;
            case "mod":
                label.setNombre(nombre);
                label.setOPC("00100");
                existe = true;
                break;
            case "cmp":
                label.setNombre(nombre);
                label.setOPC("00101");
                existe = true;
                break;
            case "and":
                label.setNombre(nombre);
                label.setOPC("00110");
                existe = true;
                break;
            case "or":
                label.setNombre(nombre);
                label.setOPC("00111");
                existe = true;
                break;
            case "not":
                label.setNombre(nombre);
                label.setOPC("01000");
                existe = true;
                break;
            case "lsl":
                label.setNombre(nombre);
                label.setOPC("01010");
                existe = true;
                break;
            case "lsr":
                label.setNombre(nombre);
                label.setOPC("01011");
                existe = true;
                break;
            case "asr":
                label.setNombre(nombre);
                label.setOPC("01100");
                existe = true;
                break;
            case "nop":
                label.setNombre(nombre);
                label.setOPC("01101");
                existe = true;
                break;
            case "ld":
                label.setNombre(nombre);
                label.setOPC("01110");
                existe = true;
                break;
            case "st":
                label.setNombre(nombre);
                label.setOPC("01111");
                existe = true;
                break;
            case "beq":
                label.setNombre(nombre);
                label.setOPC("10000");
                existe = true;
                break;
            case "bgt":
                label.setNombre(nombre);
                label.setOPC("10001");
                existe = true;
                break;
            case "b":
                label.setNombre(nombre);
                label.setOPC("10010");
                existe = true;
                break;
            case "call":
                label.setNombre(nombre);
                label.setOPC("10011");
                existe = true;
                break;
            case "ret":
                label.setNombre(nombre);
                label.setOPC("10100");
                existe = true;
                break;
            default:
                break;
        }

        return existe;
    }
}
