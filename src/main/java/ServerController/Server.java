package ServerController;

import java.io.IOException;

public class Server {

    private SocketServer socketServer;

    public Server(){
        this.socketServer = new SocketServer(5858);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.socketServer.startSocketServer();
    }
}
