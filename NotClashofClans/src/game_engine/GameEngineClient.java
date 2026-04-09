import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

package game_engine;

public class GameEngineClient {

    public static void main(String[] args) {
        String hostname = "localhost"; // Change to server IP if not running locally
        int port = 4444;

        try (Socket client = new Socket(hostname, port);
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                Scanner scanner = new Scanner(System.in)) {

                    
            System.out.println("Connected to the Game Engine Server, communicating with " + hostname + ":" + port);

            boolean run = true;

            while (run){
                System.out.println("Enter a command:");
                String command = scanner.nextLine().trim();

                if (command.equals("quit")) {
                    run = false;
                    System.out.println("Quitting Game...");
                } else {
                    // TO-DO: MAYBE CREATE SPE PACKET IF WE NEED PAYLOAD TO BE SENT AS WELL AS COMMAND
                    // FOR NOW JUST SEND THE COMMAND AS A STRING, AND SERVER CAN DECIDE WHAT TO DO WITH IT

                    // Send command to server
                    out.writeObject(command);
                    out.flush();

                    // Wait for response from server
                    Object response = in.readObject();
                    System.out.println("Server Response: " + response);

            
                }
            }

        }   catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}