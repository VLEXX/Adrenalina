/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.Cell;
import model.map.Position;
import model.map.SpawnPoint;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weapon Tractorbeam
 */
public class TractorBeam extends Weapon implements Serializable {

    /**
     * Constructor that set the cost of this weapon
     */
    public TractorBeam(){
        super();
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.YELLOW,1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER_MAX_TWO_CELL, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
        super.setName("tractorbeam");
    }

    /**
     * Attack for the DOMINATION mode at the spawn point
     * @param myPlayer player who attack
     * @param spawnPoint
     * @param allPlay current state game
     * @return OK or ATTACK_NOT_PRESENT or POSITION_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, SpawnPoint spawnPoint, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        int cellID = myPosition.getCurrentcell().getCellId();
        int roomID = myPosition.getCurrentroom().getRoomId();
        if(allPlay.getStateSelectedMode().getSelectedmode() == Mode.DOMINATION) {
            if (allPlay.getStateSelectedMap().getSelectedmap().getRoomList().get(roomID-1).getCellsList().get(cellID-1).getSpawnpointzone() == spawnPoint) {
                spawnPoint.getSPDamage().add(myPlayer);
            } else
                return MessageEnum.POSITION_NOT_VALID;
            return MessageEnum.OK;
        } else
            return MessageEnum.ATTACK_NOT_PRESENT;
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToMove where want to go
     * @param allPlay current state game
     * @return OK or POSITION_NOT_VALID
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        if(myPosition == positionToMove || check(myPosition,positionToMove)) {
            if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
            if (control != 0)
                allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().removeInCellPlayer(playerToAttack.get(0));
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().setCurrentcell(positionToMove.getCurrentcell());
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        } else
            return MessageEnum.POSITION_NOT_VALID;
        return MessageEnum.OK;
    }

    /**
     * Function punisher mode attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToMove null
     * @param allPlay current state game
     * @return PLAYER_NOT_VALID or OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition();
        if(myPosition != positionToAttack && check2(myPosition, positionToAttack) == false)
            return MessageEnum.PLAYER_NOT_VALID;
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().removeInCellPlayer(playerToAttack.get(0));
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().setCurrentcell(myPosition.getCurrentcell());
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getPlayerposition().getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
            control = allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
        if(control != 0)
            allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(control, myPlayer);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getDamageBox().increaseDamage(3, myPlayer);
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }

     /** Function that check for correct position
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


    /**
     * Check that the position is correct
     *
     * @param myPosition       position of the player who attack
     * @param positionToMove position to move
     * @return true if correct
     */
    private boolean check2(Position myPosition, Position positionToMove){
        boolean find = false;
        for (int i = 0; i < myPosition.getCurrentcell().getReachable2Cells().size(); i++) {
            if (myPosition.getCurrentcell().getReachable2Cells().get(i).getCellId() == positionToMove.getCurrentcell().getCellId()) {
                find = true;
                break;
            }
        }
        if (find == false)
            return false;
        return true;
    }
}
