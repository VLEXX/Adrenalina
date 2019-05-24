/**
 * @author Federico Scatà
 */
package servercontroller;

import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer extends Thread implements Serializable {
    private int port;
    private InitializeAllPlay allPlay;
    private ArrayList<ObserverCounter> observerCounters;
    private IDClientList idClientList;

    public SocketServer(int port, InitializeAllPlay allPlay, IDClientList clientList){
        this.port = port;
        this.allPlay = allPlay;
        this.observerCounters=new ArrayList<>();
        this.idClientList=clientList;
    }

    public void startSocketServer() throws IOException {

        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server ready");
        while(this.idClientList.getClientCounter()!=0) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new SocketClientHandler(socket, this.allPlay, idClientList));
                this.idClientList.update();
                System.out.println(this.idClientList.getClientCounter());
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
