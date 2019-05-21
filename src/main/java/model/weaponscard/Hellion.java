/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Position;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weapon Hellion
 */
public class Hellion extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public Hellion() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 1);
        super.setName("hellion");
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToMove null
     * @param allPlay current game state
     * @return POSITION_NOT_FOUND or OK
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(check(myPosition, positionToAttack) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if(checkPosition(myPosition.getCurrentcell(), positionToAttack.getCurrentcell()) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        for(int i = 0; i < positionToAttack.getCurrentcell().getInCellPlayer().size(); i++) {
            allPlay.getCurrentPlayerState().get(playerToAttack.get(i)).addControlMarks(myPlayer, 1);
        }
        return MessageEnum.OK;
    }

    /**
     * Function nano-tracer attack
     * @param myPlayer player who attack
     * @param playerToAttack player to shot
     * @param positionToMove null
     * @param allPlay current state game
     * @return POSITION_NOT_FOUND or OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(check(myPosition, positionToAttack) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if(checkPosition(myPosition.getCurrentcell(), positionToAttack.getCurrentcell()) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        for(int i = 0; i < positionToAttack.getCurrentcell().getInCellPlayer().size(); i++) {
            allPlay.getCurrentPlayerState().get(playerToAttack.get(i)).addControlMarks(myPlayer, 2);
        }
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function that check the correct position to attack
     * @param myPosition position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return true if ok
     */
    public boolean check(Position myPosition, Position positionToAttack) {
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachable3Cells().size(); i++) {
            if (myPosition.getCurrentcell().getReachable3Cells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            return false;
        }
        return true;
    }

    /**
     * Function check the correct position to shot
     * @param current current cell of the player
     * @param shot cell when the player want to go
     * @return true if possible
     */
    private boolean checkPosition(Cell current, Cell shot) {
        if (current.getUpCell() != null) {
            if (current.getUpCell().getCellId() == shot.getCellId()) {
                return true;
            }
        }
        if (current.getDownCell() != null) {
            if (current.getDownCell().getCellId() == shot.getCellId()) {
                return true;
            }
        }
        if (current.getLeftCell() != null) {
            if (current.getLeftCell().getCellId() == shot.getCellId()) {
                return true;
            }
        }
        if (current.getRightCell() != null) {
            if (current.getRightCell().getCellId() == shot.getCellId()) {
                return true;
            }
        }
        return false;
    }
}
