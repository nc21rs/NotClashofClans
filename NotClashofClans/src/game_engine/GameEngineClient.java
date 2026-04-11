package game_engine;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameEngineClient {

    public static void main(String[] args) {
        String hostname = "localhost"; // Change to server IP if not running locally
        int port = 4444;

        try (Socket client = new Socket(hostname, port);
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the Game Engine Server, communicating with " + hostname + ":" + port);

            // AUTHENTICATION PROCESS
            // get username and password for the authentication process
            Object response = in.readObject();
            System.out.println("Server: " + response);
            String username = scanner.nextLine();
            out.writeObject(username);
            out.flush();

            response = in.readObject();
            System.out.println("Server: " + response);
            String password = scanner.nextLine();
            out.writeObject(password);
            out.flush();

            // authentication response from server
            response = in.readObject();
            System.out.println("Server: " + response);

            // if not authenticated, end the connection with the server
            if (!((String) response).equals("Login successful")) {
                System.out.println("Not authenticated, Failed connection");
                return;
            }

            // COMMUNICATION WITH SERVER LOOP
            boolean run = true;
            while (run) {
                System.out.println("Enter a command:");
                String command = scanner.nextLine();

                out.writeObject(command);
                out.flush();

                // Wait for response from server
                Object serverResponse = in.readObject();
                System.out.println(serverResponse);

                if (command.equals("quit")) {
                    run = false;
                    System.out.println("Quitting Game");
                } 
            }

            System.out.println("Connection closed");

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}