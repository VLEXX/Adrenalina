/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class RailGun {

    private boolean firstUse;
    private final int priceToPay1 = 2;
    private final Munitions munitionsToPay1 = Munitions.YELLOW;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.BLUE;

    //Costruttore
    private RailGun(){
        firstUse = true;
    }
}
