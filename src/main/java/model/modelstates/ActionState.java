/**
 * @author Federico Scatà
 */
package model.modelstates;

import model.gamedata.InitializeAllPlay;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;

import java.util.HashMap;

public class ActionState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public ActionState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        if (dataPacket.getStatesEnum().equals(StatesEnum.MOVE)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.MOVE));
            allPlay.notifyObserver();
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.PICK_UP)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.PICK_UP));
            allPlay.notifyObserver();
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.SHOOT)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT));
            allPlay.notifyObserver();
            return MessageEnum.OK;
        }
        if (dataPacket.getStatesEnum().equals(StatesEnum.END)) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
            allPlay.notifyObserver();
            return MessageEnum.OK;
        } else {
            return MessageEnum.ACTION_ERROR;
        }
    }
}