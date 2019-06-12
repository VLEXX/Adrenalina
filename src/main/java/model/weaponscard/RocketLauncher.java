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
 * Weapon Rocketlauncher
 */
public class RocketLauncher extends Weapon implements Serializable {

    private Player player1;
    private Position position1;

    /**
     * Constructor that set the cost of this weapon
     */
    public RocketLauncher() {
        super();
        super.setFirstPrice(Munitions.RED, 2);
        super.setSecondPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(true);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_CELL, 1);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_CELL, 2);
        super.setName("rocketlauncher");
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to shot
     * @param allPlay current state game
     * @return PLAYER_UNREACHABLE or POSITION_NOT_VALID or OK or POSITION_UNREACHABLE
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(check(myPosition, positionToAttack) == false)
            return MessageEnum.PLAYER_UNREACHABLE;
        if(myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId())
            return MessageEnum.POSITION_NOT_VALID;
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
        player1 = playerToAttack.get(0);
        position1 = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(positionToMove != null) {
            if (check2(positionToAttack, positionToMove)) {
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().removeInCellPlayer(playerToAttack.get(0));
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().setCurrentcell(positionToMove.getCurrentcell());
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().addInCellPlayer(playerToAttack.get(0));
            } else return MessageEnum.POSITION_UNREACHABLE;
        }
        return MessageEnum.OK;
    }

    /**
     * Function portable rockets attack
     * @param myPlayer player who attack
     * @param playerToAttack player to shot
     * @param positionToMove position where a player want to go
     * @param allPlay current state game
     * @return POSITION_UNREACHABLE or OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        Position myposition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if (check2(myposition, positionToMove)) {
            allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().getCurrentcell().removeInCellPlayer(myPlayer);
            allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().setCurrentcell(positionToMove.getCurrentcell());
            allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition().getCurrentcell().addInCellPlayer(myPlayer);
        } else return MessageEnum.POSITION_UNREACHABLE;
        return MessageEnum.OK;
    }

    /**
     * Function fragmentation tested attack
     * @param myPlayer player who shot
     * @param playerToAttack player to shot
     * @param allPlay current state game
     * @return PLAYER_NOT_FOUND or OK
     */
    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay) {
        int control = 0;
        for (int i = 0; i < position1.getCurrentcell().getInCellPlayer().size(); i++) {
            if (position1.getCurrentcell().getInCellPlayer().get(i) != player1) {
                if(allPlay.getCurrentPlayerState().get(position1.getCurrentcell().getInCellPlayer().get(i)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                    control = allPlay.getCurrentPlayerState().get(position1.getCurrentcell().getInCellPlayer().get(i)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
                if (control != 0)
                    allPlay.getCurrentPlayerState().get(position1.getCurrentcell().getInCellPlayer().get(i)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
                allPlay.getCurrentPlayerState().get(position1.getCurrentcell().getInCellPlayer().get(i)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            }
        }
        if(allPlay.getCurrentPlayerState().get(player1).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(player1).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(player1).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(player1).getBoard().getDamageBox().increaseDamage(1, myPlayer);
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
