package game_engine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class represents a runnable task that each client thread executes.
 * It controls communication between the server and a single client.
 */
public class ThreadController implements Runnable {
    private Socket clientSocket;
    private GameEngineControl engineControl;

    // each controller has its own client socket
    public ThreadController(Socket socket, GameEngineControl control) {
        this.clientSocket = socket;
        this.engineControl = control;
    }

    @Override
    public void run() {

        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

            // communication loops between server and client
            boolean run = true;
            while (run) {
                Object request = in.readObject();

                // check if request is valid
                if (!(request instanceof String)) {
                    out.writeObject("Invalid request format. Please send a string.");
                    out.flush();
                    continue;
                }

                String requestStr = ((String) request).trim();
                System.out.println("Request Received from port " + clientSocket.getPort() + ": " + requestStr);

                if (requestStr.equalsIgnoreCase("quit")) {
                    // quitting game, close the connection
                    run = false;
                    out.writeObject("Quitting Game...");
                    out.flush();
                } else {
                    // send response back to client
                    String response = engineControl.processRequest(requestStr);
                    out.writeObject(response);
                    out.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Client connection closed for port " + clientSocket.getPort() + ".");
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}