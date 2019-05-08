/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class ShotGun extends WeaponsDad{

    //Costruttore
    private ShotGun(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 2);
    }
}
