package model.gamedata;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StateSelectedModeInterface extends Remote {

    void setSelectedmode(Mode selectedmode) throws RemoteException;

}
