/**
 * @author Federico Scatà
 */
package view;

import model.gamedata.IDClientList;
import model.gamedata.IDClientListInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientWithRMI implements ClientStrategy {

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(8080);

        String[] e = registry.list();

        String remoteObject = "server_central";

        ClientManager clientManager = new ClientManager();
        ViewDatabase viewDatabase = new ViewDatabase();

        IDClientListInterface idClientList = (IDClientListInterface) registry.lookup("IDClientList");
        int token = idClientList.addClient();
        if(token==-1){
            System.out.println("\n" + "\u001B[31m" + "Because the Server is dark and full of connections." + "\u001B[0m");
        }
        else{
            System.out.println(token);
        }
    }
}
