/*
Author: Tina Kolbe
Version: 1.25
Date: 22.10.2020
 */

package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class EntscheidungsknopfErweiterung extends Application implements Serializable {

    ArrayList<String> antworten;
    Button klickmichbutton;
    Button savebutton;
    TextField textField;
    Random random;
    FlowPane pane ;
    VBox root;


    public void start( Stage primaryStage) {
        root = new VBox();
        BorderPane rootTop = new BorderPane();
        antworten = new ArrayList<>();
        pane = new FlowPane();
        klickmichbutton = new Button("Klick mich!");
        textField = new TextField();
        savebutton = new Button("save");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("Datei");

        // Create MenuItems
        MenuItem loadItem = new MenuItem("Laden");
        loadItem.setOnAction(event -> {
            ladeListe();
        });

        MenuItem saveItem = new MenuItem("Speichern");
        saveItem.setOnAction(event -> {
            speichereListe();
        });

        fileMenu.getItems().addAll(loadItem,saveItem);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu);

        root.getChildren().add(menuBar);
        root.getChildren().add(obereLeiste());
        root.getChildren().add(klickMichButton());

       // rootTop.setTop(menuBar);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Entscheidungsknopf");
        primaryStage.show();
    }

    Pane klickMichButton() {
        klickmichbutton.setFont(Font.font(20));
        //btn.setStyle("-fx-font-weight: bold");
        klickmichbutton.setPrefWidth(400);
        klickmichbutton.setPrefHeight(250);

        pane.setPadding(new Insets(0, 0, 0, 0));
        pane.getChildren().add(klickmichbutton);

        klickmichbutton.setOnAction(event -> {
            ausgabeKlickmichbutton();
        });
        return pane;
    }
    private Pane obereLeiste(){
        final HBox hBox = new HBox(5);
        hBox.setPadding( new Insets(10,10,10,50));

        savebutton.setOnAction(event -> {
            listePrüfen();
            String clearfield = textField.getText().trim().replace("/","");

            if (clearfield != null && !clearfield.isEmpty()  ) {
                if (listePrüfen()==-1) {
                    klickmichbutton.setText("Eintrag ist schon vorhanden!");
                }
                else {
                    antworten.add(textField.getText());
                    textField.clear();
                    gibListe();
                }
            }



        });
        hBox.getChildren().addAll(
                new Text("Tat"), textField, savebutton);
        return hBox;

    }


    private String getAntworten(){
        random = new Random();
        int index = random.nextInt(antworten.size());
        return antworten.get(index);
    }

    private void gibListe(){
        for (String k : antworten){
            System.out.println(k);
        }
    }

    private int listePrüfen() {
        String clearfield = textField.getText().trim().replace("/","").toLowerCase();
        int index = 0;
        boolean suchen = true;
        if (clearfield != null && !clearfield.isEmpty()) {
            while (suchen && index < antworten.size()) {
                String suche = antworten.get(index);
                if (clearfield.equals(suche)) {
                    suchen = false;
                }
                else {
                    index++;
                    klickmichbutton.setText("Liste ausgeben!");
                }
            }
        }
        else {
            klickmichbutton.setText("Bitte valide Antwort eingeben!!");
            klickmichbutton.setAlignment(Pos.CENTER);
            klickmichbutton.setWrapText(true);
        }
        if (suchen){
            return 1;
        }
        else
        {
            return -1;
        }
    }


    public void speichereListe() {
        try (OutputStream fos = new FileOutputStream("src/antworten.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(antworten);
            System.out.println("Liste gespeichert: " + antworten);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void ladeListe() {
        try {
            ArrayList liste = null;
            InputStream fis = new FileInputStream("src/antworten.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

             liste = (ArrayList) ois.readObject();
             System.out.println("Liste geladen " + liste);
             ois.close();

        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
    private void ausgabeKlickmichbutton(){
        if (antworten.size()>0 ) {
            String antwort = getAntworten();
            klickmichbutton.setText(antwort);
        }
        else {
            klickmichbutton.setText("Liste ist leer! Bitte etwas eingeben!");
            klickmichbutton.setWrapText(true);
        }
        pane.getChildren().add(klickmichbutton);
    }

}

