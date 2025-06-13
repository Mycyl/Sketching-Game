package server;

import java.io.*;
import java.net.*;

public class ServerMain {
    public static GameManager gameManager;

    public static void main(String[] args) throws IOException {
        gameManager = new GameManager("assets/words.txt");
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started...");

        while (true) {
            Socket socket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandler.start();
        }
    }
}
