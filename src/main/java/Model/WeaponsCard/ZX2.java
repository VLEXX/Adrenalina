/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class ZX2 {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final Munitions munitionsToPay1 = Munitions.RED;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.YELLOW;

    //Costruttore
    private ZX2(){
        firstUse = true;
    }
}
