/**
 * @author Federico Scatà
 */
package view;


import model.playerdata.CurrentPlayerState;
import model.weaponscard.Weapon;

/**
 * Class that contains currentPlayerState and temporary weapon for second and third attack
 */
public class ViewCurrentPlayerState {
    private CurrentPlayerState currentPlayerState; //memorizza lo stato corrente del giocatore per la view
    private Weapon weaponMultAttacks;

    public ViewCurrentPlayerState(){
        currentPlayerState = null;
        weaponMultAttacks = null;
    }

    public CurrentPlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public void setCurrentPlayerState(CurrentPlayerState currentPlayerState) {
        this.currentPlayerState = currentPlayerState;
    }

    public void setWeaponMultAttacks(Weapon weaponMultAttacks) {
        this.weaponMultAttacks = weaponMultAttacks;
    }

    public Weapon getWeaponMultAttacks() {
        return weaponMultAttacks;
    }
}