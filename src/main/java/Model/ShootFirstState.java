package Model;

import Model.WeaponsCard.Weapon;

public class ShootFirstState implements State {

    @Override
    public MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        Weapon weapon = null;
        for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
            if (dataPacket.getWeapon().equals(w)) {
                weapon = w;
                //controllo che possieda l'arma TODO
            }
        }
        if (weapon != null) {
            if (dataPacket.isFirstAttack() == true) {
                if (weapon.getLoaded() == true) {
                    return weapon.firstAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersFirst(), allPlay);
                } else {
                    return MessageEnum.EMPTY_WEAPON;
                }
            }
        }
        else{
            return MessageEnum.WEAPON_NOT_FOUND;
        }
        return MessageEnum.OK;
    }
}
