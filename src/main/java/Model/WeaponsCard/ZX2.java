/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

/**
 * Weapon ZX2
 */
public class ZX2 extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public ZX2() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);      //Posizione 0 dell'arraylist per riferirsi al primo attacco
        super.setWeaponsMessage(WeaponsMessage.MAX_THREE_PLAYER, 1);
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param allPlay current state game
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player myPlayer, Player playerToAttack, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack).getPlayerposition();
        if(check(myPosition, positionToAttack)) {
            allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getMarksBox().setMyMarksMap(myPlayer, 2);
        }
        else return MessageEnum.POSITION_NOT_FOUND;
        return MessageEnum.OK;
    }

    /**
     * Function scanner mode
     * @param myPlayer player who attack
     * @param player1 player to attack
     * @param player2 player to attack
     * @param player3 player to attack
     * @param allPlay current state game
     * @return OK or POSITION_UNREACHABLE
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player myPlayer, Player player1, Player player2, Player player3, InitializeAllPlay allPlay) {
        if (player1 != player2 && player2 != player3 && player1 != player3) {
            if (player1 != null) {
                if (check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(player1).getPlayerposition()))
                    allPlay.getCurrentPlayerState().get(player1).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
                else return MessageEnum.POSITION_UNREACHABLE;
            }
            if (player2 != null) {
                if (check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(player2).getPlayerposition()))
                    allPlay.getCurrentPlayerState().get(player2).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
                else return MessageEnum.POSITION_UNREACHABLE;
            }
            if (player3 != null) {
                if (check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(player3).getPlayerposition()))
                    allPlay.getCurrentPlayerState().get(player3).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
                else return MessageEnum.POSITION_UNREACHABLE;
            }
        }
        else return MessageEnum.PLAYERS_NOT_VALID;
        return MessageEnum.OK;
    }

    /**
     * Function that check for correct position
     * @param myPosition position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return true if ok
     * @author Giulia Rivara
     */
    private boolean check(Position myPosition, Position positionToAttack){
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
