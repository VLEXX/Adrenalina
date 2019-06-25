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
 * Weapon Flamethrower
 */
public class FlameThrower extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public FlameThrower() {
        super();
        super.getFirstPrice().put(Munitions.RED, 1);
        super.getSecondPrice().put(Munitions.YELLOW, 2);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_CELL, 1);
        super.setName("flamethrower");
    }

    /**
     * Attack for the DOMINATION mode at the spawn point
     * @param myPlayer player who attack
     * @param spawnPoint
     * @param allPlay current state game
     * @return OK or ATTACK_NOT_PRESENT
     */
    public MessageEnum firstAttack(Player myPlayer, SpawnPoint spawnPoint, InitializeAllPlay allPlay){
        if(allPlay.getStateSelectedMode().getSelectedmode() == Mode.DOMINATION){
            Position positionSP = allPlay.getCurrentPlayerState().get(spawnPoint).getPlayerposition();
            Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
            if(spawnPoint != null) {
                if (positionSP == myPosition) {
                    spawnPoint.getSPDamage().add(myPlayer);
                }
            }
        } else
            return MessageEnum.ATTACK_NOT_PRESENT;
        return MessageEnum.OK;
    }

    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToAttack, InitializeAllPlay allPlay){
        int control = 0;
        char move = 'F';
        char move2 = 'F';
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack1 = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        Position positionToAttack2 = null;
        if(playerToAttack.get(1) != null)
            positionToAttack2 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getPlayerposition();
        move = checkPosition(myPosition.getCurrentcell(), positionToAttack1.getCurrentcell());
        if(move == 'F')
            return MessageEnum.POSITION_NOT_VALID;
        move2 = checkPosition(positionToAttack1.getCurrentcell(), positionToAttack2.getCurrentcell());
        if(move2 == 'F' || move2 != move)
            return  MessageEnum.POSITION_NOT_VALID;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        return MessageEnum.OK;
    }

    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToFire, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        char move = checkPosition2(myPosition, positionToFire);
        if(move == 'F')
            return MessageEnum.POSITION_NOT_VALID;
        if(move == 'T')
            attack(myPlayer,positionToFire.getCurrentcell(),allPlay,2);
        else if(move == 'U'){
            attack(myPlayer,myPosition.getCurrentcell().getUpCell(),allPlay,2);
            attack(myPlayer,positionToFire.getCurrentcell(),allPlay,1);
        }else if(move == 'D'){
            attack(myPlayer,myPosition.getCurrentcell().getDownCell(),allPlay,2);
            attack(myPlayer,positionToFire.getCurrentcell(),allPlay,1);
        }else if(move == 'L'){
            attack(myPlayer,myPosition.getCurrentcell().getLeftCell(),allPlay,2);
            attack(myPlayer,positionToFire.getCurrentcell(),allPlay,1);
        }else if(move == 'R'){
            attack(myPlayer,myPosition.getCurrentcell().getRightCell(),allPlay,2);
            attack(myPlayer,positionToFire.getCurrentcell(),allPlay,1);
        }
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    private void attack(Player myPlayer, Cell cellToFire, InitializeAllPlay allPlay, int damage){
        int control = 0;
        for(int i = 0; i < cellToFire.getInCellPlayer().size(); i++){
            Player player = cellToFire.getInCellPlayer().get(i);
            if(allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
            if(control != 0)
                allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(control, myPlayer);
            allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(damage, myPlayer);
        }
    }

    /**
     * Function check the correct position to shot
     * @param current current cell of the player
     * @param shot cell when the player want to go
     * @return true if possible
     */
    private char checkPosition(Cell current, Cell shot) {
        if (current.getUpCell() != null) {
            if (current.getUpCell().getCellId() == shot.getCellId()) {
                return 'U';
            }
        }
        if (current.getDownCell() != null) {
            if (current.getDownCell().getCellId() == shot.getCellId()) {
                return 'D';
            }
        }
        if (current.getLeftCell() != null) {
            if (current.getLeftCell().getCellId() == shot.getCellId()) {
                return 'L';
            }
        }
        if (current.getRightCell() != null) {
            if (current.getRightCell().getCellId() == shot.getCellId()) {
                return 'R';
            }
        }
        return'F';
    }

    /**
     * Function that check the correct position to move a player, in this case the player to attack can be moved in a direction for max 2 cell
     * @param myPosition position of the player who shot
     * @param positionToGo position to go
     * @return true if correct
     */
    private char checkPosition2(Position myPosition, Position positionToGo) {
        if (myPosition.getCurrentcell().getUpCell() != null) {
            if (myPosition.getCurrentcell().getUpCell().getCellId() == positionToGo.getCurrentcell().getCellId()) {
                return 'T';
            } else if (myPosition.getCurrentcell().getUpCell().getUpCell() != null) {
                if (myPosition.getCurrentcell().getUpCell().getUpCell().getCellId() == positionToGo.getCurrentcell().getCellId())
                    return 'U';
            }
        }
        if (myPosition.getCurrentcell().getDownCell() != null) {
            if (myPosition.getCurrentcell().getDownCell().getCellId() == positionToGo.getCurrentcell().getCellId()) {
                return 'T';
            } else if (myPosition.getCurrentcell().getDownCell().getDownCell() != null) {
                if (myPosition.getCurrentcell().getDownCell().getDownCell().getCellId() == positionToGo.getCurrentcell().getCellId())
                    return 'D';
            }
        }
        if (myPosition.getCurrentcell().getLeftCell() != null) {
            if (myPosition.getCurrentcell().getLeftCell().getCellId() == positionToGo.getCurrentcell().getCellId()) {
                return 'T';
            } else if (myPosition.getCurrentcell().getLeftCell().getLeftCell() != null) {
                if (myPosition.getCurrentcell().getLeftCell().getLeftCell().getCellId() == positionToGo.getCurrentcell().getCellId())
                    return 'L';
            }
        }
        if (myPosition.getCurrentcell().getRightCell() != null) {
            if (myPosition.getCurrentcell().getRightCell().getCellId() == positionToGo.getCurrentcell().getCellId()) {
                return 'T';
            } else if (myPosition.getCurrentcell().getRightCell().getRightCell() != null) {
                if (myPosition.getCurrentcell().getRightCell().getRightCell().getCellId() == positionToGo.getCurrentcell().getCellId())
                    return 'R';
            }
        }
        return 'F';
    }
}
