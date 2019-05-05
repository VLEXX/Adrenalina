package ServerController;

import Model.InitializeAllPlay;
import Model.VoteMap;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI extends Thread{
    private InitializeAllPlay allPlay;

    public ServerRMI(InitializeAllPlay allPlay){
        this.allPlay = allPlay;
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
