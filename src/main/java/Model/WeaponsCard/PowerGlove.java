/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class PowerGlove {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final Munitions munitionsToPay1 = Munitions.BLUE;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.YELLOW;
    private final int priceToPayFist = 1;
    private final Munitions munitionsToPayFist = Munitions.BLUE;

    //Costruttore
    private PowerGlove(){
        firstUse = true;
    }
}
