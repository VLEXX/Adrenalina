/**
 * @author Federico Scatà
 */
package model.modelstates;

import model.gamedata.InitializeAllPlay;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ActionState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;
    private StatesEnum namestate;

    public ActionState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap)throws RemoteException {
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
        this.namestate=StatesEnum.ACTION;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        System.out.println(dataPacket.getStatesEnum());
        if (dataPacket.getStatesEnum().equals(StatesEnum.MOVE)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.MOVE));
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.PICK_UP)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.PICK_UP));
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.SHOOT)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT));
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.END)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
            return MessageEnum.OK;
        } else {
            return MessageEnum.ACTION_ERROR;
        }
    }

    public StatesEnum getNamestate() throws RemoteException {
        return namestate;
    }
}