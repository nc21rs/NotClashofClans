package game_engine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import game_player_database.PlayerDataBase;

/**
 * This class represents a runnable task that each client thread executes.
 * It controls communication between the server and a single client.
 */
public class ThreadController implements Runnable {
    private Socket clientSocket;
    private GameEngineControl engineControl;
    private PlayerDataBase playerDataBase;

    // each controller has its own client socket
    public ThreadController(Socket socket, GameEngineControl control) {
        this.clientSocket = socket;
        this.engineControl = control;
        this.playerDataBase = new PlayerDataBase();
    }

    @Override
    public void run() {

        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

            // authenticate client before allowing requests to be sent to the server
            boolean loginOk = authenticatePlayer(in, out);

            if (loginOk) {
                out.writeObject("Login successful.");
                out.flush();
            } else {
                out.writeObject("Login failed. Invalid username or password.");
                out.flush();
                return; // end the thread if authentication fails
            }

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

    /**
     * This method authenticates the player by reading the username and password.
     * Very simple handshake-like protocol.
     */
    public boolean authenticatePlayer(ObjectInputStream in, ObjectOutputStream out)
            throws IOException, ClassNotFoundException {

        out.writeObject("Please enter your username:");
        out.flush();
        String username = (String) in.readObject();

        if (username == null) {
            out.writeObject("Invalid username. Authentication failed.");
            out.flush();
            return false;
        }

        out.writeObject("Please enter your password:");
        out.flush();
        String password = (String) in.readObject();

        if (password == null) {
            out.writeObject("Invalid password. Authentication failed.");
            out.flush();
            return false;
        }

        boolean authenticated = playerDataBase.playerAuthentication(username, password);

        if (authenticated) {
            out.writeObject("Authentication successful.");
            out.flush();
            return true;
        } else {
            out.writeObject("Authentication failed. Invalid username or password.");
            out.flush();
            return false;
        }

    }

}