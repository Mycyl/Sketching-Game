public class TestServer {
    public static void main(String[] args) {
        try {
            server.ServerMain.main(args); // Runs the real ServerMain
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
