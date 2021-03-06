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
 * Weapon Lockrifle
 */
public class LockRifle extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public LockRifle() {
        super();
        super.setFirstPrice(Munitions.BLUE, 2);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
        super.setName("lockrifle");
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
     * Function for the first attack of the weapon
     * @param myPlayer     player who attack
     * @param playerToAttack   player to attack
     * @param positionToMove null
     * @param allPlay       current game state
     * @return OK or POSITION_NOT_FOUND
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        if((playerToAttack.size() == 0)) {
            return MessageEnum.OK;
        }
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(!check(myPosition, positionToAttack))
            return MessageEnum.POSITION_NOT_FOUND;
        if(control != 0) {
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        }
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().setMyMarksMap(myPlayer, 1);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).addControlMarks(myPlayer, 1);
        return MessageEnum.OK;
    }

    /**
     * Function for second hook
     * @param myPlayer           player who attack
     * @param allPlay           current state game
     * @param playerToAttack   player to attack
     * @param positionToMove null
     * @return OK or POSITION_NOT_FOUND
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        if((playerToAttack.isEmpty()==true)) {
            return MessageEnum.OK;
        }
        if((playerToAttack.get(0).equals(Player.FLAG))) {
            return MessageEnum.WEAPON_ERROR;
        }
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(check(myPosition, positionToAttack) == false)
            return MessageEnum.POSITION_NOT_FOUND;
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).addControlMarks(myPlayer, 1);
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        if((playerToAttack == null) && (myPlayer == null) && (allPlay == null)) {
            return MessageEnum.OK;
        }
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function that check for correct position
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return true if ok
     */
    private boolean check(Position myPosition, Position positionToAttack) {
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getVisibleCells().size(); i++) {
            if (myPosition.getCurrentcell().getVisibleCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
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



