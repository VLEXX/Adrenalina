/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

import java.util.ArrayList;

/**
 * Weapon Rocketlauncher
 */
public class RocketLauncher extends Weapon {

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
        super.setWeaponsMessage(WeaponsMessage.MYPLAYER, 1);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INCELL, 2);
        super.setName("Rocketlauncher");
    }

    public MessageEnum firstAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.OK;
    }

    public MessageEnum secondAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.OK;
    }

    public MessageEnum thirdAttack(Player myPlayer, ArrayList<Player> playerToAttack, InitializeAllPlay allPlay){
        return MessageEnum.OK;
    }
}
