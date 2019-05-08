/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Thor {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final Munitions munitionsToPay1 = Munitions.BLUE;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.RED;
    private final int priceToPayChain = 1;
    private final Munitions munitionsToPayChain = Munitions.BLUE;
    private final int priceToPayVoltage = 1;
    private final Munitions munitionsToPayVoltage =  Munitions.BLUE;

    //Costruttore
    private Thor(){
        firstUse = true;
    }
}
