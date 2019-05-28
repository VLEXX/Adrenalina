/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Position;
import model.map.Room;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weapon Furnace
 */
public class Furnace extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public Furnace() {
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 1);
        super.setName("furnace");
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToAttack room to attack
     * @param allPlay current state game
     * @return POSITION_NOT_VALID or OK
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToAttack, InitializeAllPlay allPlay) {
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Room roomToAttack = positionToAttack.getCurrentroom();
        if(myPosition.getCurrentroom().getRoomId() == positionToAttack.getCurrentroom().getRoomId())
            return MessageEnum.POSITION_NOT_VALID;
        if(checkRoom(myPosition, roomToAttack) == false)
            return MessageEnum.POSITION_NOT_VALID;
        for(int i = 0; i < positionToAttack.getCurrentroom().getCellsList().size(); i++){
            for(int j = 0; j < positionToAttack.getCurrentroom().getCellsList().get(i).getInCellPlayer().size(); j++){
                control = allPlay.getCurrentPlayerState().get(roomToAttack.getCellsList().get(i).getInCellPlayer().get(j)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
                if(control != 0)
                    allPlay.getCurrentPlayerState().get(roomToAttack.getCellsList().get(i).getInCellPlayer().get(j)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
                allPlay.getCurrentPlayerState().get(roomToAttack.getCellsList().get(i).getInCellPlayer().get(j)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            }
        }
        return MessageEnum.OK;
    }

    /**
     * Function comfortable fire mode attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToAttack position in which player shot to all player present
     * @param allPlay current state game
     * @return POSITION_UNREACHABLE or OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToAttack, InitializeAllPlay allPlay) {
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if(checkPosition(myPosition.getCurrentcell(), positionToAttack.getCurrentcell()) == false)
            return MessageEnum.POSITION_UNREACHABLE;
        for( int i = 0; i < positionToAttack.getCurrentcell().getInCellPlayer().size(); i++){
            control = allPlay.getCurrentPlayerState().get(positionToAttack.getCurrentcell().getInCellPlayer().get(i)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
            if(control != 0)
                allPlay.getCurrentPlayerState().get(positionToAttack.getCurrentcell().getInCellPlayer().get(i)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
            allPlay.getCurrentPlayerState().get(positionToAttack.getCurrentcell().getInCellPlayer().get(i)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            allPlay.getCurrentPlayerState().get(positionToAttack.getCurrentcell().getInCellPlayer().get(i)).addControlMarks(myPlayer, 1);
        }
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay) {
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function check the correct position to shot
     * @param current current cell of the player
     * @param shot cell to shot
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

    /**
     * Function chech if the room is reachable
     * @param myPosition my position
     * @param roomToSHot room that i see
     * @return true if ok
     */
    private boolean checkRoom(Position myPosition, Room roomToSHot){
        for( int i = 0; i < myPosition.getCurrentcell().getReachable3Cells().size(); i++){
            for(int j = 0; j < roomToSHot.getCellsList().size(); j++){
                if(myPosition.getCurrentcell().getReachable3Cells().get(i) == roomToSHot.getCellsList().get(j)){
                    return true;
                }
            }
        }
        return false;
    }
}
