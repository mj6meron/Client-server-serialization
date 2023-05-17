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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Flatten extends Application {

    @Override
    public void start(Stage stage) {

        // Create a label with instructions.
        Label instructionLabel = new Label("Enter the details and save it to file person.ser by clicking \"Save\" button");

        // Create a label for the name field.
        Label nameLabel = new Label("Name");

        // Create a text field for the name field.
        TextField nameTextField = new TextField();

        // Create a label for the address field.
        Label addressLabel = new Label("Address");

        // Create a text field for the address field.
        TextField addressTextField = new TextField();

        // Create a label for the age field.
        Label ageLabel = new Label("Age");

        // Create a text field for the age field.
        TextField ageTextField = new TextField();

        // Create a button to serialize the person object.
        Button serializeButton = new Button("Save");
        serializeButton.setOnAction(event -> {

            // Get the name, address, and age from the text fields.
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());

            // Create a Person object.
            com.company.Person person = new com.company.Person(name, address, age);

            // Serialize the person object to a file.
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.ser"));
                out.writeObject(person);
                out.close();
                System.out.println("Saved!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Clear the text fields.
            nameTextField.clear();
            addressTextField.clear();
            ageTextField.clear();
        });

        // Create a VBox to layout the controls.
        VBox layout = new VBox();
        layout.setSpacing(10); // Add some spacing between elements
        layout.setPadding(new Insets(10, 50, 50, 50)); // Add padding around the VBox
        layout.setAlignment(Pos.CENTER); // Center the controls in the VBox
        layout.getChildren().addAll(
                instructionLabel,
                nameLabel, nameTextField,
                addressLabel, addressTextField,
                ageLabel, ageTextField,
                serializeButton
        );

        // Create a scene and set it on the stage.
        Scene scene = new Scene(layout, 600, 400); // Make the scene bigger
        stage.setScene(scene);
        stage.setTitle("Person Saver"); // Add a title to the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
