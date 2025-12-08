package com.example.lecture23_eventhandlingjavafx.chapter15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandleEvent extends Application {
    Button btCancel;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a pane and set its properties
    HBox pane = new HBox(10);
    pane.setAlignment(Pos.CENTER);

    Button btOK = new Button("OK");
    btOK.setPadding(new Insets(5, 20, 5, 20));
    OKHandlerClass handler1 = new OKHandlerClass();

//    btOK.setOnAction(handler1);

//      btOK.setOnAction(new EventHandler<ActionEvent>() {
//          public void handle(ActionEvent ae) {
//              btCancel.setDisable(true);
//              System.out.println("OK button clicked");
//          }
//      });

      btOK.setOnAction(e ->  System.out.println("OK button clicked"));

    btCancel = new Button("Cancel");
    CancelHandlerClass handler2 = new CancelHandlerClass();
    btCancel.setOnAction(handler2);
    pane.getChildren().addAll(btOK, btCancel);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("HandleEvent"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

    class OKHandlerClass implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            btCancel.setDisable(true);
            System.out.println("OK button clicked");
        }
    }
} 


class CancelHandlerClass implements EventHandler<ActionEvent> {
  @Override
  public void handle(ActionEvent e) {
        Alert alertBox = new Alert(Alert.AlertType.ERROR);
        alertBox.setContentText("Beware: You pressed the cancel button!");
        alertBox.setHeaderText("Cancel alert");
        alertBox.setTitle("Alert box");
        alertBox.showAndWait();
        System.out.println("Cancel button clicked");

   }
}
