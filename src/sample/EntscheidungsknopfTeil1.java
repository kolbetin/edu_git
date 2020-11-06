package sample;/*
Author: Tina Kolbe
Version: 1.15
Date: 22.10.2020
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Random;


public class EntscheidungsknopfTeil1 extends Application {

    ArrayList<String> antworten;
    Random random;
    Button btn;
    FlowPane pane ;

    public void start( Stage primaryStage) {
        pane = new FlowPane();
        btn = new Button();
        listeBefuellen();

        VBox root = new VBox();
        root.getChildren().add(klickMichButton());

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Entscheidungsknopf");
        primaryStage.show();

    }

    Pane klickMichButton() {
        pane.getChildren().add(btn);

        btn.setText("Klick mich!");
        btn.setFont(Font.font(30));
        btn.setPrefWidth(300);
        btn.setPrefHeight(200);

        btn.setOnAction(event -> {
            registerActionhandler();
        });
        return pane;
    }


    private void listeBefuellen() {
        antworten = new ArrayList<>();
        antworten.add ("Kino");
        antworten.add ("Spazieren gehen");
        antworten.add ("Kochen");
        antworten.add("Lesen");
        antworten.add("TV schauen");
        antworten.add("Schlafen");
        antworten.add("Freunde teffen");
        antworten.add("Wäsche waschen");
    }

    private String getAntworten(){
        random = new Random();
        int index = random.nextInt(antworten.size());
        return antworten.get(index);
    }

    private void registerActionhandler(){
        String antwort = getAntworten();
        btn.setText(antwort);
        pane.getChildren().add(btn);

    }

    public static void main(String[] args) {
        launch(args);
    }

}


