/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.Cell;
import model.map.Position;
import model.map.SpawnPoint;
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
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER_MAX_TWO_CELL, 1);
        super.setName("sledgehammer");
    }

    /**
     * Attack for the DOMINATION mode at the spawn point
     * @param myPlayer player who attack
     * @param spawnPoint
     * @param allPlay current state game
     * @return OK or ATTACK_NOT_PRESENT or POSITION_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, SpawnPoint spawnPoint, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        int cellID = myPosition.getCurrentcell().getCellId();
        int roomID = myPosition.getCurrentroom().getRoomId();
        if(allPlay.getStateSelectedMode().getSelectedmode() == Mode.DOMINATION) {
            if (allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(roomID-1).getCellsList().get(cellID-1).getSpawnpointzone() == spawnPoint) {
                spawnPoint.getSPDamage().add(myPlayer);
            } else
                return MessageEnum.POSITION_NOT_VALID;
            return MessageEnum.OK;
        } else
            return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function first attack
     * @param myPlayer player who shot
     * @param playerToAttack player to shot
     * @param positionToMove null
     * @param allPlay current state game
     * @return OK or PLAYER_NOT_VALID or PLAYER_NOT_FOUND
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if (playerToAttack.get(0) != null) {
            Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
            if (myPosition.getCurrentcell().getCellId() != positionToAttack.getCurrentcell().getCellId())
                return MessageEnum.PLAYER_NOT_VALID;
        } else return MessageEnum.PLAYER_NOT_FOUND;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
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
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if (playerToAttack.get(0) != null) {
            Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
            if (myPosition.getCurrentcell().getCellId() != positionToAttack.getCurrentcell().getCellId())
                return MessageEnum.PLAYER_NOT_VALID;
        } else return MessageEnum.PLAYER_NOT_FOUND;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
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
     * Function that check the correct position to move a player, in this case the player to attack can be moved in a direction for max 2 cell
     * @param myPosition position of the player who shot
     * @param positionToGo position to go
     * @return true if correct
     */
    private boolean checkPosition(Position myPosition, Position positionToGo) {
        if (myPosition.getCurrentcell().getUpCell() != null) {
            if (myPosition.getCurrentcell().getUpCell().getCellId() == positionToGo.getCurrentcell().getCellId()) {
                return true;
            } else if (myPosition.getCurrentcell().getUpCell().getUpCell() != null) {
                if (myPosition.getCurrentcell().getUpCell().getUpCell().getCellId() == positionToGo.getCurrentcell().getCellId())
                    return true;
            }
        }
        if (myPosition.getCurrentcell().getDownCell() != null) {
            if (myPosition.getCurrentcell().getDownCell().getCellId() == positionToGo.getCurrentcell().getCellId()) {
                return true;
            } else if (myPosition.getCurrentcell().getDownCell().getDownCell() != null) {
                if (myPosition.getCurrentcell().getDownCell().getDownCell().getCellId() == positionToGo.getCurrentcell().getCellId())
                    return true;
            }
        }
        if (myPosition.getCurrentcell().getLeftCell() != null) {
            if (myPosition.getCurrentcell().getLeftCell().getCellId() == positionToGo.getCurrentcell().getCellId()) {
                return true;
            } else if (myPosition.getCurrentcell().getLeftCell().getLeftCell() != null) {
                if (myPosition.getCurrentcell().getLeftCell().getLeftCell().getCellId() == positionToGo.getCurrentcell().getCellId())
                    return true;
            }
        }
        if (myPosition.getCurrentcell().getRightCell() != null) {
            if (myPosition.getCurrentcell().getRightCell().getCellId() == positionToGo.getCurrentcell().getCellId()) {
                return true;
            } else if (myPosition.getCurrentcell().getRightCell().getRightCell() != null) {
                if (myPosition.getCurrentcell().getRightCell().getRightCell().getCellId() == positionToGo.getCurrentcell().getCellId())
                    return true;
            }
        }
        return false;
    }
}
