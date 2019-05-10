/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

/**
 * Weapon Heatseeker
 */
public class HeatSeeker extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public HeatSeeker() {
        super();
        super.setFirstPrice(Munitions.RED, 2);
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.RED);
    }
}
