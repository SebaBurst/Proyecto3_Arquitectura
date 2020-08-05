/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author sebar
 */
public class Guardar {

    private Stage primaryStage;

    public void pasarATXT(ArrayList<String> instrucciones) {
        try {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter
                    = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File archivo = fileChooser.showSaveDialog(primaryStage);
            FileWriter escribir = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(escribir);
            bw.newLine();
            for (int i = 0; i < instrucciones.size(); i++) {
                escribir.write(instrucciones.get(i) + "\r\n");
            }

            instrucciones.clear();
            escribir.close();
        } catch (Exception e) {
            System.out.println("Error al escribir");
        }

    }
}
