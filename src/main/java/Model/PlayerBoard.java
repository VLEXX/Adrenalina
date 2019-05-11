/**
 * @author Giulia Rivara
 */
package Model;

import Model.WeaponsCard.Weapon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that initialize the player board
 */
public class PlayerBoard implements Serializable {
    private Player player;
    private boolean firstPlayerCard;             //Carta primo giocatore
    private ArrayList<Weapon> weaponsList;      //Armi giocatore
    private ArrayList<PowerUp> powerUpList;      //Potenziamenti giocatore
    private DamageBox damageBox;                 //Plancia danni
    private MunitionsBox munitionsBox;           //Plancia munizioni
    private MarksBox marksBox;                   //Plancia marchi

    /**
     * Constructor
     */
    public PlayerBoard() {
        this.firstPlayerCard = false;
        this.weaponsList = new ArrayList<>();
        this.powerUpList = new ArrayList<>();
        this.damageBox = new DamageBox();
    }

    /**
     * @return the color of the player
     * @author Giulia Rivara
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the color of the player
     * @param player
     * @author Giulia Rivara
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the arm list
     * @author Giulia Rivara
     */
    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }

    /**
     * Set the arm list
     * @param weaponsList
     * @author Giulia Rivara
     */
    public void setWeaponsList(ArrayList<Weapon> weaponsList) {
        this.weaponsList = weaponsList;
    }

    /**
     * @return true if the player took the first card
     */
    public boolean getFirtsPlayerCard() {
        return firstPlayerCard;
    }

    /**
     * @return power up list
     */
    public ArrayList<PowerUp> getPowerupList() {
        return powerUpList;
    }

    /**
     * Set the power up list
     * @param powerupList
     * @author Giulia Rivara
     */
    public void setPowerupList(ArrayList<PowerUp> powerupList) {
        this.powerUpList = powerupList;
    }

    /**
     * @param firstPlayerCard
     * @return true if the player took the first card
     * @author Giulia Rivara
     */
    public boolean setFirstPlayerCard(boolean firstPlayerCard) {
        return firstPlayerCard;
    }

    /**
     * @return the damages box
     */
    public DamageBox getDamageBox() {
        return damageBox;
    }

    /**
     * Set the damages box
     * @param damageBox
     * @author Giulia Rivara
     */
    public void setDamageBox(DamageBox damageBox) {
        this.damageBox = damageBox;
    }

    /**
     * @return the marks box
     */
    public MarksBox getMarksBox() {
        return marksBox;
    }

    /**
     * Set the marks box
     * @param marksBox
     * @author Giulia Rivara
     */
    public void setMarksBox(MarksBox marksBox) {
        this.marksBox = marksBox;
    }

    /**
     * @return the munitions box
     * @author Giulia Rivara
     */
    public MunitionsBox getMunitionsBox() {
        return munitionsBox;
    }

    /**
     * Set the munitions box
     * @param munitionsBox
     * @author Giulia Rivara
     */
    public void setMunitionsBox(MunitionsBox munitionsBox) {
        this.munitionsBox = munitionsBox;
    }
}

