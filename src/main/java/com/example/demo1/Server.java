package com.example.demo1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Integer PORT = 1234;

    public static void main(String[] args) {
        new Server().runServer();
    }

    private void runServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + PORT);
            System.exit(-1);
        }

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted to: " + clientSocket.getInetAddress());
                new Thread(() -> { // multi - connection server
                    try {
                        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.ser"));
                        Object inputObj;
                        try {
                            while ((inputObj = in.readObject()) != null) {
                                com.example.demo1.Person person = (com.example.demo1.Person) inputObj;
                                System.out.println("Received person object: " + person.getDetails());

                                // Serialize the person object to a file.
                                out.writeObject(person);
                                System.out.println("Saved!");
                            }
                        } catch (EOFException e) {
                            // Handle EOFException
                            System.out.println("End of stream reached");
                        }
                        in.close();
                        clientSocket.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.err.println("Failed in reading, writing");
                        System.exit(-1);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
