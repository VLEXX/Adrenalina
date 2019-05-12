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
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
    }

    /**
     * Function for the first attack of the weapon
     * @param allPlay       current game state
     * @param myPlayer     player who attack
     * @param playerToAttack   player to attack
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player myPlayer, Player playerToAttack, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack).getPlayerposition();
        if(check(myPosition, positionToAttack)) {
            if (super.getLoaded() == true) {
                super.setLoaded(false);
            }
        }
        else return MessageEnum.POSITION_NOT_FOUND;
        allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
        return MessageEnum.OK;
    }

    /**
     * Function for second hook
     * @param myPlayer           player who attack
     * @param allPlay           current state game
     * @param playerToAttack   player to attack
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player myPlayer, Player playerToAttack, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack).getPlayerposition();
        if(check(myPosition, positionToAttack))
            allPlay.getCurrentPlayerState().get(playerToAttack).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
        else return MessageEnum.POSITION_NOT_FOUND;
        return MessageEnum.OK;
    }

    /**
     * Function that check for correct position
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return true if ok
     * @author Giulia Rivara
     */
    public boolean check(Position myPosition, Position positionToAttack) {
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



