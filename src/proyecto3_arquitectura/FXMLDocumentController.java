/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3_arquitectura;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 *
 * @author sebar
 */
public class FXMLDocumentController implements Initializable {

    String label[] = {"add", "mov", "sum", "mul", "div", "cmp", "mod", "and", "or", "not", "lsr"
        + "asr", "lsl" + "nop", "ld", "sd", "beq", "bgt", "b", "call", "ret"};

    @FXML
    private TextArea consola;
    @FXML
    private TextArea numeros;

    private int numeroLinea = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consola.setStyle("-fx-font-size: 17");
        numeros.setStyle("-fx-font-size: 17");
        numeros.setText(Integer.toString(numeroLinea));
        Pattern p = Pattern.compile(""
                + "([a-z]{3}|[a-z]{2})\\s[r]{1}([0-9]{2}|[0-9]),(([r]{1}([0-9]{2}|[0-9]),"
                + "(([r]{1}([0-9]{2}|[0-9]))|[0-9]{2}))|(([r]{1}([0-9]{2}"
                + "|[0-9]))|([0-9]{2})|[0-9]{2}\\[[r]{1}([0-9]{2})\\]))");

        consola.setOnKeyReleased(e -> {
            if (e.getCode() == e.getCode().ENTER) {
                System.out.println("Salto de linea");
                numeroLinea++;
                numeros.setText(numeros.getText() + "\n" + numeroLinea);
                String last[] = consola.getText().split("\n");
                Matcher mat = p.matcher(last[last.length - 1]);
                boolean cadenaValida = mat.matches();
                if (cadenaValida == false) {
                    System.out.println("Cadena no valida");
                }
                System.out.println(last[last.length - 1]);
                String[] tokensX = last[last.length - 1].replaceAll("\\s+", "+").split("(?<=[-+*/()_])|(?=[-+*/()_])");
                System.out.println("Se imprimen todos los tokens de la lista.");
                for (String token : tokensX) {
                    System.out.println(token);
                }
                for (int i = 0; i < label.length; i++) {
                    if (tokensX[0].equals(label[i])) {
                        System.out.println("La etiqueta es " + label[i]);
                        if (label[i].equals("Mov")) {

                        }
                    }

                }
            }

        });
    }
}
