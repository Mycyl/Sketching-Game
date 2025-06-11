package shared;

/**
 * Serializable object used to transfer data between client and server.
 * Can encapsulate chat messages, drawing commands, player actions, etc.
 */

// instance class
// Serializable object for transferring game events/data.

public class Message {

    private String type; // Type of message (e.g., "chat", "draw", "action")
    private String content; // Content of the message (e.g., text, drawing data)

    public Message (String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String toString () {
        return "Message{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    
}
