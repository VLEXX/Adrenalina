/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.map.Position;
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
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param allPlay current state game
     * @return OK or PLAYERS_NOT_VALID
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
     * @param allPlay current state game
     * @return OK or PLAYERS_NOT_VALID
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
