package game_engine;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/**
 * This class represents the a basic server for the game.
 * Listen for a client connection and handles basic communication.
 * Useful prints are also included for easier debugging,
 * 
 * TO-DO: NOT TOO SURE IF ALL PRINTS WILL BE NEEDED.
 */

public class GameEngineServer {

    public static void main(String[] args) {

        // Initialize Game Engine Controller
        GameEngineControl engineControl = new GameEngineControl(); // server owns game engine control, which controls
                                                                   // the game engine model

        try (ServerSocket server = new ServerSocket(4444)) {
            System.out.println("Game Engine Server is running on port 4444...");

            Socket client = server.accept();
            System.out.println("Client connected and IP address is: " + client.getInetAddress() + "\n"
                    + "Client Port Number is: " + client.getPort());

            System.out.println();
            // Here you would typically start a new thread to handle the client's requests
            // For example:
            // new Thread(new ClientHandler(client)).start();
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            boolean run = true;

            while (run) {
                Object request = in.readObject();

                if (!(request instanceof String)) {
                    out.writeObject("Invalid request format. Please send a string.");
                    out.flush();
                    continue;
                }

                String requestStr = ((String) request).trim();
                System.out.println("Request Received: " + requestStr);

                if (requestStr.equalsIgnoreCase("quit")) {
                    run = false;
                    out.writeObject("Quitting Game...");
                    out.flush();
                } else {
                    // TO-DO: MAYBE CREATE SPE PACKET IF WE NEED PAYLOAD TO BE SENT AS WELL AS
                    // COMMAND
                    // FOR NOW JUST SEND THE COMMAND AS A STRING, AND SERVER CAN DECIDE WHAT TO DO
                    // WITH IT

                    String response = engineControl.processRequest(requestStr);

                    // Send response back to client
                    out.writeObject("Server received request: " + requestStr);
                    out.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                System.out.println("Closing server...");

                // TODO: CLOSE THE SOCKET HERE, BUT RIGHT NOW IT IS NOT ACCESSIBLE IN THIS SCOPE
                // THIS WILL BE CHANGE WHEN THREADS ARE IMPLEMENTED, EACH THREAD CAN CLOSE ITS
                // OWN SOCKET
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
