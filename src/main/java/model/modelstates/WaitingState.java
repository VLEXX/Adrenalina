/**
 * @author Federico Scatà
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class WaitingState extends UnicastRemoteObject implements State , Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public WaitingState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap) throws RemoteException {
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public synchronized MessageEnum doAction(DataPacket dataPacket) {
        while (true) {
            if (!(allPlay.getPlayerState(dataPacket.getPlayer()) instanceof WaitingState)) {
                return MessageEnum.OK;
            }
        }
    }
}
