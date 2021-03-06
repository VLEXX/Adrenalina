/**
 * @author Giulia Rivara
 */
package model.playerdata;

import model.powerups.PowerUp;
import model.weaponscard.Weapon;

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
        this.munitionsBox = new MunitionsBox();
        this.marksBox = new MarksBox();
    }

    /**
     * @return the color of the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the color of the player
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the arm list
     */
    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }

    /**
     * Set the arm list
     * @param weaponsList
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
     */
    public void setPowerupList(ArrayList<PowerUp> powerupList) {
        this.powerUpList = powerupList;
    }

    /**
     * @param firstPlayerCard
     * @return true if the player took the first card
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
     */
    public void setMarksBox(MarksBox marksBox) {
        this.marksBox = marksBox;
    }

    /**
     * @return the munitions box
     */
    public MunitionsBox getMunitionsBox() {
        return munitionsBox;
    }

    /**
     * Set the munitions box
     * @param munitionsBox
     */
    public void setMunitionsBox(MunitionsBox munitionsBox) {
        this.munitionsBox = munitionsBox;
    }
}

