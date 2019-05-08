/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class FlameThrower {

    private boolean firstUse;
    private final int priceToPay = 1;
    private final Munitions munitionsToPay = Munitions.RED;
    private final int priceToPayBarbecue = 2;
    private final Munitions munitionsToPayBarbecue = Munitions.YELLOW;

    //Costruttore
    FlameThrower(){
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

    public int getPriceToPayBarbecue() {
        return priceToPayBarbecue;
    }

    public Munitions getMunitionsToPayBarbecue() {
        return munitionsToPayBarbecue;
    }
}
