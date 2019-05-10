/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

/**
 * Weapon Powerglove
 */
public class PowerGlove extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public PowerGlove(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setSecondPrice(Munitions.BLUE, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
    }
}
