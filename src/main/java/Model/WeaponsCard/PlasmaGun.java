/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class PlasmaGun {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final Munitions munitionsToPay1 = Munitions.BLUE;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.YELLOW;
    private final int priceToPayOverload = 1;
    private final Munitions munitionToPayObverload = Munitions.BLUE;

    //Costruttore
    private PlasmaGun(){
        firstUse = true;
    }
}
