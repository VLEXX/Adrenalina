/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

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
    }
}
