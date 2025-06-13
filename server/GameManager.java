package server;

/**
 * Core controller of the game's logic on the server side.
 * Manages turns, rounds, word selection, and scoring.
 * Handles transitions between game phases and player rotations.
 */

// singleton or instance class (recommended singleton)
// Manages the game state, turns, round timing, and progression.

import java.util.*;

public class GameManager {
    private Map<String, GameRoom> rooms = new HashMap<>();
    private WordGenerator wordGenerator;

    public GameManager(String wordPath) throws IOException {
        this.wordGenerator = new WordGenerator(wordPath);
    }

    public GameRoom createRoom(String code) {
        GameRoom room = new GameRoom(code, wordGenerator);
        rooms.put(code, room);
        return room;
    }

    public GameRoom getRoom(String code) {
        return rooms.get(code);
    }
}