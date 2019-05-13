/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

import java.util.ArrayList;

/**
 * Weapon Electroscythe
 */
public class Electroscythe extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public Electroscythe() {
        super();
        super.getFirstPrice().put(Munitions.BLUE, 1);
        super.getSecondPrice().put(Munitions.BLUE, 1);
        super.getSecondPrice().put(Munitions.RED, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 1);
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param allPlay current state game
     * @return OK or POSITION_UNREACHABLE
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        for(int i = 0; i < myPosition.getCurrentcell().getInCellPlayer().size(); i++) {
            if (myPosition.getCurrentcell().getInCellPlayer().get(i) == positionToAttack.getCurrentcell().getInCellPlayer().get(i)) {
                if (myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
                }
            }
            else return MessageEnum.POSITION_UNREACHABLE;
        }
        return MessageEnum.OK;
    }

    /**
     * Function for the reaper attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param allPlay current state game
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        for(int i = 0; i < myPosition.getCurrentcell().getInCellPlayer().size(); i++) {
            if (myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                if (myPosition.getCurrentcell().getInCellPlayer() == positionToAttack.getCurrentcell().getInCellPlayer()) {
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
                }
            }
            else return MessageEnum.POSITION_NOT_FOUND;
        }
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }
}
