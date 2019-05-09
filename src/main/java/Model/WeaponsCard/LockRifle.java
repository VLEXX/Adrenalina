/**
 *@author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;
import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;

public class LockRifle extends Weapon {

    //Costruttore
    public LockRifle() {
        super();
        super.setFirstPrice(Munitions.BLUE, 2);
        super.setSecondPrice(Munitions.RED, 1);
    }

    //Funzione effetto base
    public PlayerBoard attack(Position myPosition, Player activePlayer, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound {
        check(activePlayer,myPosition,positionToAttack,playerToAttack);
        if(super.getFirstUse() == true)
            super.setFirstUse(false);
        playerToAttack.getDamageBox().increaseDamage(2, activePlayer);
        playerToAttack.getMarksBox().setMyMarksMap(activePlayer, 1);
        return playerToAttack;
    }

    //Funzione secondo aggancio
    public PlayerBoard hooking(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound{
        check(player,myPosition,positionToAttack,playerToAttack);
        playerToAttack.getMarksBox().setMyMarksMap(player, 1);
        return playerToAttack;
    }

    //Controlla che la pposizione sia corretta e che il giocatore in quella posizione sia presente
    private void check(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound{
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
            if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false)
            throw new PositionNotFound("Position not found for " + positionToAttack.getCurrentcell().getCellId());
        find = false;
        for (int i = 0; i < positionToAttack.getCurrentcell().getInCellPlayer().size(); i++) {
            if (positionToAttack.getCurrentcell().getInCellPlayer().get(i) == playerToAttack.getPlayer()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            throw new PlayerNotFound("In the selected cell player " + playerToAttack.getPlayer().toString() + " not found");
        }
    }
}



