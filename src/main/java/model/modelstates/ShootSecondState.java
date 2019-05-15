package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.weaponscard.Weapon;

public class ShootSecondState implements State {

    private InitializeAllPlay allPlay;

    public ShootSecondState(InitializeAllPlay initializeAllPlay){
        this.allPlay = initializeAllPlay;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        if(dataPacket.isSecondAttack()==false){
            return MessageEnum.OK;
        }
        Weapon weapon;
        if(dataPacket.isSecondAttack()==true) {
            for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (dataPacket.getWeapon().getName().equals(w.getName())) {
                    weapon = w;
                    return weapon.secondAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersSecond(), allPlay);
                }
            }
        }
        return MessageEnum.WEAPON_NOT_FOUND;
    }
}
