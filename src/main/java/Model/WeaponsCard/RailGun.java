/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class RailGun extends WeaponsDad{

    //Costruttore
    private RailGun(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 2);
        super.setFirstPrice(Munitions.BLUE, 1);
    }
}
