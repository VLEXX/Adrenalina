/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

import java.util.HashMap;

public class HeatSeeker extends WeaponsDad{

    //Costruttore
    public HeatSeeker() {
        super();
        super.setFirstPrice(Munitions.RED, 2);
        super.setFirstPrice(Munitions.YELLOW, 1);
    }
}
