package model.modelstates;

import model.datapacket.DataPacket;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;
import model.weaponscard.Weapon;

public class ShootFirstState implements State {

    private InitializeAllPlay allPlay;

    public ShootFirstState(InitializeAllPlay initializeAllPlay){
        this.allPlay = initializeAllPlay;
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
