/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class ShockWave {

    private boolean firstUse;
    private final int priceToPay = 1;
    private final Munitions munitionsToPay = Munitions.YELLOW;
    private final int priceToPayTsunami = 1;
    private final Munitions munitionsToPayTsunami = Munitions.YELLOW;

    //Costruttore
    private ShockWave(){
        firstUse = true;
    }
}
