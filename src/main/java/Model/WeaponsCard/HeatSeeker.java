/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class HeatSeeker extends Weapon {

    //Costruttore
    public HeatSeeker() {
        super();
        super.setFirstPrice(Munitions.RED, 2);
        super.setFirstPrice(Munitions.YELLOW, 1);
    }
}
