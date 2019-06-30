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
 * Weapon Electroscythe
 */
public class Electroscythe extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public Electroscythe() {
        super();
        super.getFirstPrice().put(Munitions.BLUE, 1);
        super.getSecondPrice().put(Munitions.BLUE, 1);
        super.getSecondPrice().put(Munitions.RED, 1);
        super.setCardColor(Munitions.BLUE);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.NOTHING, 0);
        super.setWeaponsMessage(WeaponsMessage.NOTHING, 1);
        super.setName("electroscythe");
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
     * @param positionToMove null
     * @param allPlay current state game
     * @return OK
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay) {
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        for (int i = 0; i < myPosition.getCurrentcell().getInCellPlayer().size(); i++) {
            Player player = myPosition.getCurrentcell().getInCellPlayer().get(i);
            if (player != null && player != myPlayer) {
                if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                    control = allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
                if (control != 0) {
                    allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(control, myPlayer);
                    allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(1, myPlayer);
                }
            }
        }
        return MessageEnum.OK;
    }

    /**
     * Function for the reaper attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param positionToMove null
     * @param allPlay current state game
     * @return OK
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, Position positionToMove, InitializeAllPlay allPlay){
        int control = 0;
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        for (int i = 0; i < myPosition.getCurrentcell().getInCellPlayer().size(); i++) {
            Player player = myPosition.getCurrentcell().getInCellPlayer().get(i);
            if (player != null && player != myPlayer) {
                if(allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).getBoard().getMarksBox().getMyMarksMap().containsKey(myPlayer))
                    control = allPlay.getCurrentPlayerState().get(player).getBoard().getMarksBox().getMyMarksMap().get(myPlayer);
                if (control != 0) {
                    allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(control, myPlayer);
                    allPlay.getCurrentPlayerState().get(player).getBoard().getDamageBox().increaseDamage(2, myPlayer);
                }
            }
        }
        return MessageEnum.OK;    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay) {
        return MessageEnum.ATTACK_NOT_PRESENT;
    }
}
