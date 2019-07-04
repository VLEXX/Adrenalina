package model.gamedata;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VoteModeInterface extends Remote {

    void setVoteResult(int index) throws RemoteException;

    void setFinalResult() throws RemoteException;

    Mode getFinalResult() throws RemoteException;

}
