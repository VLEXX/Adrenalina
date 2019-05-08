package ServerController;

import Model.InitializeAllPlay;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer extends Thread{
    private int port;
    private InitializeAllPlay allPlay;
    private int counter;

    public SocketServer(int port, InitializeAllPlay allPlay, int i){
        this.port = port;
        this.allPlay = allPlay;
        this.counter=i;
    }

    public void startSocketServer() throws IOException {

        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server ready");
        while(this.counter!=0) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new SocketClientHandler(socket, this.allPlay));
                counter--;
            }
            catch (IOException e){
                break;
            }
        }

        executor.shutdown();
        serverSocket.close();
    }

    public void run(){
        try {
            startSocketServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
