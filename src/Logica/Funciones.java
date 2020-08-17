/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import SetInstrucciones.B;
import SetInstrucciones.Mov;
import SetInstrucciones.Nop;
import SetInstrucciones.Not;
import java.util.ArrayList;

/**
 *
 * @author sebar
 */
public class Funciones {

    private final String[] noA = {"nop", "b", "beq", "bgt", "call"};
    private final String[] noB = {"nop", "cmp", "ret", "b", "beq", "bgt", "st"};
    private final String[] noC = {"nop", "cmp", "ret", "b", "beq", "bgt", "st", "nop", "b", "beq", "bgt", "call"};

    public boolean noError(Instruccion a) {

        boolean error = true;
        for (String noC1 : noC) {
            if (a.getEtiqueta().getNombre().equals(noC1)) {
                error = false;
            }
        }

        return error;

    }

    public boolean conflicto(Instruccion a, Instruccion b) {

        Etiqueta ax = a.getEtiqueta();
        Etiqueta bx = b.getEtiqueta();

        for (String noA1 : noA) {
            if (ax.getNombre().equals(noA1)) {
                return false;
            }
        }

        if (a instanceof Offset || b instanceof Offset) {
            return false;

        }
        for (String noB1 : noB) {
            if (bx.getNombre().equals(noB1)) {
                return false;
            }
        }

        Registro src1 = a.getR1();
        Registro src2 = a.getR2();

        if (ax.getNombre().equals("st")) {
            src2 = a.getRd();
        }
        //Verificar if del ret

        boolean hasSrc1 = true;

        if (a instanceof Mov || a instanceof Not) {
            hasSrc1 = false;

        }

        Registro dest = b.getRd();

        // Verificar iff del call
        boolean hasSrc2 = true;
        System.out.println("Llegue a este punto de comparacion");
        if (ax.getNombre().equals("st") == false) {
            //Saber si es un inmediato

        }

        if (hasSrc1 == true && src1 == dest) {
            return true;

        } else if (hasSrc2 == true && src2 == dest) {
            return true;

        }

        return false;
    }

    public ArrayList<Instruccion> errorOrden(ArrayList<Instruccion> instrucciones) {
        for (int i = 0; i < instrucciones.size(); i++) {
            Instruccion aux = instrucciones.get(i);
            System.out.println("Conflictos de la Instruccion: " + aux.getInstruccion());
            if ((i + 3) < instrucciones.size()) {
                for (int j = 1; j < 4; j++) {
                    boolean retorno = conflicto(instrucciones.get(i + j), aux);
                    if (retorno) {
                        System.out.println("InsertarNop");
                        instrucciones = insertarNop(instrucciones);
                    }
                    System.out.println("Conflicto con " + instrucciones.get(i + j) + " : " + retorno);
                }
            }

        }
        return instrucciones;

    }

    public ArrayList<Instruccion> insertarNop(ArrayList<Instruccion> instrucciones) {
        for (int i = 0; i < instrucciones.size(); i++) {
            Instruccion ax = instrucciones.get(i);
            if (ax instanceof Nop) {
                if ((i + 1) < instrucciones.size()) {
                    instrucciones.add(i + 1, ax);
                    return instrucciones;
                }

            }
        }
        return null;
    }

    /**
     * Funcion que reordenara el codigo en caso de encontrar algun problema.
     *
     * @param instrucciones
     * @return
     */
    public ArrayList<Instruccion> reOrdenar(ArrayList<Instruccion> instrucciones) {

        //Primero creamos 2 listas que serviran como separacion entre los posibles not
        ArrayList<Instruccion> inferior = new ArrayList();

        // Recorremos la lista de instrucciones
        for (int i = 0; i < instrucciones.size(); i++) {
            Instruccion aux = instrucciones.get(i); // Guardamos en una variable auxiliar la instruccion actual
            boolean raw = noError(aux); // Llamamos a la funcion no error, que nos evitara la funciones

            // Recorremos el arreglo de superior para ver si que la funcion no se agrege a si misma
            for (int j = 0; j < inferior.size(); j++) {
                if (inferior.get(j) == aux) {
                    raw = false;
                }
            }
            if (raw == true) {
                Registro r = aux.getRd();
                for (int j = 0; j < instrucciones.size(); j++) {
                    Instruccion a = instrucciones.get(j);
                    if (a.getR1() == r || a.getR2() == r) {
                        inferior.add(a);
                    }
                }
            }

        }

        // Ahora eliminamos las instrucciones que tenemos en superior
        for (int i = 0; i < inferior.size(); i++) {
            instrucciones.remove(inferior.get(i));
        }

        //Ahora agregamos los nop, solamente, si las instrucciones inferiores son mayores a 0
        if (inferior.size() > 0) {
            //Creamos el objeto nop;

            Nop nop = new Nop();
            nop.setInstruccion("nop");
            Etiqueta e = new Etiqueta();
            e.setNombre("nop");
            nop.setEtiqueta(e);

            int size = instrucciones.size() - 1;
            int indice = 0;
            switch (size) {
                case 0:
                    indice = 3;
                    break;
                case 1:
                    indice = 2;
                    break;
                case 2:
                    indice = 1;
                    break;
                default:
                    break;
            }

            for (int i = 0; i < indice; i++) {
                instrucciones.add(nop);
            }

        }

        for (int i = 0; i < inferior.size(); i++) {
            instrucciones.add(inferior.get(i));
        }

        //Ahora llamamos a la errorOrden, que se encarga de verificar y arreglar los errores que pudan
        //surgir ; espero :(
        if (inferior.size() > 0) {
            instrucciones = errorOrden(instrucciones);
        }
        return instrucciones;

    }

}
