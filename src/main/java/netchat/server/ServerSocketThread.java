package netchat.server;

public class ServerSocketThread extends Thread {
    private int port;

    public ServerSocketThread(String name, int port) {
        super(name);
        this.port = port;
        start();
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Server started");
        while (!isInterrupted()) {

        }
    }
}
