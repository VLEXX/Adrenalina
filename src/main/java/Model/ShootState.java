package Model;

import Model.WeaponsCard.Weapon;

public class ShootState implements State {

    @Override
    public MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        if(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList().contains(dataPacket.getWeapon())){
            Weapon weapon = null;
            for(Weapon w: allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()){
                if(dataPacket.getWeapon().equals(w)){
                    weapon = w;
                }
            }
            if(weapon.getLoaded()==true){

            }
            else{
                return MessageEnum.EMPTY_WEAPON;
            }
        }
        else{
            return MessageEnum.WEAPON_NOT_FOUND;
        }
        return MessageEnum.OK;
    }
}
