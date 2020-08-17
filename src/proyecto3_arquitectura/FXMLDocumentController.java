/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3_arquitectura;

import Acciones.Guardar;
import Logica.CrearEtiqueta;
import Logica.CrearInstruccion;
import Logica.Etiqueta;
import Logica.Funciones;
import Logica.Instruccion;
import Logica.Offset;
import Logica.Registro;
import SetInstrucciones.B;
import SetInstrucciones.Beq;
import SetInstrucciones.Bgt;
import SetInstrucciones.Call;
import SetInstrucciones.Cmp;
import SetInstrucciones.Mov;
import SetInstrucciones.Nop;
import SetInstrucciones.Ret;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author sebar
 */
public class FXMLDocumentController implements Initializable {

    //Variables
    public ObservableList<Registro> registros = FXCollections.observableArrayList();
    private int numeroLinea = 1;
    private ArrayList<Instruccion> instrucciones = new ArrayList();
    private double x, y;

    //Variables FX
    @FXML
    private TextArea consola;
    @FXML
    private TextArea numeros;
    @FXML
    private TableView<Registro> tablaRG;
    @FXML
    private TableColumn<Registro, String> colregistros;
    @FXML
    private TableColumn<Registro, Integer> columnValores;
    @FXML
    private Button ejecutar;
    @FXML
    private Button nuevo;
    @FXML
    private Label nombre;

    /**
     * Metodo que se encarga de inicializar los registros dentro del programam
     * todos los registros comienzan en 0, se incluyen 16 registros del 0 al 15
     * mas los 2 variables de comparacion, luego de inicializar carga todo a la
     * tableview para mostrar los valores.
     */
    public void inicializarRegistros() {
        for (int i = 0; i < 16; i++) { // Se crean los 16 registros y se agregan a un arreglo
            Registro r = new Registro();
            r.setNombreRegistro("r" + i);
            registros.add(r);
        }

        // Ahora se crean los registros de compracion GT y E
        Registro flagE = new Registro();
        flagE.setNombreRegistro("e");
        Registro flagGT = new Registro();
        flagGT.setNombreRegistro("gt");

        // Se Agregan los registros al arreglo
        registros.add(flagE);
        registros.add(flagGT);

        // Ahora se ingresan los elementos a la tableview para ser visualizados por el usuario
        colregistros.setCellValueFactory(
                new PropertyValueFactory<>("nombreRegistro"));
        columnValores.setCellValueFactory(
                new PropertyValueFactory<>("valor"));
        tablaRG.setItems(registros);

    }

