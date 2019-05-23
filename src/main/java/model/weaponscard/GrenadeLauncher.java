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
 * weapon Grenadelauncher
 */
public class GrenadeLauncher extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public GrenadeLauncher() {
        super();
        super.setFirstPrice(Munitions.RED, 1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 1);
        super.setName("grenadelauncher");
    }

    /**
     * Function first attack
     * @param myPlayer player who shot
     * @param playerToAttack player to shot
     * @param positionToMove position to move the player to shot
     * @param allPlay current state game
     * @return PLAYER_NOT_FOUND or OK or POSITION_UNREACHABLE
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(check(myPosition, positionToAttack) == false)
            return MessageEnum.PLAYER_NOT_FOUND;
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        if(positionToMove != null) {
            if(checkPosition(positionToAttack.getCurrentcell(), positionToMove.getCurrentcell())){
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().removeInCellPlayer(playerToAttack.get(0));
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().setCurrentcell(positionToMove.getCurrentcell());
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().addInCellPlayer(playerToAttack.get(0));
            } else return MessageEnum.POSITION_UNREACHABLE;
        }
        return MessageEnum.OK;
    }

    /**
     * Function grenade extra attack
     * @param myPlayer player who attack
     * @param playerToAttack player to shot
     * @param positionToAttack position to attack
     * @param allPlay current state game
     * @return POSITION_UNREACHABLE or OK or ATTACK_NOT_PRESENT
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToAttack, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if(check(myPosition, positionToAttack) == false)
            return MessageEnum.POSITION_UNREACHABLE;
        for(int i = 0; i < positionToAttack.getCurrentcell().getInCellPlayer().size(); i++){
            control = allPlay.getCurrentPlayerState().get(positionToAttack.getCurrentcell().getInCellPlayer().get(i)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
            if(control != 0)
                allPlay.getCurrentPlayerState().get(positionToAttack.getCurrentcell().getInCellPlayer().get(i)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
            allPlay.getCurrentPlayerState().get(positionToAttack.getCurrentcell().getInCellPlayer().get(i)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
        }
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function that check for correct position reachable
     * @param myPosition       position of the player who attack
     * @param positionToAttack position of the player to attack
     * @return true if ok
     */
    public boolean check(Position myPosition, Position positionToAttack) {
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachable3Cells().size(); i++) {
            if (myPosition.getCurrentcell().getReachable3Cells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false) {
            return false;
        }
        return true;
    }

    /**
     * Function check the correct position where a player want to move
     * @param current current cell of the player
     * @param go cell when the player want to go
     * @return true if possible
     */
    private boolean checkPosition(Cell current, Cell go) {
        if (current.getCellId() == go.getCellId()) {
            return true;
        }
        if (current.getUpCell() != null) {
            if (current.getUpCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        if (current.getDownCell() != null) {
            if (current.getDownCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        if (current.getLeftCell() != null) {
            if (current.getLeftCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        if (current.getRightCell() != null) {
            if (current.getRightCell().getCellId() == go.getCellId()) {
                return true;
            }
        }
        return false;
    }
}
