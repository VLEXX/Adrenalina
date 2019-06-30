/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.Position;
import model.map.SpawnPoint;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weapon Whisper
 */
public class Whisper extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public Whisper() {
        super();
        super.setFirstPrice(Munitions.BLUE, 2);
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(false);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setName("whisper");
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

    /**
     * Function first attack
     * @param player player who shot
     * @param playerToAttack player to shot
     * @param allPlay current state game
     * @return Ok or PLAYER_UNREACHABLE or PLAYER_TOO_MUCH_NEAR
     */
    public MessageEnum firstAttack(Player player, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(player))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(player);
        Position myPosition = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToShot = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(checkPosition(myPosition, positionToShot) != true) {
            if (check(myPosition, positionToShot)) {
                if(control != 0) {
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(3 + control, player);
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().setMyMarksMap(player, 1);
                    allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).addControlMarks(player, 1);
                }
            }
            else return MessageEnum.POSITION_UNREACHABLE;
        }
        else return MessageEnum.PLAYER_TOO_MUCH_NEAR;
        return MessageEnum.OK;
    }

    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }
    /**
     * Function that check the correct position to shot
     * @param myPosition position of the player who shot
     * @param positionToAttack position to shot
     * @return true if correct
     */
    private boolean checkPosition(Position myPosition, Position positionToAttack){
        if(myPosition.getCurrentcell().getUpCell() != null) {
            if(myPosition.getCurrentcell().getUpCell().getCellId() == positionToAttack.getCurrentcell().getCellId())
                return true;
        }
        if(myPosition.getCurrentcell().getDownCell() != null){
            if(myPosition.getCurrentcell().getDownCell().getCellId() == positionToAttack.getCurrentcell().getCellId()){
                return true;
            }
        }
        if(myPosition.getCurrentcell().getLeftCell() != null){
            if(myPosition.getCurrentcell().getLeftCell().getCellId() == positionToAttack.getCurrentcell().getCellId()){
                return true;
            }
        }
        if(myPosition.getCurrentcell().getRightCell() != null){
            if(myPosition.getCurrentcell().getRightCell().getCellId() == positionToAttack.getCurrentcell().getCellId()){
                return true;
            }
        }
        return false;
    }

    /**
     * Function that check the correct position to attack
     * @param myPosition position of the player who attack
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
