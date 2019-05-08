/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class ShotGun {

    private boolean firstUse;
    private final int priceToPay = 2;
    private final Munitions munitionsToPay = Munitions.YELLOW;

    //Costruttore
    private ShotGun(){
        firstUse = true;
    }
}
