/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class ShotGun extends Weapon {

    //Costruttore
    public ShotGun(){
        super();
        super.setFirstPrice(Munitions.YELLOW, 2);
    }
}
