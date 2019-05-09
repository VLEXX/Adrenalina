/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class FlameThrower extends Weapon {

    //Costruttore
    public FlameThrower() {
        super();
        super.getFirstPrice().put(Munitions.RED, 1);
        super.getSecondPrice().put(Munitions.YELLOW, 2);
    }
}
