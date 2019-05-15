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
        if(checkPosition(myPosition, positionToShot) != true) {
            if (check(myPosition, positionToShot)) {
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(3, player);
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().setMyMarksMap(player, 1);
            }
            else return MessageEnum.POSITION_UNREACHABLE;
        }
        else return MessageEnum.PLAYER_TOOMUCH_NEAR ;
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

    public boolean check(Position myPosition, Position positionToAttack) {
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
            if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
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
