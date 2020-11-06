package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



import javafx.scene.control.Label;


public class Main extends Application {


    public void start( Stage primaryStage) {
        Label l1 = new Label("Hallo");
        Label l2 = new Label("Welt");
        VBox root = new VBox();
        root.getChildren().add(l1);
        root.getChildren().add(l2);
        root.getChildren().add(createButton());
        root.getChildren().add(createButtonLambda());
        Scene scene = new Scene(root, 340, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();



    }

    Pane createButton() {
        final Button btn = new Button();
        btn.setText("Add 'Hello World' Label");
        final FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(7, 7, 7, 7));
        pane.getChildren().add(btn);
// ActionHandler registrieren
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().add(new Label("- Hello World! -"));
            }

        });
        return pane;
    }

    Pane createButtonLambda() {
        final Button btn = new Button();
        btn.setText("Add 'Hello World' Label");
        final FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(7, 7, 7, 7));
        pane.getChildren().add(btn);
// ActionHandler registrieren
        btn.setOnAction(event -> pane.getChildren()
                .add(new Label("- Hello World! -")));
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }


}

