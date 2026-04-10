package game_engine;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/**
 * This class represents the a basic server for the game.
 * Listen for a client connection and handles basic communication.
 * Useful prints are also included for easier debugging.
 */

public class GameEngineServer {

    public static void main(String[] args) {

        // Initialize Game Engine Controller
        // Server owns the controller
        GameEngineControl engineControl = new GameEngineControl(); 
        

        try (ServerSocket server = new ServerSocket(4444)) {
            System.out.println("Game Engine Server is running on port 4444...");

            while (true){
                Socket client = server.accept();
                System.out.println("Client connected and IP address is: " + client.getInetAddress() + "\n"
                    + "Client Port Number is: " + client.getPort());

                // create new thread 
                ThreadController threadController = new ThreadController(client, engineControl);
                Thread clientThread = new Thread(threadController);
                clientThread.start();
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server stopped.");
        }
    }
}