/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Furnace {

    private boolean firstUse;
    private final int priceToPay1 = 1;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay1 = Munitions.BLUE;
    private final Munitions munitionsToPay2 = Munitions.RED;

    //Costruttore
    Furnace(){
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
}
