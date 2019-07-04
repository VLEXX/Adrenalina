/**
 * @author Federico Scatà
 */
package model.gamedata;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VoteMapInterface extends Remote {

    void addPlayerCounter() throws RemoteException;

    int getPlayerCounter() throws RemoteException;

    int getFinalresult() throws RemoteException;

    void setI(int i) throws RemoteException;

    void setInitmap() throws RemoteException;

    void setFinalresult() throws RemoteException;


}
