/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class VortexCannon {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final Munitions munitionsToPay1 = Munitions.RED;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.BLUE;
    private final int priceToPaBlackHole = 1;
    private final Munitions munitionsToPayBlackHole = Munitions.RED;

    //Costruttore
    private VortexCannon(){
        firstUse = true;
    }
}
