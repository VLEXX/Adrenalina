package ServerController;

import Model.InizializeAllPlay;

public class Server {

    private SocketServer socketserver;
    private ServerRMI server_rmi;
    private InizializeAllPlay allPlay;

    public Server(){
        this.allPlay = new InizializeAllPlay();
        System.out.println("Model Inizialized\n");
        this.socketserver = new SocketServer(5858, this.allPlay);
        this.server_rmi = new ServerRMI(this.allPlay);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.socketserver.start();
        server.server_rmi.start();
    }
}
