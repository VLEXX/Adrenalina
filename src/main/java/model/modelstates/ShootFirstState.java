package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;
import model.weaponscard.Weapon;

import java.util.HashMap;

public class ShootFirstState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public ShootFirstState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        if(dataPacket.isFirstAttack()==false){
            return MessageEnum.OK;
        }
        Weapon weapon;
        if(dataPacket.isFirstAttack()==true){
            for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (dataPacket.getWeapon().getName().equals(w.getName())) {
                    weapon = w;
                    if(weapon.getLoaded()==true){
                        return weapon.firstAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersFirst(), allPlay);
                    }
                    else{
                        return MessageEnum.EMPTY_WEAPON;
                    }
                }
            }
        }
        return MessageEnum.WEAPON_NOT_FOUND;

    }
}
