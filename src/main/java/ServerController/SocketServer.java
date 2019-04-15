package ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    private int port;

    public SocketServer(int port){
        this.port = port;
    }

    public void startSocketServer() throws IOException {

        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server ready");
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new SocketClientHandler(socket));
            }
            catch (IOException e){
                break;
            }
        }
        executor.shutdown();
    }
}
