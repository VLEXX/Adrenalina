/**
 * @author Federico Scatà
 */
package servercontroller;

import model.gamedata.InitializeAllPlay;
import model.gamedata.VoteMap;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI extends Thread{
    private InitializeAllPlay allPlay;
    private int counter;

    public ServerRMI(InitializeAllPlay allPlay, int i){
        this.allPlay = allPlay;
        this.counter=i;
    }

    public void startServerRMI() throws RemoteException, AlreadyBoundException {
        VoteMap voteMap = new VoteMap();

        System.out.println("Binding server to registry...");
        Registry registry = LocateRegistry.createRegistry(8080);
        registry.bind("server_central", voteMap);


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
