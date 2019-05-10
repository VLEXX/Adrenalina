/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

/**
 * Weapon Shockwawe
 */
public class ShockWave extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public ShockWave(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.YELLOW, 1);
        super.setCardColor(Munitions.YELLOW);
    }
}
