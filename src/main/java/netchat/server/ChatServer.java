package netchat.server;

public class ChatServer {
    private ServerSocketThread server;
    public void start(int port) {
        if (server != null && server.isAlive()) {
            System.out.println("Server already started");
        } else {
            System.out.println("Server started at port " + port);
            server = new ServerSocketThread("Chat server", port);
        }
    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            System.out.println("Server is not running");
        } else {
            System.out.println("Server stopped");
            server.interrupt();
        }
    }
}
