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
        Position myPosition = allPlay.getCurrentPlayerState().get(player).getPlayerposition();
        if (check2(myPosition, positionToMove)) {
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
        for (int i = 0; i < myPosition.getCurrentcell().getReachable3Cells().size(); i++) {
            if (myPosition.getCurrentcell().getReachable3Cells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false)
            return false;
        return true;
    }

    /**
     * Check that the position is correct
     *
     * @param myPosition       position of the player who attack
     * @param positionToAttack position to attack
     * @return OK or POSITION_NOT_FOUND
     */
    private boolean check2(Position myPosition, Position positionToAttack){
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachable2Cells().size(); i++) {
            if (myPosition.getCurrentcell().getReachable2Cells().get(i).getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false)
            return false;
        return true;
    }
}
