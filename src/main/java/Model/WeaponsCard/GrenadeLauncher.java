/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

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
    }
}
