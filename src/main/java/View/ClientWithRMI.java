package View;

import Model.VoteMapInterface;

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
        VoteMapInterface voteMapInterface = (VoteMapInterface) registry.lookup(remoteObject);

        voteMapInterface.setVoteresult(0);
        System.out.println("Il voto è: " + voteMapInterface.getVoteresult()[0]);
    }
}
