package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.weaponscard.Weapon;

public class ShootThirdState implements State {

    private InitializeAllPlay allPlay;

    public ShootThirdState(InitializeAllPlay initializeAllPlay){
        this.allPlay = initializeAllPlay;
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
