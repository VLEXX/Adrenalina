//Author: Federico Scatà

package Model;

import java.util.Stack;

//Classe che memorizza lo stato corrente dei mazzi delle Armi, Munizioni e Potenziamenti
public class CurrentDeckState {
    private Stack<Ammo> ammodeck;           //Mazzo delle munizioni
    private Stack<Weapons> weaponsdeck;     //Mazzo delle armi
    private Stack<PowerUp> powerupdeck;     //Mazzo dei potenziamenti

    //costruttore
    public CurrentDeckState(){
        this.ammodeck=null;
        this.weaponsdeck=null;
        this.powerupdeck=null;
    }

    //ritorna il mazzo delle munizioni
    public Stack<Ammo> getAmmodeck() {
        return this.ammodeck;
    }

    //ritorna il mazzo delle armi
    public Stack<Weapons> getWeaponsdeck() {
        return this.weaponsdeck;
    }

    //ritorna il mazzo dei potenziamenti
    public Stack<PowerUp> getPowerupdeck() {
        return this.powerupdeck;
    }
}
