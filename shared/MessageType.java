package shared;

/**
 * Enum that defines the types of messages exchanged in the game.
 * Examples: CHAT, DRAW, GUESS, WORD_PROMPT, SCORE_UPDATE, GAME_EVENT.
 */

// static enum
// Enumeration of different message types.

public class MessageType {

    public static final String CHAT = "CHAT";
    public static final String DRAW = "DRAW"; 
    public static final String GUESS = "GUESS"; 
    public static final String WORD_PROMPT = "WORD_PROMPT"; 
    public static final String SCORE_UPDATE = "SCORE_UPDATE";
    public static final String GAME_EVENT = "GAME_EVENT"; 
    
    private MessageType() {
        // Prevent instantiation
    }
    
}
