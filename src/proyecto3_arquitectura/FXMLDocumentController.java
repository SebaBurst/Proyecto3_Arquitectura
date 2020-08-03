/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3_arquitectura;

import Logica.Etiqueta;
import Logica.Instruccion;
import Logica.Offset;
import Logica.Registro;
import SetInstrucciones.Add;
import SetInstrucciones.And;
import SetInstrucciones.Asr;
import SetInstrucciones.B;
import SetInstrucciones.Beq;
import SetInstrucciones.Bgt;
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
import SetInstrucciones.Sub;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

/**
 *
 * @author sebar
 */
public class FXMLDocumentController implements Initializable {

    public ObservableList<Registro> registros = FXCollections.observableArrayList();

    String label[] = {"add", "mov", "sum", "mul", "div", "cmp", "mod", "and", "or", "not", "lsr"
        + "asr", "lsl" + "nop", "ld", "sd", "beq", "bgt", "b", "call", "ret"};

    private Offset ofActual = new Offset();
    private boolean ofActivo = false;
    @FXML
    private TextArea consola;
    @FXML
    private TextArea numeros;
    private int numeroLinea = 1;
    private int instruccionesValidas = 0;
    private ArrayList<Instruccion> instrucciones = new ArrayList();
    @FXML
    private TableView<Registro> tablaRG;
    @FXML
    private TableColumn<Registro, String> colregistros;
    @FXML
    private TableColumn<Registro, Integer> columnValores;
    @FXML
    private Button ejecutar;

    //Nota implementar patron factory
    public Instruccion tipoEtiqueta(String nombre) {
        if (nombre.equals("add")) {
            return new Add();
        } else if (nombre.equals("sub")) {
            return new Sub();

        } else if (nombre.equals("mul")) {
            return new Mul();

        } else if (nombre.equals("div")) {
            return new Div();

        } else if (nombre.equals("mov")) {
            return new Mov();

        } else if (nombre.equals("mod")) {
            return new Mod();

        } else if (nombre.equals("cmp")) {
            return new Cmp();

        } else if (nombre.equals("and")) {
            return new And();

        } else if (nombre.equals("or")) {
            return new Or();

        } else if (nombre.equals("not")) {
            return new Not();

        } else if (nombre.equals("lsl")) {
            return new Lsl();

        } else if (nombre.equals("lsr")) {
            return new Lsr();

        } else if (nombre.equals("asr")) {
            return new Asr();

        } else if (nombre.equals("nop")) {
            return new Nop();
        } else if (nombre.equals("ld")) {

        } else if (nombre.equals("st")) {

        } else if (nombre.equals("beq")) {

        } else if (nombre.equals("bgt")) {

        } else if (nombre.equals("b")) {
            return new B();

        } else if (nombre.equals("call")) {

        } else if (nombre.equals("ret")) {

        }

        return null;

    }

