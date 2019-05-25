/**
 * @author Federico Scatà
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;

import java.rmi.RemoteException;

public interface State {
    MessageEnum doAction(DataPacket dataPacket) throws RemoteException;
}
