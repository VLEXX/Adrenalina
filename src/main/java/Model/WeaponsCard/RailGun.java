/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

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
    }
}
