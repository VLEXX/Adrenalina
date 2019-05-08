/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class SledgeHammer {

    private boolean firstUse;
    private final int priceToPay = 1;
    private final Munitions munitionsToPay = Munitions.YELLOW;
    private final int priceToPayPolverize = 1;
    private final Munitions munitionsToPayPolverize = Munitions.RED;

    //Costruttore
    private SledgeHammer(){
        firstUse = true;
    }
}
