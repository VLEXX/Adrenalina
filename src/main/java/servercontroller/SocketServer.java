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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer extends Thread implements Serializable {
    private int port;
    private InitializeAllPlay allPlay;
    private IDClientList idClientList;

    public SocketServer(int port, InitializeAllPlay allPlay, IDClientList clientList){
        this.port = port;
        this.allPlay = allPlay;
        this.idClientList=clientList;
    }

    public void startSocketServer() throws IOException {

        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server ready");
        while(true) {
            try {
                if(idClientList.getNicknameList().size()!=5){
                    Socket socket = serverSocket.accept();
                    executor.submit(new SocketClientHandler(socket, this.allPlay, idClientList));
                }
                else{
                    break;
                }
            }
            catch (IOException e){

            }
        }


        executor.shutdown();
        serverSocket.close();
    }

    public synchronized IDClientList getIdClientList() {
        return idClientList;
    }

    public void run(){
        try {
            startSocketServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
