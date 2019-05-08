/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class GrenadeLauncher {

    private boolean firstUse;
    private final int priceToPay = 1;
    private final Munitions munitionsToPay = Munitions.RED;
    private final int priceToPayExtra = 1;
    private final Munitions munitionsToPayExtra = Munitions.RED;

    //Costruttore
    GrenadeLauncher(){
        firstUse = true;
    }

    public boolean isFirstUse() {
        return firstUse;
    }

    public int getPriceToPay() {
        return priceToPay;
    }

    public Munitions getMunitionsToPay() {
        return munitionsToPay;
    }

    public int getPriceToPayExtra() {
        return priceToPayExtra;
    }

    public Munitions getMunitionsToPayExtra() {
        return munitionsToPayExtra;
    }
}
