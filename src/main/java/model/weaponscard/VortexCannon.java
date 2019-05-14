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
 * weapon Vortexcannon
 */
public class VortexCannon extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public VortexCannon() {
        super();
        super.setFirstPrice(Munitions.RED, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_PLAYER, 1);
        super.setName("Vortexcannon");
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
