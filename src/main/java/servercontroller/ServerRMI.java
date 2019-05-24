/**
 * @author Federico Scatà
 */
package servercontroller;

import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.gamedata.VoteMap;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI extends Thread{
    private InitializeAllPlay allPlay;
    private IDClientList idClientList;

    public ServerRMI(InitializeAllPlay allPlay, IDClientList clientList){
        this.allPlay = allPlay;
        this.idClientList=clientList;
    }

    public void startServerRMI() throws RemoteException, AlreadyBoundException {

        System.out.println("Binding server to registry...");
        Registry registry = LocateRegistry.createRegistry(8080);
        registry.bind("server_central", this.allPlay.getVoteMap());
        registry.bind("IDClientList", this.idClientList);

        System.out.println("Attendo invocazioni dal client...");


    }

    public void run(){
        try {
            startServerRMI();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
