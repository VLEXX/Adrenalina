/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Hellion {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final Munitions munitionsToPay1 = Munitions.YELLOW;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.RED;
    private final int priceToPayNanoTracers = 1;
    private final Munitions munitionsToPayNanoTracers = Munitions.RED;

    //Costruttore
    public Hellion(){
        firstUse = true;
    }

    public boolean isFirstUse() {
        return firstUse;
    }

    public int getPriceToPay1() {
        return priceToPay1;
    }

    public Munitions getMunitionsToPay1() {
        return munitionsToPay1;
    }

    public int getPriceToPay2() {
        return priceToPay2;
    }

    public Munitions getMunitionsToPay2() {
        return munitionsToPay2;
    }

    public int getPriceToPayNanoTracers() {
        return priceToPayNanoTracers;
    }

    public Munitions getMunitionsToPayNanoTracers() {
        return munitionsToPayNanoTracers;
    }
}
