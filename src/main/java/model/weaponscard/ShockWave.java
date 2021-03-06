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
 * Weapon Shockwawe
 */
public class ShockWave extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public ShockWave() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_THREE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.NOTHING, 1);
        super.setName("shockwave");
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
     * @param playerToAttack player to attack
     * @param positionToMove null
     * @param allPlay current state game
     * @return OK or POSITION_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position positionToAttack1 = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack2 = allPlay.getCurrentPlayerState().get(playerToAttack.get(1)).getPlayerposition();
        Position positionToAttack3 = allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).getPlayerposition();
        if(playerToAttack.get(0) != null) {
            if (checkPosition(myPosition.getCurrentcell(), positionToAttack1.getCurrentcell()) == false)
                return MessageEnum.POSITION_NOT_VALID;
        }
        if(playerToAttack.size() > 1) {
            if (checkPosition(myPosition.getCurrentcell(), positionToAttack2.getCurrentcell()) == false)
                return MessageEnum.POSITION_NOT_VALID;
        }
        if(playerToAttack.size() > 2) {
            if (checkPosition(myPosition.getCurrentcell(), positionToAttack3.getCurrentcell()) == false)
                return MessageEnum.POSITION_NOT_VALID;
        }
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
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(2)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        return MessageEnum.OK;
    }

    /**
     * Function tsunami mode attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToMove null
     * @param allPlay current state game
     * @return OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if(myPosition.getCurrentcell().getUpCell() != null){
            for(int i = 0; i < myPosition.getCurrentcell().getUpCell().getInCellPlayer().size(); i++){
                Player player = myPosition.getCurrentcell().getUpCell().getInCellPlayer().get(i);
                if(allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                    control = allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
                if(control != 0)
                    allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(control, myPlayer);
                allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            }
        }
        if(myPosition.getCurrentcell().getDownCell() != null){
            for(int i = 0; i < myPosition.getCurrentcell().getDownCell().getInCellPlayer().size(); i++){
                Player player = myPosition.getCurrentcell().getDownCell().getInCellPlayer().get(i);
                if(allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                    control = allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
                if(control != 0)
                    allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(control, myPlayer);
                allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            }
        }
        if(myPosition.getCurrentcell().getRightCell() != null){
            for(int i = 0; i < myPosition.getCurrentcell().getRightCell().getInCellPlayer().size(); i++){
                Player player = myPosition.getCurrentcell().getRightCell().getInCellPlayer().get(i);
                if(allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                    control = allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
                if(control != 0)
                    allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(control, myPlayer);
                allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            }
        }
        if(myPosition.getCurrentcell().getLeftCell() != null){
            for(int i = 0; i < myPosition.getCurrentcell().getLeftCell().getInCellPlayer().size(); i++){
                Player player = myPosition.getCurrentcell().getLeftCell().getInCellPlayer().get(i);
                if(allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                    control = allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
                if(control != 0)
                    allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(control, myPlayer);
                allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            }
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
