/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

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
     * @param myPosition       position of the player who attack
     * @param activePlayer     player who attack
     * @param playerToAttack   player to attack
     * @return OK
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Position myPosition, Player activePlayer, Position positionToAttack, PlayerBoard playerToAttack){
        check(myPosition, positionToAttack);
        if (super.getLoaded() == true)
            super.setLoaded(false);
        playerToAttack.getDamageBox().increaseDamage(2, activePlayer);
        playerToAttack.getMarksBox().setMyMarksMap(activePlayer, 1);
        return MessageEnum.OK;
    }

    /**
     * Function for second hook
     * @param player           player who attack
     * @param myPosition       position of the player who attack
     * @param playerToAttack   player to attack
     * @return OK
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack){
        check(myPosition, positionToAttack);
        playerToAttack.getMarksBox().setMyMarksMap(player, 1);
        return MessageEnum.OK;
    }

    /**
     * Function that check for correct position and correct player
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    private MessageEnum check(Position myPosition, Position positionToAttack) {
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
            if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            return MessageEnum.POSITION_NOT_FOUND;
        }
        return MessageEnum.OK;
    }
}



