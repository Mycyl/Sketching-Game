package client;
import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    private JTextArea chatArea;
    private JTextField inputField;

    public ChatPanel() {
        setLayout(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        inputField = new JTextField();

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
    }

    public void appendMessage(String message) {
        chatArea.append(message + "\n");
    }

    public JTextField getInputField() {
        return inputField;
    }
}