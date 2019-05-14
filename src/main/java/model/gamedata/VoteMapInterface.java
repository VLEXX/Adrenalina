/**
 * @author Federico Scatà
 */
package model.gamedata;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VoteMapInterface extends Remote {
    int[] getVoteresult() throws RemoteException;

    void setVoteresult(int index) throws RemoteException;
}
