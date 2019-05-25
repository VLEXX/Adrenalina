/**
 * @author Federico Scatà
 */
package servercontroller;

import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class Server {

    public static void main(String[] args) {
        InitializeAllPlay allPlay = new InitializeAllPlay();
        IDClientList idClientList = null;
        try {
            idClientList = new IDClientList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        SocketServer socketserver = new SocketServer(5858, allPlay, idClientList);
        ServerRMI server_rmi = new ServerRMI(allPlay, idClientList);
        socketserver.start();
        try {
            server_rmi.startServerRMI();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
