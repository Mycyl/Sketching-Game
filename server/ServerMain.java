package server;
// --- Server.java ---

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import server.ServerBroadcaster;
import java.util.Scanner;

/**
 * Entry point for the server-side application.
 * Listens for incoming client connections and creates handlers for each.
 * Manages the overall game session lifecycle.
 */

// static class
// Contains main() method to start the server.

/**
 * A simple server that listens for client connections on a specified port,
 * receives messages, and echoes them back to the client.
 */
public class ServerMain {

    private static final int PORT = 12345; // Port number for the server
    private static ArrayList<Client> clients = new ArrayList<Client>(); // List to keep track of connected clients

    public static void main(String[] args) {
        System.out.println("Server started. Listening on port " + PORT + "...");

        // Use a try-with-resources statement to ensure the server socket is closed
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) { // Keep the server running indefinitely to accept multiple clients
                System.out.println("Waiting for a client to connect...");
                // Accept a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Handle the client communication in a separate thread
                // This allows the server to accept new clients while current clients are being served
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Inner class to handle communication with a single client.
     * Each client connection gets its own ClientHandler thread.
     */
    public static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out; // For sending data to the client
        private BufferedReader in; // For receiving data from the client
        private Scanner scan = new Scanner(System.in);

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                clients.add(new Client(socket.getInetAddress().getHostName(), socket));
            } catch (IOException e) {
                System.err.println("Failed to add client: " + e.getMessage());
                e.printStackTrace();
            }
        }

        public static ArrayList<Client> getClients() {
            return clients;
        }

        public static String processMessage(String message) {
            // Process the message as needed (e.g., parse commands, etc.)
            // For now, just return the message as is
            return message;
        }  

        public void run() {
            try {
                // Initialize input and output streams for the client socket
                out = new PrintWriter(clientSocket.getOutputStream(), true); // 'true' for auto-flush
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                // Read messages from the client until the client closes the connection
                // or sends an empty line (or specific "bye" message, etc.)
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client " + clientSocket.getInetAddress().getHostAddress() + ": " + inputLine);
                    System.out.print("Broadcast to all clients: ");
                    //ServerBroadcaster.broadcastMessage("Server Broadcast" + scan.nextLine());

                    //ServerBroadcaster.broadcastMessage("Client " + clientSocket.getInetAddress().getHostAddress() + ": " + inputLine);

                    // Echo the received message back to the client
                    out.println("Server received: " + inputLine);

                    // If the client sends "bye", close the connection for this client
                    if ("bye".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                }
                
                System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " disconnected.");

            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Ensure resources are closed
                try {
                    if (out != null) out.close();
                    if (in != null) in.close();
                    if (clientSocket != null && !clientSocket.isClosed()) {
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error closing resources: " + e.getMessage());
                }
            }
        }
    }

    public static class Client {
        private String name;
        private Socket socket;
        private PrintWriter out;

        public Client(String name, Socket socket) throws IOException {
            this.name = name;
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }

        public String getName() {
            return name;
        }

        public Socket getSocket() {
            return socket;
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        @Override
        public String toString() {
            return "Client{" +
                    "name='" + name + '\'' +
                    ", socket=" + socket +
                    '}';
        }
    }
}
