package com.example.javafx_helloworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        StackPane root = new StackPane();

        root.getChildren().add(new Button("Cancel"));
        root.getChildren().add(new Button("ok"));

        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(new Label("Name: "));
        hBox1.getChildren().add(new TextField(" "));

        HBox hBox2 = new HBox();
        hBox2.getChildren().add(new Label("Password: "));
        hBox2.getChildren().add(new TextField(" "));

        vBox.getChildren().addAll(hBox1, hBox2);
        vBox.getChildren().add(new Button("Submit"));

        Scene scene = new Scene(vBox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
