package game_engine;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents the a basic server for the game.
 * Listen for a client connection and handles basic communication.
 * Useful prints are also included for easier debugging.
 */

public class GameEngineServer {

    private static final int POOL_SIZE = 6;

    public static void main(String[] args) {

        // Initialize Game Engine Controller
        GameEngineControl engineControl = new GameEngineControl();

        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

        try (ServerSocket server = new ServerSocket(4444)) {

            System.out.println("Game Engine Server is running on port 4444 ");

            while (true) {
                Socket client = server.accept();
                System.out.println("Client connected and IP address is: " + client.getInetAddress() + "\n"
                        + "Client Port Number is: " + client.getPort());
                System.out.println();

                ThreadController controller = new ThreadController(client, engineControl);
                pool.execute(controller);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server stopped");
            pool.shutdown();
        }
    }
}