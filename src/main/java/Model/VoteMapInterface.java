package Model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VoteMapInterface extends Remote {
    void setVoteresult(int index) throws RemoteException;

    int[] getVoteresult() throws RemoteException;
}
