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
 * Weapon Heatseeker
 */
public class HeatSeeker extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public HeatSeeker() {
        super();
        super.setFirstPrice(Munitions.RED, 2);
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(false);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setName("heatseeker");
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
     * @return Ok or ATTACK_NOT_PRESENT
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        if((playerToAttack.size() == 0)) {
            return MessageEnum.OK;
        }

        if((playerToAttack.get(0).equals(Player.FLAG))) {
            return MessageEnum.WEAPON_ERROR;
        }

        int control = 0;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(playerToAttack.get(0) != null && !checkNotSee(allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition(), allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition()))
            return MessageEnum.POSITION_UNREACHABLE;
        if(control != 0) {
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        }
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(3, myPlayer);
        return MessageEnum.OK;
    }

    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function that check the correct position of the player
     * @param myPosition position of the player who attack
     * @param positionToAttack  position of the player to attack
     * @return true if ok
     */
    private boolean checkNotSee(Position myPosition, Position positionToAttack) {
        boolean notfind = true;
        for (int i = 0; i < myPosition.getCurrentcell().getVisibleCells().size(); i++) {
            if (myPosition.getCurrentcell().getVisibleCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                notfind = false;
                break;
            }
        }
        if (notfind == false) {
            return false;
        }
        return true;
    }
}
