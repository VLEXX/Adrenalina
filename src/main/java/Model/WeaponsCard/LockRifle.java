/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Exceptions.PlayerNotFound;
import Model.Exceptions.PositionNotFound;
import Model.Munitions;
import Model.Player;
import Model.PlayerBoard;
import Model.Position;

/**
 * Weapon Lockrifle
 */
public class LockRifle extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public LockRifle() {
        super();
        super.setFirstPrice(Munitions.BLUE, 2);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
    }

    /**
     * Function for the first attack of the weapon
     * @param myPosition position of the player who attack
     * @param activePlayer player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack player to attack
     * @return playerboard of the player to attack
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @author Giulia Rivara
     */
    public PlayerBoard firstAttack(Position myPosition, Player activePlayer, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound {
        check(myPosition, positionToAttack, playerToAttack);
        if (super.getLoaded() == true)
            super.setLoaded(false);
        playerToAttack.getDamageBox().increaseDamage(2, activePlayer);
        playerToAttack.getMarksBox().setMyMarksMap(activePlayer, 1);
        return playerToAttack;
    }

    /**
     * Function for second hook
     * @param player player who attack
     * @param myPosition position of the player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack player to attack
     * @return playerboard of the player to attack
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @author Giulia Rivara
     */
    public PlayerBoard secondAttack(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound {
        check(myPosition, positionToAttack, playerToAttack);
        playerToAttack.getMarksBox().setMyMarksMap(player, 1);
        return playerToAttack;
    }

    /**
     * Function that check for correct position and correct player
     * @param myPosition position of the player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack player to attack
     * @throws PositionNotFound
     * @throws PlayerNotFound
     * @author Giulia Rivara
     */
    private void check(Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) throws PositionNotFound, PlayerNotFound {
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



