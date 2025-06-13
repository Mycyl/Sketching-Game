package server;

import java.util.*;
import shared.GameConstants;

public class GameRoom {
    public String code;
    public List<ClientHandler> players = new ArrayList<>();
    public int currentRound = 1;
    public int totalRounds = GameConstants.DEFAULT_ROUNDS;
    public int currentDrawerIndex = -1;
    public boolean gameStarted = false;
    public String currentWord = "";
    public WordGenerator wordGenerator;

    public GameRoom(String code, WordGenerator wordGenerator) {
        this.code = code;
        this.wordGenerator = wordGenerator;
    }

    public ClientHandler getNextDrawer() {
        currentDrawerIndex = (currentDrawerIndex + 1) % players.size();
        return players.get(currentDrawerIndex);
    }
}
