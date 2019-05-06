package ServerController;

import Model.InitializeAllPlay;

public class Server {

    private SocketServer socketserver;
    private ServerRMI server_rmi;
    private InitializeAllPlay allPlay;

    public Server(){
        this.allPlay = new InitializeAllPlay();
        System.out.println("Model Inizialized\n");
        this.socketserver = new SocketServer(5858, this.allPlay);
        this.server_rmi = new ServerRMI(this.allPlay);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.socketserver.start();
    }
}
