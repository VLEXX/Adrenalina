/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class MachineGun {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final Munitions munitionsToPay1 = Munitions.BLUE;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.RED;
    private final int priceToPayFocused = 1;
    private final Munitions munitionsToPayFocused = Munitions.YELLOW;
    private final int priceToPayTripod = 1;
    private final Munitions munitionsToPayTripod = Munitions.BLUE;

    //Costruttore
    private MachineGun(){
        firstUse = true;
    }
}
