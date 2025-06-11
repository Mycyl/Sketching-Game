package server;
// --- Client.java ---

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * A simple client that connects to a server, sends messages,
 * and receives responses.
 */

 /**
 * Handles a single player's connection to the server.
 * Receives messages from the client and responds accordingly.
 * Forwards draw events, guesses, and chat to all connected clients.
 */

// instance class
// One instance per connected player; handles individual communication.


public class ClientHandler {
    private static final String SERVER_ADDRESS = "localhost"; // Server IP address or hostname
    private static final int SERVER_PORT = 12345; // Server port number

    public static void main(String[] args) {
        System.out.println("Connecting to server at " + SERVER_ADDRESS + ":" + SERVER_PORT + "...");
        

        // Use a try-with-resources statement to ensure the socket and streams are closed
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // 'true' for auto-flush
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) { // For reading user input from console

            System.out.println("Connected to the server. Type 'bye' to exit.");

            String userInput;
            String serverResponse;

            // Loop to send messages and receive responses
            while (true) {
                System.out.print("Enter message: ");
                userInput = scanner.nextLine(); // Read user input

                out.println(userInput); // Send message to the server

                // Read response from the server
                serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);

                // If the user types "bye", close the client connection
                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } finally {
            System.out.println("Client disconnected.");
        }
    }
}
