/**
 * @author Federico Scatà
 */
package servercontroller;

import model.gamedata.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Thread that identify ServerRMI and manage client RMI method call
 */
public class ServerRMI extends Thread {
    private InitializeAllPlay allPlay;
    private IDClientList idClientList;

    public ServerRMI(InitializeAllPlay allPlay, IDClientList clientList){
        this.allPlay = allPlay;
        this.idClientList=clientList;
    }

    public void startServerRMI() throws RemoteException, AlreadyBoundException {

        Updater updater = new Updater(this.allPlay);
        ServerManagerFunctionRMI serverManagerFunctionRMI = new ServerManagerFunctionRMI(this.allPlay, this.idClientList);
        StateBox stateBox = new StateBox(this.allPlay, idClientList);
        ManageEndTurn manageEndTurn = new ManageEndTurn(allPlay, idClientList);


        System.out.println("Binding server to registry...");
        Registry registry = LocateRegistry.createRegistry(8080);


        registry.bind("allPlay", this.allPlay);
        registry.bind("IDClientList", this.idClientList);
        registry.bind("Updater", updater);
        registry.bind("ServerManagerFunctionRMI", serverManagerFunctionRMI);
        registry.bind("VoteMap", allPlay.getVoteMap());
        registry.bind("StateBox", stateBox);
        registry.bind("ManageEndTurn", manageEndTurn);
        registry.bind("StateSelectedMap", allPlay.getStateSelectedMap());
        registry.bind("VoteMode", allPlay.getVoteMode());
        registry.bind("StateSelectedMode", allPlay.getStateSelectedMode());



        System.out.println("Attendo invocazioni dal client...");

        CheckConnectionController checkConnectionController = new CheckConnectionController(idClientList,allPlay);

        checkConnectionController.start();
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
