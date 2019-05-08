/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Cyberblade {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay1 = Munitions.YELLOW;
    private final Munitions munitionsToPay2 = Munitions.RED;
    private final int priceToPayCrumble = 1;
    private final Munitions munitionsToPayCrumble = Munitions.YELLOW;

    //Costruttore
    public Cyberblade(){
        firstUse = true;
    }

    public boolean isFirstUse() {
        return firstUse;
    }

    public int getPriceToPay1() {
        return priceToPay1;
    }

    public int getPriceToPay2() {
        return priceToPay2;
    }

    public int getPriceToPayCrumble() {
        return priceToPayCrumble;
    }

    public Munitions getMunitionsToPay1() {
        return munitionsToPay1;
    }

    public Munitions getMunitionsToPay2() {
        return munitionsToPay2;
    }

    public Munitions getMunitionsToPayCrumble() {
        return munitionsToPayCrumble;
    }
}
