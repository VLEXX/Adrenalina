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
 * Weapon Shotgun
 */
public class ShotGun extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public ShotGun() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 2);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
        super.setName("shotgun");
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to shot
     * @param positionToMove position where the player is moved
     * @param allPlay current state game
     * @return PLAYER_NOT_VALID or PLAYER_NOT_FOUND or POSITION_UNREACHABLE or OK
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if (playerToAttack.get(0) != null) {
            if (myPosition.getCurrentcell().getCellId() != positionToAttack.getCurrentcell().getCellId())
                return MessageEnum.PLAYER_NOT_VALID;
        } else return MessageEnum.PLAYER_NOT_FOUND;
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if (control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(3, myPlayer);
        if(positionToMove != null)
            if(checkPosition(positionToAttack.getCurrentcell(), positionToMove.getCurrentcell())){
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().removeInCellPlayer(playerToAttack.get(0));
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().setCurrentcell(positionToMove.getCurrentcell());
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().addInCellPlayer(playerToAttack.get(0));
            } else return MessageEnum.POSITION_UNREACHABLE;
        return MessageEnum.OK;
    }

    /**
     * Function long barrel mode attack
     * @param myPlayer player who attack
     * @param playerToAttack player to shot
     * @param positionToMove null
     * @param allPlay current state game
     * @return POSITION_NOT_VALID or OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(checkPosition(myPosition.getCurrentcell(), positionToAttack.getCurrentcell())){
            if(control != 0)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        } else return MessageEnum.POSITION_NOT_VALID;
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function check the correct position where a player want to move
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
