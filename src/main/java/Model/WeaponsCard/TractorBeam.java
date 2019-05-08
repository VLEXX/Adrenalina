/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class TractorBeam {

    private boolean firstUse;
    private final int priceToPay = 1;
    private final Munitions munitionsToPay = Munitions.BLUE;
    private final int priceToPayPunisher1 = 1;
    private final Munitions munitionsToPayPUnisher1 = Munitions.RED;
    private final int priceToPayPunisher2 = 1;
    private final Munitions munitionsToPayPUnisher2 = Munitions.YELLOW;

    //Costruttore
    private TractorBeam(){
        firstUse = true;
    }
}