    /**
     * Metodo para poder si un string corrresponde a un numeros,
     * especificamente, para poder saber si la instruccion posee un valor
     * inmediato
     */
    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Metodo que se encarga de crear las instrucciones ingresadas por el
     * usuario
     *
     * @param tokens - Recibe la instruccion separa en tokens
     * @param set - Recibe una instruccion vacia, para poder ser rellenada
     * @param Label - Recibe la etiqueta de la instruccion por separado.
     * @return - Retorna un booleano para ver si la instruccion es valida.
     */
    public boolean crearInstruccion(String[] tokens, Instruccion set, Etiqueta Label) {

        ArrayList<String> reg = new ArrayList(); // Primero se crea un arraylist vacio
        for (String token : tokens) { // Ahora se guardan todos los valores de la instruccion
            if (!",".equals(token)) { // Se omiten las comas.
                reg.add(token);
            }
        }
        if (reg.size() == 2 || reg.size() == 3) { // Ahora se pregunta si es una instruccion con 2 o 3 registros
            int inmediato = 0;
            int contadorInmediatos = 0;

            for (int i = 0; i < reg.size(); i++) { // Ahora se busca si existe algun valor inmediato
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

    /**
     * Metodo que inicializa la app
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Se pide al usuario ingresar un nombre para el archivo.
        Font.loadFont(FXMLDocumentController.class.getResource("/Recursos/MonospaceBold.ttf").toExternalForm(), 20);

        // Arreglar codigo , Se ve HORRIBLLE, (INSERTE CANCION DEL REY LEON 2)
        inicializarRegistros();
        //consola.setStyle("-fx-font-size: 17");
        numeros.clear();

        numeros.setStyle("-fx-font-size: 17");
        numeros.setText(Integer.toString(numeroLinea));

        consola.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.BACK_SPACE) {
                String last[] = consola.getText().split("\n");
                ArrayList<String> ins = new ArrayList();
                //Remover lineas vacias;
                for (String last1 : last) {
                    ins.add(last1);
                }
                for (int i = 0; i < ins.size(); i++) {
                    numeros.setText(numeros.getText() + "\n" + (i + 1));
                }
                numeroLinea = ins.size() + 1;

            }

            if (e.getCode() == KeyCode.ENTER) {
                numeroLinea++;
                numeros.setText(numeros.getText() + "\n" + numeroLinea);
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
        instruccion = instruccion.replace("\t", "");
        boolean cadenaValida = mat.matches();
        if (cadenaValida == false) {

            if (instruccion.equals("ret")) {
                Ret retorno = new Ret();
                Etiqueta etiqueta = new Etiqueta();
                etiqueta.setNombre("ret");
                retorno.setEtiqueta(etiqueta);
                retorno.setInstruccion("ret");
                instrucciones.add(retorno);

            }
            Pattern offsetFormat = Pattern.compile("\\.[a-z]+\\:");
            Matcher m = offsetFormat.matcher(instruccion);
            boolean valido = m.matches();

            if (valido) {
                Instruccion of = new Offset();
                of.setInstruccion(instruccion);
                instrucciones.add(of);
            } else {
                Pattern b = Pattern.compile("b\\.[a-z]+");
                Matcher mb = b.matcher(instruccion);
                boolean validob = mb.matches();
                B salto = new B();
                if (validob) {
                    String remove = instruccion.substring(1);
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
                            jump.setOffset(remove);
                            instrucciones.add(jump);
                        } else {
                            b = Pattern.compile("call\\.[a-z]+");
                            mb = b.matcher(instruccion);
                            validobe = mb.matches();
                            Call llamada = new Call();

                            if (validobe) {
                                String remove = instruccion.substring(4);
                                llamada.setOffset(remove);
                                instrucciones.add(llamada);
                            }

                        }

                    }

                }

            }
        }
        String[] tokensX = instruccion.replaceAll("\\s+", "+").split("(?<=[-+*/()_])|(?=[-+*/()_])");
        Etiqueta etiqueta = new Etiqueta();
        boolean valida = CrearEtiqueta.crear(etiqueta, tokensX[0]);
        if (valida == true && (!etiqueta.getNombre().equals("ret"))) {
            String[] tokens = tokensX[2].replaceAll("\\s+", "").split("(?<=[,])|(?=[,])");
            Instruccion set = CrearInstruccion.creaInstruccion(etiqueta.getNombre());

            boolean instruccionValida = crearInstruccion(tokens, set, etiqueta);

            if (instruccionValida) {
                System.out.println("Linea de codigo completamente valida");
                set.setInstruccion(instruccion);
                instrucciones.add(set);

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
            ((Cmp) set).setMayor(registros.get(17));
            ((Cmp) set).setIgual(registros.get(16));

        }
        set.ejecutar();
        System.out.println("Set: " + set.getInstruccion());
        if ((set.getInstruccion().equals("nop")) == false) {
            if (set instanceof Offset == false) {
                for (int i = 0; i < registros.size(); i++) {
                    if (set.rd.getNombreRegistro().equals(registros.get(i).getNombreRegistro())) {
                        registros.get(i).setValor(set.rd.getValor());
                    }
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
        for (String last1 : last) {
            if (!last1.equals("")) {
                ins.add(last1);
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
        }

        //Ciclo para crear la funciones de y asi llamarlas con el call.offset
        for (int i = 0; i < instrucciones.size(); i++) {
            Instruccion aux = instrucciones.get(i);
            if (aux instanceof Offset) {
                for (int j = i + 1; j < instrucciones.size(); j++) {
                    if (instrucciones.get(j) != aux) {
                        if (instrucciones.get(j) instanceof Offset) {
                            j = instrucciones.size();
                            ((Offset) aux).setRetorno(false);
                            instrucciones.set(i, aux);
                        } else {
                            if (instrucciones.get(j) instanceof Ret) {
                                ((Offset) aux).addBloque(instrucciones.get(j));
                                ((Offset) aux).setRetorno(true);
                                j = instrucciones.size();

                            } else {
                                ((Offset) aux).addBloque(instrucciones.get(j));
                                ((Offset) aux).setRetorno(false);

                            }

                        }

                    }
                }

            }
            instrucciones.set(i, aux);

        }

        if (instrucciones.size() == ins.size()) {

            boolean sinFunciones = false; // boolean para detectar si exixste alguna funcion tipo .loop

            // se crearan removeran aquellas funciones, que ya se encuentren dentro de un .algo. para que
            // solo se ejecuten cuando se llame a la funcion
            for (int i = 0; i < instrucciones.size(); i++) {
                Instruccion aux = instrucciones.get(i);
                if (aux instanceof Offset) {
                    System.out.println("Entre a esto");
                    sinFunciones = true;
                    if (((Offset) aux).isRetorno()) {
                        for (int j = 0; j < ((Offset) aux).getBloque().size(); j++) {
                            Instruccion b = ((Offset) aux).getBloque().get(j);
                            instrucciones.remove(b);

                        }

                    }
                }
            }

            // En ese punto todas las funciones ya son utilizables, ahora hay que insertar los nop
            // La version anterior funcionaba, pero al incluir los offset/funciones, comenzo a tirar errores.
            // Ahora por lo tanto hay dos posibles casos
            //CASO 1, sin funciones
            System.out.println("SinFuncion" + sinFunciones);
            if (sinFunciones == false) {
                System.out.println("Logre Entrar a los NOPS");

                Funciones f = new Funciones();
                instrucciones = f.reOrdenar(instrucciones);
                consola.clear();
                // Caso 2, con funciones.
                // Tambien podria existir un posible caso 3 una comnbinacion del caso 1 y 2
                // imprimir nuevamente las instrucciones en la consola.
                for (int i = 0; i < instrucciones.size(); i++) {
                    Instruccion aux = instrucciones.get(i);
                    consola.setText(consola.getText() + aux.getInstruccion() + "\n");
                }
            }

            // Llamamos a la funcion que activa las instrucciones correctamente
            activarInstrucciones(instrucciones);

        }

    }

    public void activarInstrucciones(ArrayList<Instruccion> instruccion) {
        for (int i = 0; i < instruccion.size(); i++) {
            Instruccion aux = instruccion.get(i);
            if (aux instanceof Call) {
                for (int j = 0; j < instrucciones.size(); j++) {
                    if (instrucciones.get(j) instanceof Offset) {
                        if (instrucciones.get(j).getInstruccion().contains(((Call) aux).getOffset())) {
                            Offset a = (Offset) instrucciones.get(j);
                            System.out.println("LLAMANDO A LA FUNCION");
                            activarInstrucciones(a.getBloque());

                        }

                    }

                }

            } else if (aux instanceof Ret) {
                System.out.println("Hay Retorno, Ahora me voy , adiosion");

            } else if (aux instanceof B) {
                if (((B) aux).isUsado() == false) {
                    System.out.println("Entre al Salto");
                    i = ((B) aux).salto(instruccion);
                    System.out.println("El indice es igual a : " + i);
                    ((B) aux).setUsado(true);
                }
            } else if (aux instanceof Beq) {
                if (((Beq) aux).isUsado() == false) {
                    System.out.println("Entre al Salto");
                    int valor = ((Beq) aux).salto(instruccion);
                    if (valor != -1) {
                        i = valor;

                    }
                    System.out.println("El indice es igual a : " + i);
                    ((Beq) aux).setUsado(true);
                }

            } else if (aux instanceof Bgt) {
                if (((Bgt) aux).isUsado() == false) {
                    System.out.println("Entre al Salto");
                    int valor = ((Bgt) aux).salto(instruccion);
                    if (valor != -1) {
                        i = valor;

                    }
                    System.out.println("El indice es igual a : " + i);
                    ((Bgt) aux).setUsado(true);
                }
            } else {
                System.out.println("Entre a activar funcion de valores");
                actualizarValors(instruccion.get(i));
            }
        }

    }

    @FXML
    private void arrastar(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    @FXML
    private void presionar(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();

    }

    @FXML
    private void cerrar(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimizar(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    private void guardar(ActionEvent event) {
        String last[] = consola.getText().split("\n");
        ArrayList<String> ins = new ArrayList();
        //Remover lineas vacias;
        for (int i = 0; i < last.length; i++) {
            ins.add(last[i]);
        }
        Guardar guardar = new Guardar();
        guardar.pasarATXT(ins);
    }

    @FXML
    private void nuevoCodigo(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nombre del archivo: ");
        dialog.setHeaderText("");
        dialog.setContentText("Ingrese el nombre del archivo");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String respuesta = result.get();
            nombre.setText(respuesta);
        }
    }

    @FXML
    private void cargarCodigo(ActionEvent event) {
    }

}
