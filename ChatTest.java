// import javax.swing.JFrame;
// import javax.swing.SwingUtilities;
// import client.*;

// public class ChatTest {
//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             // Step 1: create a holder for ChatPanel
//             final ChatPanel[] panelHolder = new ChatPanel[1];

//             // Step 2: instantiate with lambda
//             panelHolder[0] = new ChatPanel(text -> {
//                 panelHolder[0].appendMessage("You: " + text);
//             });

//             JFrame frame = new JFrame("Chat Panel Test");
//             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             frame.setSize(400, 500);
//             frame.add(panelHolder[0]);
//             frame.setVisible(true);
//         });
//     }
// }
