package server;
// --- Client.java ---
import java.io.*;
import java.net.*;
import shared.*;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;
    public GameRoom room;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message msg) throws IOException {
        out.writeObject(msg);
        out.flush();
    }

    public void run() {
        try {
            while (true) {
                Message msg = (Message) in.readObject();
                handle(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handle(Message msg) throws IOException {
        switch (msg.type) {
            case CREATE_ROOM:
                String code = (String) msg.data;
                room = ServerMain.gameManager.createRoom(code);
                room.players.add(this);
                username = msg.sender;
                send(new Message(MessageType.ROOM_JOINED, "SERVER", room.code));
                break;
            case JOIN_ROOM:
                code = (String) msg.data;
                GameRoom joinRoom = ServerMain.gameManager.getRoom(code);
                if (joinRoom != null) {
                    room = joinRoom;
                    room.players.add(this);
                    username = msg.sender;
                    send(new Message(MessageType.ROOM_JOINED, "SERVER", code));
                } else {
                    send(new Message(MessageType.ROOM_ERROR, "SERVER", "Room not found"));
                }
                break;
            case START_GAME:
                room.gameStarted = true;
                nextTurn();
                break;
            case SELECTED_WORD:
                room.currentWord = (String) msg.data;
                broadcastExcept(new Message(MessageType.CHAT_MESSAGE, "SERVER", "Clue: " + maskWord(room.currentWord)), this);
                break;
            case GUESS:
                String guess = (String) msg.data;
                if (guess.equalsIgnoreCase(room.currentWord)) {
                    broadcast(new Message(MessageType.CORRECT_GUESS, msg.sender, guess));
                } else {
                    broadcast(new Message(MessageType.CHAT_MESSAGE, msg.sender, guess));
                }
                break;
        }
    }

    private String maskWord(String word) {
        return word.replaceAll(".", "_");
    }

    private void broadcast(Message msg) throws IOException {
        for (ClientHandler player : room.players) {
            player.send(msg);
        }
    }

    private void broadcastExcept(Message msg, ClientHandler except) throws IOException {
        for (ClientHandler player : room.players) {
            if (player != except) player.send(msg);
        }
    }

    private void nextTurn() throws IOException {
        ClientHandler drawer = room.getNextDrawer();
        List<String> words = room.wordGenerator.getRandomWords(3);
        drawer.send(new Message(MessageType.DRAW_PROMPT, "SERVER", words));
    }
}