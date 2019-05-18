/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.datapacket.DataPacket;
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
 * Weapon Plasmagun
 */
public class PlasmaGun extends Weapon implements Serializable {

    private Player player1;

    /**
     * Constructor that set the cost of this weapon
     */
    public PlasmaGun() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(true);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MYPLAYER, 1);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 2);
        super.setName("plasmagun");
    }

    /**
     * Function first attack
     *
     * @param player           player who attack
     * @param playerToAttack   player to attack
     * @param allPlay current state game
     * @return OK or POSITION_NOT_FOUND
     */
    public MessageEnum firstAttack(Player player, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position position = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(check(position, positionToAttack))
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, player);
        else return  MessageEnum.POSITION_NOT_FOUND;
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(player);
        if(control != 0);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, player);
        player1 = playerToAttack.get(0);
        return MessageEnum.OK;
    }

    /**
     * Function phase slip
     *
     * @param player       player who attack
     * @param playerToAttack player to attack = null
     * @param positionToMove position to go
     * @return OK or POSITION_UNREACHABLE
     */
    public MessageEnum secondAttack(Player player, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        Position myposition = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        if (checkPosition(myposition, positionToMove)) {
            allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell().removeInCellPlayer(player);
            allPlay.getCurrentPlayerState().get(player).getPlayerposition().setCurrentcell(positionToMove.getCurrentcell());
            allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell().addInCellPlayer(player);
        } else return MessageEnum.POSITION_UNREACHABLE;
        return MessageEnum.OK;
    }

    /**
     * Function overloaded shot
     *
     * @param player           player who attack
     * @param playerToAttack   player to attack
     * @param allPlay current state game
     * @return OK or POSITION_NOT_FOUND
     */
    public MessageEnum thirdAttack(Player player, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        int control = 0;
        if(player1 != playerToAttack.get(0))
            return MessageEnum.PLAYER_NOT_VALID;
        control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(player);
        Position position = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(check(position, positionToAttack)) {
            if(control != 0)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, player);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, player);
        }
        else return MessageEnum.POSITION_NOT_FOUND;
        return MessageEnum.OK;
    }

    /**
     * Check that the position is correct
     *
     * @param myPosition       position of the player who attack
     * @param positionToAttack position to attack
     * @return OK or POSITION_NOT_FOUND
     */
    private boolean check(Position myPosition, Position positionToAttack){
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachableCells().size(); i++) {
            if (myPosition.getCurrentcell().getReachableCells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false)
            return false;
        return true;
    }

    /**
     * Function that check the correct position to shot
     * @param myPosition position of the player who shot
     * @param positionToGo position to go
     * @return true if correct
     */
    private boolean checkPosition(Position myPosition, Position positionToGo) {
        if (myPosition.getCurrentcell().getUpCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getUpCell(), positionToGo.getCurrentcell()))
                return true;
        }
        if (myPosition.getCurrentcell().getDownCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getDownCell(), positionToGo.getCurrentcell())) {
                return true;
            }
        }
        if (myPosition.getCurrentcell().getLeftCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getLeftCell(), positionToGo.getCurrentcell())) {
                return true;
            }
        }
        if (myPosition.getCurrentcell().getRightCell() != null) {
            if (checkAround(myPosition.getCurrentcell().getRightCell(), positionToGo.getCurrentcell())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function that check the correct position to shot
     * @param current current cell of the player
     * @param go cell to go
     * @return true if correct
     */
    private boolean checkAround(Cell current, Cell go) {
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
