/**
 * @author Federico Scatà
 */
package View;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientWithRMI implements ClientStrategy {

    public ClientWithRMI(){

    }

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(8080);

        String[] e = registry.list();

        String remoteObject = "server_central";

        System.out.println("Choose a character...");
        System.out.println("Black | Blue | Green | Purple | Yellow");

        ClientManager clientManager = new ClientManager();

    }
}
