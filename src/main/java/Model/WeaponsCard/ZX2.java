/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

/**
 * Weapon ZX2
 */
public class ZX2 extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public ZX2(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.YELLOW);
        super.setSecondAttack(true);
        super.setThirdAttack(false);
    }
}
