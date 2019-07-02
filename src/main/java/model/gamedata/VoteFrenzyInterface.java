package model.gamedata;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VoteFrenzyInterface extends Remote {

    void addYes() throws RemoteException;

    void addNo() throws RemoteException;

    boolean getResult() throws RemoteException;
}
