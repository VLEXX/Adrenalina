package model.gamedata;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StateSelectedMapInterface extends Remote {

    void setStrategyMap(int i) throws RemoteException;

    void setSelectedmap() throws RemoteException;

}
