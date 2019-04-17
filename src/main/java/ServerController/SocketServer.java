package ServerController;

import Model.InizializeAllPlay;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer extends Thread{
    private int port;
    private InizializeAllPlay allPlay;
    private int j;

    public SocketServer(int port){
        this.port = port;
        this.allPlay = new InizializeAllPlay();
    }

    public void startSocketServer() throws IOException {

        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server ready");
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new SocketClientHandler(socket, this.allPlay));
            }
            catch (IOException e){
                break;
            }
        }
        executor.shutdown();
    }

    public void run(){
        try {
            startSocketServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
