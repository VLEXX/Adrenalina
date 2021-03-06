/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.Cell;
import model.map.Position;
import model.map.Room;
import model.map.SpawnPoint;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weapon Powerglove
 */
public class PowerGlove extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public PowerGlove() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_CELL_MAX_TWO_PLAYER, 1);
        super.setName("powerglove");
    }

    /**
     * Attack for the DOMINATION mode at the spawn point
     * @param myPlayer player who attack
     * @param spawnPoint
     * @param allPlay current state game
     * @return OK or ATTACK_NOT_PRESENT or POSITION_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, SpawnPoint spawnPoint, InitializeAllPlay allPlay){
        if(allPlay.getStateSelectedMode().getSelectedmode() == Mode.DOMINATION) {
            for(Room room: allPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
                for(Cell cell: room.getCellsList()){
                    if(cell.getSpawnpointzone()!=null){
                        if(spawnPoint.getSpawnColor().equals(cell.getSpawnpointzone().getSpawnColor())){
                            cell.getSpawnpointzone().getSPDamage().add(myPlayer);
                        }
                    }
                }
            }
            return MessageEnum.OK;
        } else
            return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to shot
     * @param positionToMove null
     * @param allPlay current game state
     * @return OK or POSITION_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(checkPosition(myPosition.getCurrentcell(), positionToAttack.getCurrentcell()) == 'F')
            return MessageEnum.POSITION_NOT_VALID;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().getCurrentcell().removeInCellPlayer(myPlayer);
        allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().setCurrentcell(positionToAttack.getCurrentcell());
        allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().getCurrentcell().addInCellPlayer(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).addControlMarks(myPlayer, 2);
        return MessageEnum.OK;
    }

    /**
     * Function hundred fists mode
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToMove null
     * @param allPlay current state game
     * @return POSITION_NOT_VALID or OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay) {
        int control = 0;
        char move = 'F';
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        move = checkPosition(myPosition.getCurrentcell(), positionToAttack.getCurrentcell());
        if (move == 'F')
            return MessageEnum.POSITION_NOT_VALID;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().getCurrentcell().removeInCellPlayer(myPlayer);
        allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().setCurrentcell(positionToAttack.getCurrentcell());
        allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().getCurrentcell().addInCellPlayer(myPlayer);
        if (control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        if (playerToAttack.size() > 1){
            Position positionToAttack2 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getPlayerposition();
            if(move == 'R'){
                if(positionToAttack.getCurrentcell().getRightCell() != null && positionToAttack.getCurrentcell().getRightCell() != positionToAttack2.getCurrentcell()) {
                    return MessageEnum.POSITION_NOT_VALID;
                }
            } else if(move == 'L'){
                if(positionToAttack.getCurrentcell().getLeftCell() != null && positionToAttack.getCurrentcell().getLeftCell().getCellId() != positionToAttack2.getCurrentcell().getCellId()) {
                    return MessageEnum.POSITION_NOT_VALID;
                }
            } else if (move == 'U') {
                if(positionToAttack.getCurrentcell().getUpCell() != null && positionToAttack.getCurrentcell().getUpCell() != positionToAttack2.getCurrentcell()) {
                    return MessageEnum.POSITION_NOT_VALID;
                }
            } else if(move == 'D') {
                if (positionToAttack.getCurrentcell().getDownCell() != null && positionToAttack.getCurrentcell().getDownCell() != positionToAttack2.getCurrentcell()) {
                    return MessageEnum.POSITION_NOT_VALID;
                }
            }
            if(allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                control = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
            if(control != 0)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        }
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
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
}
