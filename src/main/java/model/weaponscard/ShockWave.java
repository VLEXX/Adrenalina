/**
 * @author Giulia Rivara
 */
package model.weaponscard;

import model.gamedata.InitializeAllPlay;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.datapacket.MessageEnum;
import model.datapacket.WeaponsMessage;

import java.util.ArrayList;

/**
 * Weapon Shockwawe
 */
public class ShockWave extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public ShockWave() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_THREE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_ONEDISTANCE, 1);
        super.setName("Shockwave");
    }

    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.OK;
    }

    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.ATTACK_NOT_PRESENT;
    }
}
