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

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weapon Cyberblase
 */
public class Cyberblade extends Weapon implements Serializable {

    private Player player1;

    /**
     * Constructor that set the cost of this weapon
     */
    public Cyberblade() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(true);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_CELL, 1);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 2);
        super.setName("cyberblade");
    }

    /**
     * Function first attack
     *
     * @param myPlayer       player who attack
     * @param playerToAttack player to attack
     * @param allPlay        current state game
     * @return OK or PLAYER_NOT_FOUND or PLAYER_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay) {
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if (playerToAttack.get(0) != null) {
            Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
            if (myPosition.getCurrentcell().getCellId() != positionToAttack.getCurrentcell().getCellId())
                return MessageEnum.PLAYER_NOT_VALID;
        } else return MessageEnum.PLAYER_NOT_FOUND;
        if (allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if (control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        player1 = playerToAttack.get(0);
        return MessageEnum.OK;
    }

    /**
     * Function shadow pitch attack
     *
     * @param myPlayer       player who shot
     * @param playerToAttack player to shot
     * @param positionToMove position where the player want to go
     * @param allPlay        current state game
     * @return POSITION_UNREACHABLE or OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay) {
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if (checkPosition(myPosition.getCurrentcell(), positionToMove.getCurrentcell())) {
            allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().getCurrentcell().removeInCellPlayer(myPlayer);
            allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().setCurrentcell(positionToMove.getCurrentcell());
            allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().getCurrentcell().addInCellPlayer(myPlayer);
        } else return MessageEnum.POSITION_UNREACHABLE;
        return MessageEnum.OK;
    }

    /**
     * Function chopping mode
     *
     * @param myPlayer       player who shot
     * @param playerToAttack player to shot
     * @param allPlay        current state game
     * @return PLAYER_NOT_VALID or OK
     */
    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay) {
        int control = 0;
        if (player1 == playerToAttack.get(0))
            return MessageEnum.PLAYER_NOT_VALID;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if (myPosition.getCurrentcell().getCellId() != positionToAttack.getCurrentcell().getCellId())
            return MessageEnum.PLAYER_NOT_VALID;
        if (allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if (control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        return MessageEnum.OK;
    }

    /**
     * Function checks the correct position where a player want to go
     *
     * @param current current cell of the player
     * @param go      cell when the player want to go
     * @return true if possible
     */
    private boolean checkPosition(Cell current, Cell go) {
        if (current.getCellId() == go.getCellId())
            return true;
        if (current.getUpCell() != null) {
            if (current.getUpCell().getCellId() == go.getCellId())
                return true;
        }
        if (current.getDownCell() != null) {
            if (current.getDownCell().getCellId() == go.getCellId())
                return true;
        }
        if (current.getLeftCell() != null) {
            if (current.getLeftCell().getCellId() == go.getCellId())
                return true;
        }
        if (current.getRightCell() != null) {
            if (current.getRightCell().getCellId() == go.getCellId())
                return true;
        }
        return false;
    }
}