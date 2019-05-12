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
                for(Player player: dataPacket.getTargetPlayers()) {
                    if(weapon.check(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition(), allPlay.getCurrentPlayerState().get(player).getPlayerposition())==false){
                        return MessageEnum.PLAYERS_NOT_VALID;
                    }
                }
                for(Player player: dataPacket.getTargetPlayers()){
                    weapon.firstAttack(dataPacket.getPlayer(), player, allPlay);
                }
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
