/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.modelstates.State;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Interface for StateBox used by RMI Client
 */
public interface StateBoxInterface extends Remote {

    HashMap<StatesEnum, State> getHashMap() throws RemoteException;
}
