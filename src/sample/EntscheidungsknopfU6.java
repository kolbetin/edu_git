/*
Author: Tina Kolbe
Version: 1.25
Date: 22.10.2020
 */

package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class EntscheidungsknopfErweiterung extends Application implements Serializable {

    ArrayList<String> antworten;
    Button klickmichbutton;
    Button savebutton;
    Button removebutton;
    TextField textField;
    Random random;
    FlowPane pane;
    VBox root;
    private Laden laden;
    private Speichern speichern;


    public void start(Stage primaryStage) {
        root = new VBox();
        BorderPane rootTop = new BorderPane();
        antworten = new ArrayList<>();
        pane = new FlowPane();
        klickmichbutton = new Button("Klick mich!");
        textField = new TextField();
        savebutton = new Button("Save");
        removebutton = new Button("Neue Liste");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("Datei");

        // Create MenuItems
        MenuItem loadItem = new MenuItem("Laden");
        loadItem.setOnAction(event -> {
            laden = new Laden();
            laden.laden();
            getLaden();
        });

        MenuItem saveItem = new MenuItem("Speichern");
        saveItem.setOnAction(event -> {
            speichern = new Speichern();
            speichern.speichereListe(antworten);
        });

        fileMenu.getItems().addAll(loadItem, saveItem);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu);

        root.getChildren().add(menuBar);
        root.getChildren().add(obereLeiste());
        root.getChildren().add(klickMichButton());

        // rootTop.setTop(menuBar);
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Entscheidungsknopf");
        primaryStage.show();
    }

    Pane klickMichButton() {
        klickmichbutton.setFont(Font.font(20));
        //btn.setStyle("-fx-font-weight: bold");
        klickmichbutton.setPrefWidth(500);
        klickmichbutton.setPrefHeight(250);

        pane.setPadding(new Insets(0, 0, 0, 0));
        pane.getChildren().add(klickmichbutton);

        klickmichbutton.setOnAction(event -> {
            ausgabeKlickmichbutton();
        });
        return pane;
    }

    private Pane obereLeiste() {
        final HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(15, 10, 10, 50));

        savebutton.setOnAction(event -> {
            listePrüfen();
            String clearfield = textField.getText().trim().replace("/", "");

            if (clearfield != null && !clearfield.isEmpty()) {
                if (listePrüfen() == -1) {
                    klickmichbutton.setText("Eintrag ist schon vorhanden!");
                } else {
                    antworten.add(textField.getText());
                    textField.clear();
                    System.out.println(antworten);
                }
            }

        });
        removebutton.setOnAction(event -> {
                    antworten.removeAll(antworten);
                    laden.liste.removeAll(laden.liste);
                    System.out.println("Liste ist leer");
                }
        );

        hBox.getChildren().addAll(
                new Text("Aktivität"), textField, savebutton, removebutton);
        return hBox;
    }
    /* test*/


    private String getAntworten() {
        random = new Random();
        int index = random.nextInt(antworten.size());
        return antworten.get(index);
    }

    private String getLaden() {
        random = new Random();
        int index = random.nextInt(laden.liste.size());
        return laden.liste.get(index);
    }


    private int listePrüfen() {
        String clearfield = textField.getText().trim().replace("/", "").toLowerCase();
        int index = 0;
        boolean suchen = true;
        if (clearfield != null && !clearfield.isEmpty()) {
            while (suchen && index < antworten.size()) {
                String suche = antworten.get(index);
                if (clearfield.equals(suche)) {
                    suchen = false;
                } else {
                    index++;
                    klickmichbutton.setText("Liste ausgeben!");
                }
            }
        } else {
            klickmichbutton.setText("Bitte valide Antwort eingeben!!");
            klickmichbutton.setAlignment(Pos.CENTER);
            klickmichbutton.setWrapText(true);
        }
        if (suchen) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void ausgabeKlickmichbutton() {
        if (antworten.size() > 0) {
            String antwort = getAntworten();
            klickmichbutton.setText(antwort);

        }
        if (laden.liste.size() > 0) {
            String laden = getLaden();
            klickmichbutton.setText(laden);
        } else {
            klickmichbutton.setText("Liste ist leer! Bitte etwas eingeben!");
            klickmichbutton.setWrapText(true);
        }
        pane.getChildren().add(klickmichbutton);
    }

}


