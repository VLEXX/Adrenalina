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
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_PLAYER, 2);
    }

    /**
     * Function first attack
     *
     * @param player            player who attack
     * @param playerToAttack1   first player to attack
     * @param playerToAttack2   second player to attack
     * @param allPlay           current state game
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player player, Player playerToAttack1, Player playerToAttack2, InitializeAllPlay allPlay){
        Position position = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToAttack1 = allPlay.getCurrentPlayerState().get(playerToAttack1).getPlayerposition();
        Position positionToAttack2 = allPlay.getCurrentPlayerState().get(playerToAttack2).getPlayerposition();
        player1 = playerToAttack1;
        player2 = playerToAttack2;
        if (positionToAttack1 != null && playerToAttack1 != null) {
            if (check(position, positionToAttack1))
                allPlay.getCurrentPlayerState().get(playerToAttack1).getBoard().getDamageBox().increaseDamage(1, player);
            else return MessageEnum.POSITION_NOT_FOUND;
        }
        if (positionToAttack2 != null && playerToAttack2 != null) {
            if(check(position, positionToAttack2))
                allPlay.getCurrentPlayerState().get(playerToAttack2).getBoard().getDamageBox().increaseDamage(1, player);
            else return MessageEnum.POSITION_NOT_FOUND;
        }
        return MessageEnum.OK;
    }

    /**
     * Function focused shot
     *
     * @param player           player who attack
     * @param playerToAttack   player to attack
     * @param allPlay current state game
     * @return OK or CANNOT_USE_THIS_EFFECT or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player player, Player playerToAttack, InitializeAllPlay allPlay){
        Position position = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack).getPlayerposition();
        if (player1 == null || player2 == null) {
            return MessageEnum.CANNOT_USE_THIS_EFFECT;
        }
        if (allPlay.getCurrentPlayerState().get(playerToAttack).getActiveplayer() != player1 && allPlay.getCurrentPlayerState().get(playerToAttack).getActiveplayer() != player2) {
            return  MessageEnum.CANNOT_USE_THIS_EFFECT;
        }
        if(check(position, positionToAttack))
            allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getDamageBox().increaseDamage(1, player);
        else return MessageEnum.POSITION_NOT_FOUND;
        if (playerToAttack == player1)
            player1Attacked = true;
        else player1Attacked = false;
        return MessageEnum.OK;
    }

    /**
     * Function support tripods
     *
     * @param player            player who attack
     * @param playerToAttack    first player to firstAttack
     * @param playerToAttack2   second player to attack
     * @param allPlay current state game
     * @return OK or CANNOT_USE_THIS_EFFECT or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum thirdAttack(Player player, Player playerToAttack, Player playerToAttack2, InitializeAllPlay allPlay){
        Position position = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack).getPlayerposition();
        Position positionToAttack2 = allPlay.getCurrentPlayerState().get(playerToAttack2).getPlayerposition();
        if (player1 == null || player2 == null) {
            if (playerToAttack == player1 || playerToAttack == player2) {
                if(check(position, positionToAttack))
                    allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getDamageBox().increaseDamage(1, player);
                else return MessageEnum.POSITION_NOT_FOUND;
                if (playerToAttack2 != null) {
                    if(check(position, positionToAttack2))
                        allPlay.getCurrentPlayerState().get(playerToAttack2).getBoard().getDamageBox().increaseDamage(1, player);
                    else return MessageEnum.POSITION_NOT_FOUND;
                }
            } else if (playerToAttack2 == player1 || playerToAttack2 == player2) {
                if(check(position, positionToAttack2))
                    allPlay.getCurrentPlayerState().get(playerToAttack2).getBoard().getDamageBox().increaseDamage(1, player);
                else return MessageEnum.POSITION_NOT_FOUND;
                if (playerToAttack != null) {
                    if(check(position, positionToAttack))
                        allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getDamageBox().increaseDamage(1, player);
                    else return MessageEnum.POSITION_NOT_FOUND;
                }
            }
        } else if ((playerToAttack == player1 && player1Attacked == false) || (playerToAttack == player2 && player1Attacked == true)) {
            if(check(position, positionToAttack))
                allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getDamageBox().increaseDamage(1, player);
            else return MessageEnum.POSITION_NOT_FOUND;
            if (playerToAttack2 != null && playerToAttack2 != player1 && playerToAttack2 != player2) {
                if(check(position, positionToAttack2))
                    allPlay.getCurrentPlayerState().get(playerToAttack2).getBoard().getDamageBox().increaseDamage(1, player);
                return MessageEnum.POSITION_NOT_FOUND;
            }
        } else if ((playerToAttack2 == player1 && player1Attacked == false) || (playerToAttack2 == player2 && player1Attacked == true)) {
            if(check(position, positionToAttack2))
                allPlay.getCurrentPlayerState().get(playerToAttack2).getBoard().getDamageBox().increaseDamage(1, player);
            else return MessageEnum.POSITION_NOT_FOUND;
            if (playerToAttack != null && playerToAttack != player1 && playerToAttack != player2) {
                if(check(position, positionToAttack))
                    allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getDamageBox().increaseDamage(1, player);
                else return MessageEnum.POSITION_NOT_FOUND;
            }
        } else return MessageEnum.CANNOT_USE_THIS_EFFECT;
        return MessageEnum.OK;
    }

    /**
     * Function that check for correct position
     *
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return true if ok
     * @author Giulia Rivara
     */
    private boolean check(Position myPosition, Position positionToAttack) {
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
            if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            return false;
        }
        return true;
    }
}