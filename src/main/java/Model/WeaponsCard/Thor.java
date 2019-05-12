/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

/**
 * Weapon Thor
 */
public class Thor extends Weapon {

    private Position position1;
    private Position position2;
    private Player myPlayer;

    /**
     * Constructor that set the cost of this weapon
     */
    public Thor() {
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(true);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 2);
    }

    /**
     * Function first attack
     * @param myposition       position of the player who attack
     * @param player           player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @return OK
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Position myposition, Player player, Position positionToAttack, PlayerBoard playerToAttack){
        check(myposition, positionToAttack);
        playerToAttack.getDamageBox().increaseDamage(2, player);
        position1 = positionToAttack;
        myPlayer = player;
        return MessageEnum.OK;
    }

    /**
     * Function react to chain
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @return OK
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Position positionToAttack, PlayerBoard playerToAttack){
        check(position1, positionToAttack);
        playerToAttack.getDamageBox().increaseDamage(1, myPlayer);
        position2 = positionToAttack;
        return MessageEnum.OK;
    }

    /**
     * Function high voltage
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @return OK
     * @author Giulia Rivara
     */
    public MessageEnum thirdAttack(Position positionToAttack, PlayerBoard playerToAttack){
        check(position2, positionToAttack);
        playerToAttack.getDamageBox().increaseDamage(2, myPlayer);
        return MessageEnum.OK;
    }

    /**
     * Function that check for correct position and correct player
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    private MessageEnum check(Position myPosition, Position positionToAttack){
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
            if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false)
            return MessageEnum.POSITION_NOT_FOUND;
        return MessageEnum.OK;
    }
}
