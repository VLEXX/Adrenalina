/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.*;

import java.util.ArrayList;

/**
 * Weapon Shotgun
 */
public class ShotGun extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public ShotGun() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 2);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 1);
        super.setName("Shotgun");
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
