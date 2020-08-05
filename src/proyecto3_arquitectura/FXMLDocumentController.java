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
import Logica.Instruccion;
import Logica.Offset;
import Logica.Registro;
import SetInstrucciones.B;
import SetInstrucciones.Beq;
import SetInstrucciones.Bgt;
import SetInstrucciones.Cmp;
import SetInstrucciones.Mov;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    double x, y;

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
        Font.loadFont(FXMLDocumentController.class.getResource("/Recursos/MonospaceBold.ttf").toExternalForm(), 20);
        
        Text text2 = new Text(" little bold blue text");
        text2.setFill(Color.BLUE);

        // Arreglar codigo , Se ve HORRIBLLE, (INSERTE CANCION DEL REY LEON 2)
        inicializarRegistros();
        //consola.setStyle("-fx-font-size: 17");
        numeros.setStyle("-fx-font-size: 17");
        numeros.setText(Integer.toString(numeroLinea));
        consola.setOnKeyReleased(e -> {
            if (e.getCode() == e.getCode().BACK_SPACE) {
                String last[] = consola.getText().split("\n");
                ArrayList<String> ins = new ArrayList();
                numeros.clear();
                //Remover lineas vacias;
                for (int i = 0; i < last.length; i++) {
                    ins.add(last[i]);
                    
                }
                for (int i = 0; i < ins.size(); i++) {
                    numeros.setText(numeros.getText() + "\n" + (i + 1));
                }
                numeroLinea = ins.size() + 1;
                
            }
            if (e.getCode() == e.getCode().ENTER) {
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
                instrucciones.add(of);
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
        boolean valida = CrearEtiqueta.crear(etiqueta, tokensX[0]);
        if (valida == true) {
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
    
}
