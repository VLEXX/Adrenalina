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
 * Weapon Sledgehammer
 */
public class SledgeHammer extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public SledgeHammer() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
        super.setName("sledgehammer");
    }

    /**
     * Function first attack
     * @param myPlayer player who shot
     * @param playerToAttack player to shot
     * @param allPlay current state game
     * @return OK or PLAYER_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if (playerToAttack.get(0) != null) {
            Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
            if (myPosition.getCurrentcell().getCellId() != positionToAttack.getCurrentcell().getCellId())
                return MessageEnum.PLAYER_NOT_VALID;
        } else return MessageEnum.PLAYER_NOT_FOUND;
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if (control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        return MessageEnum.OK;
    }

    /**
     * Function pulverize attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToMove position where a player want to go
     * @param allPlay current state game
     * @return PLAYER_NOT_VALID or OK or POSITION_UNREACHABLE
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        //TODO controllo discorso posizione
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if (playerToAttack.get(0) != null) {
            Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
            if (myPosition.getCurrentcell().getCellId() != positionToAttack.getCurrentcell().getCellId())
                return MessageEnum.PLAYER_NOT_VALID;
        } else return MessageEnum.PLAYER_NOT_FOUND;
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if (control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(3, myPlayer);
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if (checkPosition(positionToAttack, positionToMove)) {
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().removeInCellPlayer(playerToAttack.get(0));
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().setCurrentcell(positionToMove.getCurrentcell());
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        } else return MessageEnum.POSITION_UNREACHABLE;
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function that check the correct position to shot
     * @param myPosition position of the player who shot
     * @param positionToGo position to go
     * @return true if correct
     */
    private boolean checkPosition(Position myPosition, Position positionToGo) {
        if (myPosition.getCurrentcell().getUpCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getUpCell(), positionToGo.getCurrentcell()))
                return true;
        }
        if (myPosition.getCurrentcell().getDownCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getDownCell(), positionToGo.getCurrentcell())) {
                return true;
            }
        }
        if (myPosition.getCurrentcell().getLeftCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getLeftCell(), positionToGo.getCurrentcell())) {
                return true;
            }
        }
        if (myPosition.getCurrentcell().getRightCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getRightCell(), positionToGo.getCurrentcell())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function that check the correct position to shot
     * @param current current cell of the player
     * @param go cell to go
     * @return true if correct
     */
    private boolean checkAround(Cell current, Cell go) {
        if (current.getCellId() == go.getCellId()) {
            return true;
        }
        if (current.getUpCell() != null) {
            if (current.getUpCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        if (current.getDownCell() != null) {
            if (current.getDownCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        if (current.getLeftCell() != null) {
            if (current.getLeftCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        if (current.getRightCell() != null) {
            if (current.getRightCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        return false;
    }
}