    /// Nota: En una siguiente actualizacion, implementar el patron abstract Factory.
    //Metodo que crea la etiqueta del programa
    public Boolean crearEtiqueta(Etiqueta label, String nombre) {
        boolean existe = false;
        if (nombre.equals("add")) {
            label.setNombre(nombre);
            label.setOPC("00000");
            existe = true;

        } else if (nombre.equals("sub")) {
            label.setNombre(nombre);
            label.setOPC("00001");
            existe = true;
        } else if (nombre.equals("mul")) {
            label.setNombre(nombre);
            label.setOPC("00010");
            existe = true;
        } else if (nombre.equals("div")) {
            label.setNombre(nombre);
            label.setOPC("00011");
            existe = true;
        } else if (nombre.equals("mov")) {
            label.setNombre(nombre);
            label.setOPC("01001");
            existe = true;

        } else if (nombre.equals("mod")) {
            label.setNombre(nombre);
            label.setOPC("00100");
            existe = true;

        } else if (nombre.equals("cmp")) {
            label.setNombre(nombre);
            label.setOPC("00101");
            existe = true;
        } else if (nombre.equals("and")) {
            label.setNombre(nombre);
            label.setOPC("00110");
            existe = true;
        } else if (nombre.equals("or")) {
            label.setNombre(nombre);
            label.setOPC("00111");
            existe = true;
        } else if (nombre.equals("not")) {
            label.setNombre(nombre);
            label.setOPC("01000");
            existe = true;

        } else if (nombre.equals("lsl")) {
            label.setNombre(nombre);
            label.setOPC("01010");
            existe = true;

        } else if (nombre.equals("lsr")) {
            label.setNombre(nombre);
            label.setOPC("01011");
            existe = true;
        } else if (nombre.equals("asr")) {
            label.setNombre(nombre);
            label.setOPC("01100");
            existe = true;

        } else if (nombre.equals("nop")) {
            label.setNombre(nombre);
            label.setOPC("01101");
            existe = true;

        } else if (nombre.equals("ld")) {
            label.setNombre(nombre);
            label.setOPC("01110");
            existe = true;

        } else if (nombre.equals("st")) {
            label.setNombre(nombre);
            label.setOPC("01111");
            existe = true;

        } else if (nombre.equals("beq")) {
            label.setNombre(nombre);
            label.setOPC("10000");
            existe = true;
        } else if (nombre.equals("bgt")) {
            label.setNombre(nombre);
            label.setOPC("10001");
            existe = true;

        } else if (nombre.equals("b")) {
            label.setNombre(nombre);
            label.setOPC("10010");
            existe = true;

        } else if (nombre.equals("call")) {
            label.setNombre(nombre);
            label.setOPC("10011");
            existe = true;

        } else if (nombre.equals("ret")) {
            label.setNombre(nombre);
            label.setOPC("10100");
            existe = true;

        }

        return existe;
    }

