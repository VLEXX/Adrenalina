/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

import java.util.ArrayList;

/**
 * weapon Grenadelauncher
 */
public class GrenadeLauncher extends Weapon {

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
        super.setName("Grenadelauncher");
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
