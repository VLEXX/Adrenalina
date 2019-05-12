/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import Model.WeaponsMessage;

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
    }
}
