package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.weaponscard.Weapon;

import java.util.HashMap;

public class ShootThirdState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public ShootThirdState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        if(dataPacket.isThirdAttack()==false){
            return MessageEnum.OK;
        }
        Weapon weapon;
        if(dataPacket.isThirdAttack()==true){
            for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (dataPacket.getWeapon().getName().equals(w.getName())) {
                    weapon = w;
                    return weapon.thirdAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersSecond(), allPlay);
                }
            }
        }
        return MessageEnum.WEAPON_NOT_FOUND;
    }
}
