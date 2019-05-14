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
     * Function first attack
     * @param player player who shot
     * @param playerToAttack player to shot
     * @param allPlay current state game
     * @return Ok or PLAYER_UNREACHABLE
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player player, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToShot = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(checkPosition(myPosition, positionToShot)){
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(3, player);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().setMyMarksMap(player, 1);
        }
        else return MessageEnum.PLAYER_UNREACHABLE;
        return MessageEnum.OK;
    }

    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
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
     * @author Giulia Rivara
     */
    private boolean checkPosition(Position myPosition, Position positionToAttack){
        if(myPosition.getCurrentcell().getUpCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getUpCell(), positionToAttack.getCurrentcell()))
                return true;
        }
        if(myPosition.getCurrentcell().getDownCell() != null){
            if(checkAround(myPosition.getCurrentcell().getDownCell(), positionToAttack.getCurrentcell())){
                return true;
            }
        }
        if(myPosition.getCurrentcell().getLeftCell() != null){
            if(checkAround(myPosition.getCurrentcell().getLeftCell(), positionToAttack.getCurrentcell())){
                return true;
            }
        }
        if(myPosition.getCurrentcell().getRightCell() != null){
            if(checkAround(myPosition.getCurrentcell().getRightCell(), positionToAttack.getCurrentcell())){
                return true;
            }
        }
        return false;
    }

    /**
     * Function that check the correct position to attack
     * @param current current cell of the player
     * @param attack cell to attack
     * @return true if correct
     * @author Giulia Rivara
     */
    private boolean checkAround(Cell current, Cell attack){
        if(current.getCellId() == attack.getCellId()){
            return true;
        }
        if(current.getUpCell() != null) {
            if (current.getUpCell().getCellId() == attack.getCellId()) {
                return true;
            }
        }
        if(current.getDownCell() != null){
            if(current.getDownCell().getCellId() == attack.getCellId()){
                return true;
            }
        }
        if(current.getLeftCell() != null){
            if(current.getLeftCell().getCellId() == attack.getCellId()){
                return true;
            }
        }
        if(current.getRightCell() != null){
            if(current.getRightCell().getCellId() == attack.getCellId()){
                return true;
            }
        }
        return false;
    }
}
