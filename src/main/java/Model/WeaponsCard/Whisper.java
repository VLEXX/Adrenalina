/**
 * @author Giulia Rivara
 */
package Model.WeaponsCard;

import Model.Munitions;

public class Whisper {

    private boolean firstUse;
    private final int priceToPay1 = 2;
    private final Munitions munitionsToPay1 = Munitions.BLUE;
    private final int priceToPay2 = 1;
    private final Munitions munitionsToPay2 = Munitions.YELLOW;

    //Costruttore
    private Whisper(){
        firstUse = true;
    }
}
