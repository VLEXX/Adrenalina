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
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 1);
        super.setName("Electroscythe");
    }

    /**
     * Function first attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param allPlay current state game
     * @return OK or POSITION_UNREACHABLE or PLAYERS_NOT_VALID
     * @author Giulia Rivara
     */
    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay) {
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        for (int i = 0; i < playerToAttack.size(); i++) {
            if (playerToAttack.get(i) != null) {
                for (int j = 0; j < myPosition.getCurrentcell().getInCellPlayer().size(); j++) {
                    Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(i)).getPlayerposition();
                    if (myPosition.getCurrentcell().getInCellPlayer().get(j) == positionToAttack.getCurrentcell().getInCellPlayer().get(j)) {
                        if (myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                            allPlay.getCurrentPlayerState().get(playerToAttack.get(i)).getBoard().getDamageBox().increaseDamage(1, myPlayer);
                        } else return MessageEnum.POSITION_UNREACHABLE;
                    } else return MessageEnum.PLAYERS_NOT_VALID;
                }
            } else return MessageEnum.PLAYERS_NOT_VALID;
        }
        return MessageEnum.OK;
    }

    /**
     * Function for the reaper attack
     * @param myPlayer player who attack
     * @param playerToAttack player to attack
     * @param allPlay current state game
     * @return OK or POSITION_NOT_FOUND
     * @author Giulia Rivara
     */
    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        Position myPosition = allPlay.getCurrentPlayerState().get(myPlayer).getPlayerposition();
        for (int i = 0; i < playerToAttack.size(); i++) {
            if (playerToAttack.get(i) != null) {
                for (int j = 0; j < myPosition.getCurrentcell().getInCellPlayer().size(); j++) {
                    Position positionToAttack = allPlay.getCurrentPlayerState().get(playerToAttack.get(i)).getPlayerposition();
                    if (myPosition.getCurrentcell().getInCellPlayer().get(j) == positionToAttack.getCurrentcell().getInCellPlayer().get(j)) {
                        if (myPosition.getCurrentcell().getCellId() == positionToAttack.getCurrentcell().getCellId()) {
                            allPlay.getCurrentPlayerState().get(playerToAttack.get(i)).getBoard().getDamageBox().increaseDamage(2, myPlayer);
                        } else return MessageEnum.POSITION_UNREACHABLE;
                    } else return MessageEnum.PLAYERS_NOT_VALID;
                }
            } else return MessageEnum.PLAYERS_NOT_VALID;
        }
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay) {
        return MessageEnum.ATTACK_NOT_PRESENT;
    }
}
