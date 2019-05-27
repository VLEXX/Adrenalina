/**
 * @author Federico Scatà
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface State extends Remote {
    MessageEnum doAction(DataPacket dataPacket) throws RemoteException;
}