    public void inicializarRegistros() {
        for (int i = 0; i < 15; i++) {
            Registro r = new Registro();
            r.setNombreRegistro("r" + i);
            registros.add(r);
        }
        Registro flagE = new Registro();
        flagE.setNombreRegistro("e");
        Registro flagGT = new Registro();
        flagGT.setNombreRegistro("gt");
        registros.add(flagE);
        registros.add(flagGT);

        colregistros.setCellValueFactory(
                new PropertyValueFactory<Registro, String>("nombreRegistro"));
        columnValores.setCellValueFactory(
                new PropertyValueFactory<Registro, Integer>("valor"));
        tablaRG.setItems(registros);

    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean crearInstruccion(String[] tokens, Instruccion set, Etiqueta Label) {

        ArrayList<String> reg = new ArrayList();
        for (int i = 0; i < tokens.length; i++) {
            if (!",".equals(tokens[i])) {
                reg.add(tokens[i]);

            }
        }

        for (int i = 0; i < reg.size(); i++) {
            System.out.println("R; " + reg.get(i));
        }

        if (reg.size() == 2 || reg.size() == 3) {
            int inmediato = 0;
            int contadorInmediatos = 0;

            for (int i = 0; i < reg.size(); i++) {
                if (isNumeric(reg.get(i))) {
                    inmediato = Integer.parseInt(reg.get(i));
                    contadorInmediatos++;
                    set.setInmediato(inmediato);
                }

            }

            //Primer elemento de la lista == registro de destino;
            boolean existe = false;
            int contadorr = 0;
            int contador2 = 0;
            // Comprobar que registro existe;
            for (int i = 0; i < registros.size(); i++) {
                if (registros.get(i).getNombreRegistro().equals(reg.get(0))) {
                    existe = true;
                    set.setRd(registros.get(i));

                }
                if (contadorInmediatos == 1 && !(set instanceof Mov)) {
                    if (registros.get(i).getNombreRegistro().equals(reg.get(1))) {
                        contadorr = 1;
                        set.setR1(registros.get(i));
                    }
                }
                if (contadorInmediatos == 0) {
                    if (reg.size() == 3) {
                        if (registros.get(i).getNombreRegistro().equals(reg.get(1))) {
                            contadorr = 1;
                            set.setR1(registros.get(i));
                        } else if (registros.get(i).getNombreRegistro().equals(reg.get(2))) {
                            existe = true;
                            contador2 = 1;
                            set.setR2(registros.get(i));

                        }

                    } else {
                        if (registros.get(i).getNombreRegistro().equals(reg.get(1))) {
                            existe = true;
                            contadorr = 1;
                            set.setR1(registros.get(i));

                        }

                    }

                }

            }
            if ((existe == true && contadorr == 1 && contador2 == 1) || (existe == true && contadorr == 1)
                    || (existe == true && contadorr == 1 && contadorInmediatos == 1) || (existe == true && contadorInmediatos == 1)) {
                set.setEtiqueta(Label);
                return true;

            } else {
                return false;
            }

        } else {
            System.out.println("Error");

        }
        //Validar que registros esten bien
        return false;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

        // Arreglar codigo , Se ve HORRIBLLE, (INSERTE CANCION DEL REY LEON 2)
        inicializarRegistros();
        consola.setStyle("-fx-font-size: 17");
        numeros.setStyle("-fx-font-size: 17");
        numeros.setText(Integer.toString(numeroLinea));
        consola.setOnKeyReleased(e -> {
            if (e.getCode() == e.getCode().ENTER) {

            }

        });
    }

    public void validarInstruccion(String instruccion) {
        Pattern p = Pattern.compile(""
                + "([a-z]{3}|[a-z]{2})\\s[r]{1}([0-9]{2}|[0-9]),(([r]{1}([0-9]{2}|[0-9]),"
                + "(([r]{1}([0-9]{2}|[0-9]))|[0-9]{2}))|(([r]{1}([0-9]{2}"
                + "|[0-9]))|([0-9]{2})|[0-9]{2}\\[[r]{1}([0-9]{2})\\]))");
        numeroLinea++;
        numeros.setText(numeros.getText() + "\n" + numeroLinea);
        Matcher mat = p.matcher(instruccion);
        boolean cadenaValida = mat.matches();
        if (cadenaValida == false) {
            System.out.println("Cadena no valida");
            Pattern offsetFormat = Pattern.compile("\\.[a-z]+\\:");
            Matcher m = offsetFormat.matcher(instruccion);
            boolean valido = m.matches();

            if (valido) {
                System.out.println("Es una Funcioncilla");
                Instruccion of = new Offset();
                of.setInstruccion(instruccion);
                ofActual = (Offset) of;
                instrucciones.add(of);
                instruccionesValidas++;
            } else {
                Pattern b = Pattern.compile("b\\.[a-z]+");
                Matcher mb = b.matcher(instruccion);
                boolean validob = mb.matches();
                B salto = new B();
                if (validob) {
                    String remove = instruccion.substring(1);
                    System.out.println("Remove: " + remove);
                    salto.setOffset(remove);
                    instrucciones.add(salto);
                } else {
                    b = Pattern.compile("beq\\.[a-z]+");
                    mb = b.matcher(instruccion);
                    boolean validobe = mb.matches();
                    Beq saltoe = new Beq();
                    saltoe.setR1(registros.get(15));
                    saltoe.setR2(registros.get(16));

                    if (validobe) {
                        String remove = instruccion.substring(3);
                        System.out.println("Remove: " + remove);
                        saltoe.setOffset(remove);
                        instrucciones.add(saltoe);
                    } else {
                        b = Pattern.compile("bgt\\.[a-z]+");
                        mb = b.matcher(instruccion);
                        validobe = mb.matches();
                        Bgt jump = new Bgt();
                        jump.setR1(registros.get(15));
                        jump.setR2(registros.get(16));

                        if (validobe) {
                            String remove = instruccion.substring(3);
                            System.out.println("Remove: " + remove);
                            jump.setOffset(remove);
                            instrucciones.add(jump);
                        }

                    }

                }

            }
        }
        System.out.println(instruccion);
        String[] tokensX = instruccion.replaceAll("\\s+", "+").split("(?<=[-+*/()_])|(?=[-+*/()_])");
        System.out.println("Se imprimen todos los tokens de la lista.");
        for (String token : tokensX) {
            System.out.println(token);
        }

        Etiqueta etiqueta = new Etiqueta();
        boolean valida = crearEtiqueta(etiqueta, tokensX[0]);
        if (valida == true) {
            String[] tokens = tokensX[2].replaceAll("\\s+", "").split("(?<=[,])|(?=[,])");
            Instruccion set = tipoEtiqueta(etiqueta.getNombre());

            boolean instruccionValida = crearInstruccion(tokens, set, etiqueta);

            if (instruccionValida) {
                System.out.println("Linea de codigo completamente valida");
                set.setInstruccion(instruccion);
                instrucciones.add(set);
                instruccionesValidas++;

            } else {
                System.out.println("Error en la siguiente linea : " + instruccion);

            }

        } else {
            System.out.println("Error en la linea ");

        }

    }

    public void actualizarValors(Instruccion set) {

        if (set instanceof Cmp) {
            System.out.println("Set rd: " + set.getRd().getNombreRegistro());
            ((Cmp) set).setMayor(registros.get(16));
            ((Cmp) set).setIgual(registros.get(15));

        }
        set.ejecutar();
        if (set instanceof Offset == false) {
            for (int i = 0; i < registros.size(); i++) {
                if (set.rd.getNombreRegistro().equals(registros.get(i).getNombreRegistro())) {
                    registros.get(i).setValor(set.rd.getValor());
                }
            }
        }
        tablaRG.refresh();

    }

    @FXML
    private void ejecutarCode(ActionEvent event) {
        instrucciones.clear();
        String last[] = consola.getText().split("\n");
        ArrayList<String> ins = new ArrayList();

        //Remover lineas vacias;
        for (int i = 0; i < last.length; i++) {
            if (!last[i].equals("")) {
                ins.add(last[i]);

            }
        }
        for (int i = 0; i < ins.size(); i++) {
            if (ins.get(i).equals(i)) {
                for (int j = i; j < ins.size(); j++) {
                    if (!ins.get(i).equals("ret")) {

                    }
                }

            }

            validarInstruccion(ins.get(i));

            System.out.println("Linea " + i + ": " + ins.get(i));
        }

        if (instrucciones.size() == ins.size()) {
            System.out.println("TODAS LAS INSTRUCCIONES VALIDAS");
            for (int i = 0; i < instrucciones.size(); i++) {
                Instruccion aux = instrucciones.get(i);
                if (aux instanceof B) {
                    if (((B) aux).isUsado() == false) {
                        System.out.println("Entre al Salto");
                        i = ((B) aux).salto(instrucciones);
                        System.out.println("El indice es igual a : " + i);
                        ((B) aux).setUsado(true);
                    }
                } else if (aux instanceof Beq) {
                    if (((Beq) aux).isUsado() == false) {
                        System.out.println("Entre al Salto");
                        int valor = ((Beq) aux).salto(instrucciones);
                        if (valor != -1) {
                            i = valor;

                        }
                        System.out.println("El indice es igual a : " + i);
                        ((Beq) aux).setUsado(true);
                    }

                } else if (aux instanceof Bgt) {
                    if (((Bgt) aux).isUsado() == false) {
                        System.out.println("Entre al Salto");
                        int valor = ((Bgt) aux).salto(instrucciones);
                        if (valor != -1) {
                            i = valor;

                        }
                        System.out.println("El indice es igual a : " + i);
                        ((Bgt) aux).setUsado(true);
                    }
                } else {
                    System.out.println("Entre a activar funcion de valores");
                    actualizarValors(instrucciones.get(i));
                }
            }
        }
    }
}
