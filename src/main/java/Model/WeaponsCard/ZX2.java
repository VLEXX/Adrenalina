/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class ZX2 extends WeaponsDad{

    //Costruttore
    private ZX2(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.RED, 1);
    }
}
