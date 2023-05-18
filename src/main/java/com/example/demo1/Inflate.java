package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

public class Inflate extends Application {

    @Override
    public void start(Stage stage) {

        // Initialize the nameTextField.
        AtomicReference<TextField> nameTextField = new AtomicReference<>(new TextField());

        // Create a button to read the person object from the file.
        Button readButton = new Button("Read");
        readButton.setOnAction(event -> {

            // Read the person object from the file.
            try {
                com.example.demo1.Person time;
                FileInputStream fis = new FileInputStream("person.ser");
                ObjectInputStream in = new ObjectInputStream(fis);
                time = (com.example.demo1.Person) in.readObject();
                in.close();

                // Update the text field with the getDetails() text.
                nameTextField.get().setText(time.getDetails());

                // Display the getDetails() text.
                System.out.println("Details: " + time.getDetails());
            } catch(IOException | ClassNotFoundException ex){
                ex.printStackTrace();
            }
        });

        // Create a label with instructions.
        Label instructionLabel = new Label("Click \"Read\" to display what is saved in the file person.ser");

        // Create a VBox to layout the controls.
        VBox layout = new VBox();
        layout.setSpacing(10); // Add some spacing between elements
        layout.setPadding(new Insets(10, 50, 50, 50)); // Add padding around the VBox
        layout.setAlignment(Pos.CENTER); // Center the controls in the VBox
        layout.getChildren().addAll(instructionLabel, readButton, nameTextField.get());

        // Create a scene and set it on the stage.
        Scene scene = new Scene(layout, 600, 400); // Make the scene bigger
        stage.setScene(scene);
        stage.setTitle("Person Reader"); // Add a title to the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
