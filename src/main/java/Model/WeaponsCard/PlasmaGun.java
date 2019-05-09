/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class PlasmaGun extends Weapon {

    //Costruttore
    public PlasmaGun(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 1);
        super.setFirstPrice(Munitions.BLUE, 1);
        super.setThirdPrice(Munitions.BLUE, 1);
    }
}
