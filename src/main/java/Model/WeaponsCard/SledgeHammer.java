/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

/**
 * Weapon Sledgehammer
 */
public class SledgeHammer extends Weapon {

    /**
     * Constructor that set the cost of this weapon
     */
    public SledgeHammer(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setSecondPrice(Munitions.RED, 1);
        super.setCardColor(Munitions.YELLOW);
    }
}
