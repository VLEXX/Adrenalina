/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

import java.util.ArrayList;

/**
 * Weapon Machinegun
 */
public class MachineGun extends Weapon {

    boolean player1Attacked;
    private Player player1;
    private Player player2;

    /**
     * Constructor that set the cost of this weapon
     */
    public MachineGun() {
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
        player1Attacked = false;
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(true);
    }

    /**
     * Function first attack
     *
     * @param player            player who attack
     * @param myPosition        position of the player who attack
     * @param positionToAttack1 position of the first player to attack
     * @param playerToAttack1   first player to attack
     * @param positionToAttack2 position of the second player to attack
     * @param playerToAttack2   second player to attack
     * @return OK
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player player, Position myPosition, Position positionToAttack1, PlayerBoard playerToAttack1, Position positionToAttack2, PlayerBoard playerToAttack2){
        player1 = playerToAttack1.getPlayer();
        player2 = playerToAttack2.getPlayer();
        if (positionToAttack1 != null && playerToAttack1 != null) {
            check(myPosition, positionToAttack1, playerToAttack1);
            playerToAttack1.getDamageBox().increaseDamage(1, player);
        }
        if (positionToAttack2 != null && playerToAttack2 != null) {
            check(myPosition, positionToAttack2, playerToAttack2);
            playerToAttack2.getDamageBox().increaseDamage(1, player);
        }
        return MessageEnum.OK;
    }

    /**
     * Function focused shot
     *
     * @param player           player who attack
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @return OK or CANNOT_USE_THIS_EFFECT
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack){
        if (player1 == null || player2 == null) {
            return MessageEnum.CANNOT_USE_THIS_EFFECT;
        }
        if (playerToAttack.getPlayer() != player1 && playerToAttack.getPlayer() != player2) {
            return  MessageEnum.CANNOT_USE_THIS_EFFECT;
        }
        check(myPosition, positionToAttack, playerToAttack);
        playerToAttack.getDamageBox().increaseDamage(1, player);
        if (playerToAttack.getPlayer() == player1)
            player1Attacked = true;
        else player1Attacked = false;
        return MessageEnum.OK;
    }

    /**
     * Function support tripods
     *
     * @param player            player who attack
     * @param myPosition        position of the player who attack
     * @param positionToAttack  position of the second player to attack
     * @param playerToAttack    first player to firstAttack
     * @param positionToAttack2 position of the second player to attack
     * @param playerToAttack2   second player to attack
     * @return OK or CANNOT_USE_THIS_EFFECT
     * @author Giulia Rivara
     */
    public MessageEnum thirdAttack(Player player, Position myPosition, Position positionToAttack, PlayerBoard playerToAttack, Position positionToAttack2, PlayerBoard playerToAttack2){
        if (player1 == null || player2 == null) {
            if (playerToAttack.getPlayer() == player1 || playerToAttack.getPlayer() == player2) {
                check(myPosition, positionToAttack, playerToAttack);
                playerToAttack.getDamageBox().increaseDamage(1, player);
                if (playerToAttack2 != null) {
                    check(myPosition, positionToAttack2, playerToAttack2);
                    playerToAttack2.getDamageBox().increaseDamage(1, player);
                }
            } else if (playerToAttack2.getPlayer() == player1 || playerToAttack2.getPlayer() == player2) {
                check(myPosition, positionToAttack2, playerToAttack2);
                playerToAttack2.getDamageBox().increaseDamage(1, player);
                if (playerToAttack != null) {
                    check(myPosition, positionToAttack, playerToAttack);
                    playerToAttack.getDamageBox().increaseDamage(1, player);
                }
            }
        } else if ((playerToAttack.getPlayer() == player1 && player1Attacked == false) || (playerToAttack.getPlayer() == player2 && player1Attacked == true)) {
            check(myPosition, positionToAttack, playerToAttack);
            playerToAttack.getDamageBox().increaseDamage(1, player);
            if (playerToAttack2 != null && playerToAttack2.getPlayer() != player1 && playerToAttack2.getPlayer() != player2) {
                check(myPosition, positionToAttack2, playerToAttack2);
                playerToAttack2.getDamageBox().increaseDamage(1, player);
            }
        } else if ((playerToAttack2.getPlayer() == player1 && player1Attacked == false) || (playerToAttack2.getPlayer() == player2 && player1Attacked == true)) {
            check(myPosition, positionToAttack2, playerToAttack2);
            playerToAttack2.getDamageBox().increaseDamage(1, player);
            if (playerToAttack != null && playerToAttack.getPlayer() != player1 && playerToAttack.getPlayer() != player2) {
                check(myPosition, positionToAttack, playerToAttack);
                playerToAttack.getDamageBox().increaseDamage(1, player);
            }
        } else return MessageEnum.CANNOT_USE_THIS_EFFECT;
        return MessageEnum.OK;
    }

    /**
     * Function that check for correct position and correct player
     *
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @param playerToAttack   player to attack
     * @return Ok or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    private MessageEnum check(Position myPosition, Position positionToAttack, PlayerBoard playerToAttack) {
        boolean find = false;
        if (playerToAttack.getPlayer() != null) {
            for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
                if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                    find = true;
                    break;
                }
            }
            if (find = false) {
                return MessageEnum.POSITION_NOT_FOUND;
            }
        }
        return MessageEnum.OK;
    }
}