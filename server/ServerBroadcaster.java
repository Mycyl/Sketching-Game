package server;

import java.util.ArrayList;

/**
 * Utility for broadcasting messages to all connected clients.
 * Ensures synchronized state across players (chat, drawing, game updates).
 */

// static class
// Utility for sending messages to all clients.

public class ServerBroadcaster {
    private ServerBroadcaster() {}

    public static void broadcastMessage(String message) {
        // Get the list of clients from ServerMain
        ArrayList<ServerMain.Client> clients = ServerMain.ClientHandler.getClients();
        
        for (ServerMain.Client client : clients) {
            client.sendMessage(message);
        }

        System.out.println("Broadcasting message to " + clients.size() + " clients.");
        System.out.println("Broadcasting message to all clients: " + message +  
                           " (Total clients: " + clients.size() + ")");
    }
}
