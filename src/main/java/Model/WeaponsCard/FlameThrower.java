/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

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
    }
}
