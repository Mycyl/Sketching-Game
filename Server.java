// --- Server.java ---

import java.io.*;
import java.net.*;

/**
 * A simple server that listens for client connections on a specified port,
 * receives messages, and echoes them back to the client.
 */
public class Server {
    private static final int PORT = 12345; // Port number for the server

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
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out; // For sending data to the client
        private BufferedReader in; // For receiving data from the client

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
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
}