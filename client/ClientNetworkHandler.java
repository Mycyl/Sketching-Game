// package client;

// import shared.*;

// import java.io.*;
// import java.net.*;
// import java.util.Map;
// import java.util.function.Consumer;

// public class ClientNetworkHandler {
//     private ObjectOutputStream out;
//     private ObjectInputStream in;
//     private Socket socket;

//     public ClientNetworkHandler(String host, int port, Consumer<Message> onMessageReceived) throws IOException {
//         socket = new Socket(host, port);
//         out = new ObjectOutputStream(socket.getOutputStream());
//         in = new ObjectInputStream(socket.getInputStream());

//         new Thread(() -> {
//             try {
//                 while (true) {
//                     Object obj = in.readObject();
//                     if (obj instanceof Message message) {
//                         onMessageReceived.accept(message);
//                     }
//                 }
//             } catch (Exception e) {
//                 System.out.println("Disconnected from server.");
//             }
//         }).start();
//     }

//     public void sendMessage(MessageType type, Map<String, Object> payload) {
//         try {
//             out.writeObject(new Message(type, payload));
//             out.flush();
//         } catch (IOException e) {
//             System.out.println("Send failed: " + e.getMessage());
//         }
//     }

//     public void close() {
//     try {
//         if (in != null) {
//             in.close();
//         }
//         if (out != null) {
//             out.close();
//         }
//         if (socket != null && !socket.isClosed()) {
//             socket.close();
//         }
//     } catch (IOException e) {
//         System.err.println("Error closing client connection: " + e.getMessage());
//         e.printStackTrace();
//     }
// }

// }
