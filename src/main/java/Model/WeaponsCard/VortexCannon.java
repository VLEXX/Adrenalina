/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

/**
 * weapon Vortexcannon
 */
public class VortexCannon extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public VortexCannon(){
        super();
        super.setFirstPrice(Munitions.RED, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.RED);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
    }
}
