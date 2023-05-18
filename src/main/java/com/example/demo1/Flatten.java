package com.example.demo1;

import com.example.demo1.Person;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Flatten extends Application {

    @Override
    public void start(Stage stage) {

        // Create a label with instructions.
        Label instructionLabel = new Label("Enter the details and send it to the server by clicking \"Send\" button");

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
        Button serializeButton = new Button("Send");
        serializeButton.setOnAction(event -> {

            // Get the name, address, and age from the text fields.
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());

            // Create a Person object.
            Person person = new Person(name, address, age);

            // Serialize the person object and send it to the server.
            try {
                Socket socket = new Socket(InetAddress.getLocalHost(), 1234);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(person);
                out.close();
                System.out.println("Sent!");
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
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
        stage.setTitle("Person Sender"); // Add a title to the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
