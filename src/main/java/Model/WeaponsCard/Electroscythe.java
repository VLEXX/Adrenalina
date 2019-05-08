/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Electroscythe {

    private boolean firstUse;
    private final int priceToPay = 1;
    private final Munitions munitionsToPay = Munitions.BLUE;
    private final int priceToPayReaper1 = 1;
    private final Munitions munitionsToPayReaper1 = Munitions.BLUE;
    private final int priceToPayReaper2 = 1;
    private final Munitions munitionsToPayReaper2 = Munitions.RED;

    //Costruttore
    public Electroscythe(){
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

    public int getPriceToPayReaper1() {
        return priceToPayReaper1;
    }

    public Munitions getMunitionsToPayReaper1() {
        return munitionsToPayReaper1;
    }

    public int getPriceToPayReaper2() {
        return priceToPayReaper2;
    }

    public Munitions getMunitionsToPayReaper2() {
        return munitionsToPayReaper2;
    }
}
