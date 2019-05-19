/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.map.Position;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weapon ZX2
 */
public class ZX2 extends Weapon implements Serializable {

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
        super.setName("zx2");
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param allPlay current state game
     * @return OK or POSITION_NOT_FOUND
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(check(myPosition, positionToAttack) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).addControlMarks(myPlayer, 2);
        return MessageEnum.OK;
    }

    /**
     * Function scanner mode
     * @param myPlayer player who attack
     * @param allPlay current state game
     * @param playerToAttack player to attack
     * @return OK or POSITION_UNREACHABLE
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay) {
        int control1 = 0;
        int control2 = 0;
        int control3 = 0;
        control1 = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        control2 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        control3 = allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(playerToAttack.get(0) != null && check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition()))
            return MessageEnum.POSITION_NOT_FOUND;
        if(playerToAttack.get(1) != null && check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getPlayerposition()))
            return MessageEnum.POSITION_NOT_FOUND;
        if(playerToAttack.get(2) != null && check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition()))
            return MessageEnum.POSITION_NOT_FOUND;
        if (playerToAttack.get(0) != playerToAttack.get(1) && playerToAttack.get(1) != playerToAttack.get(2) && playerToAttack.get(0) != playerToAttack.get(2)) {
            if (playerToAttack.get(0) != null) {
                if(control1 != 0)
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control1, myPlayer);
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).addControlMarks(myPlayer, 1);
            }
            if (playerToAttack.get(1) != null) {
                if(control2 != 0)
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control2, myPlayer);
                allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).addControlMarks(myPlayer, 1);
            }
            if (playerToAttack.get(2) != null) {
                if(control3 != 0)
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).getBoard().getDamageBox().increaseDamage(control3, myPlayer);
                allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).addControlMarks(myPlayer, 1);
            }
        }
        else return MessageEnum.PLAYERS_NOT_VALID;
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function that check for correct position
     * @param myPosition position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return true if ok
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
