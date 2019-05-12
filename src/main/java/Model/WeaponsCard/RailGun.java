/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;
import Model.WeaponsMessage;

/**
 * Weapon Railgun
 */
public class RailGun extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public RailGun() {
        super();
        super.setFirstPrice(Munitions.YELLOW, 2);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
        super.setWeaponsMessage(WeaponsMessage.MAX_ONE_PLAYER, 0);
        super.setWeaponsMessage(WeaponsMessage.MAX_TWO_PLAYER, 1);
    }
}
