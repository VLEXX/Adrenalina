/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

import java.util.ArrayList;

/**
 * Weapon Plamethrower
 */
public class FlameThrower extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public FlameThrower() {
        super();
        super.getFirstPrice().put(Munitions.RED, 1);
        super.getSecondPrice().put(Munitions.YELLOW, 2);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER_FORCELL, 0);
        super.setWeaponsMessage(WeaponsMessage.ALL_PLAYER_INTWOCELL, 1);
        super.setName("Flamethrower");
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
