/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class RocketLauncher {

    private boolean firstUse;
    private final int priceToPay = 2;
    private final Munitions munitionsToPay = Munitions.RED;
    private final int priceToPayPortable = 1;
    private final Munitions munitionsToPayPortable = Munitions.BLUE;
    private final int priceToPayFragmentation = 1;
    private final Munitions munitionsToPayFragmentation = Munitions.YELLOW;

    //Costruttore
    private RocketLauncher(){
        firstUse = true;
    }
}
