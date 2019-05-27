package servercontroller;

import model.datapacket.StatesEnum;
import model.modelstates.State;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface StateBoxInterface extends Remote {

    HashMap<StatesEnum, State> getHashMap() throws RemoteException;
}
