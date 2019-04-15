//Author: Federico Scatà e Giulia Rivara
package Model;

import Model.Ammo;
import Model.PowerUp;
import Model.Weapons;
import java.util.Stack;

public class InitializeDeck {
    private Stack<Ammo> ammodeck;
    private Stack<Weapons> weaponsdeck;
    private Stack<PowerUp> powerupdeck;

    public InitializeDeck(){
        ammodeck = new Stack<>();
        weaponsdeck = new Stack<>();
        powerupdeck = new Stack<>();
    }
}
