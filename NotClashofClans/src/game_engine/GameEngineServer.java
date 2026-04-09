package game_engine;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * This class represents the a basic server for the game. 
 * Listen for a client connection and handles basic communication. 
 * Useful prints are also included for easier debugging, 
 * 
 * TO-DO: NOT TOO SURE IF ALL PRINTS WILL BE NEEDED.
 */

public class GameEngineServer {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(4444)){
            System.out.println("Game Engine Server is running on port 4444...");

            Socket client = server.accept();
            System.out.println("Client connected and IP address is: " + client.getInetAddress() + "\n" + "Client Port Number is: " + client.getPort());

            System.out.println();
            // Here you would typically start a new thread to handle the client's requests
            // For example:
            // new Thread(new ClientHandler(client)).start();

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
