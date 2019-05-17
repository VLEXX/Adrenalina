package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;

import java.util.HashMap;

public class PowerupState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    /**
     * Class constructor
     */
    public PowerupState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {

        if(dataPacket.getMarksToAdd().isEmpty()==true) {
            for (PowerUp powerUp : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList()) {
                if (powerUp.getId().equals(PowerUpId.TAGBACK_GRENADE)) {
                    if(allPlay.getCurrentPlayerState().get(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getHit()).getControlMarks().containsKey(dataPacket.getPlayer())) {
                        Integer before = allPlay.getCurrentPlayerState().get(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getHit()).getControlMarks().get(dataPacket.getPlayer());
                        allPlay.getCurrentPlayerState().get(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getHit()).getControlMarks().replace(dataPacket.getPlayer(),before+1);
                        return MessageEnum.OK;
                    }
                    else{
                        allPlay.getCurrentPlayerState().get(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getHit()).getControlMarks().put(dataPacket.getPlayer(),1);
                        return MessageEnum.OK;
                    }
                }
            }
            return MessageEnum.POWERUP_NOT_FOUND;
        }
        return MessageEnum.OK;
    }
}
