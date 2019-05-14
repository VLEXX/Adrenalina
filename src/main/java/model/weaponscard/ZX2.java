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

import java.util.ArrayList;

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
        super.setName("ZX2");
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param allPlay current state game
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(check(myPosition, positionToAttack) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().setMyMarksMap(myPlayer, 2);
        return MessageEnum.OK;
    }

    /**
     * Function scanner mode
     * @param myPlayer player who attack
     * @param allPlay current state game
     * @param playerToAttack player to attack
     * @return OK or POSITION_UNREACHABLE
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay) {
        if(playerToAttack.get(0) != null && check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition()))
            return MessageEnum.POSITION_NOT_FOUND;
        if(playerToAttack.get(1) != null && check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getPlayerposition()))
            return MessageEnum.POSITION_NOT_FOUND;
        if(playerToAttack.get(2) != null && check(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition()))
            return MessageEnum.POSITION_NOT_FOUND;
        if (playerToAttack.get(0) != playerToAttack.get(1) && playerToAttack.get(1) != playerToAttack.get(2) && playerToAttack.get(0) != playerToAttack.get(2)) {
            if (playerToAttack.get(0) != null)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
            if (playerToAttack.get(1) != null)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
            if (playerToAttack.get(2) != null)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
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
