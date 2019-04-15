package ServerController;

import java.io.IOException;
import java.rmi.AlreadyBoundException;

public class Server {

    private SocketServer socketserver;
    private ServerRMI server_rmi;

    public Server(){
        this.socketserver = new SocketServer(5858);
        this.server_rmi = new ServerRMI();
    }

    public static void main(String[] args) throws IOException, AlreadyBoundException {
        Server server = new Server();
        server.socketserver.start();
        server.server_rmi.start();
    }
}
