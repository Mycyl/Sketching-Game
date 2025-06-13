package shared;

import java.io.Serializable;
import java.util.Map;

public class Message implements Serializable {
    public MessageType type;
    public String sender;
    public Object data;

    public Message(MessageType type, String sender, Object data) {
        this.type = type;
        this.sender = sender;
        this.data = data;
    }
}