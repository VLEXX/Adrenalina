/**
 * @author Giulia Rivara
 */
package Model;

import Model.WeaponsCard.Weapon;

import java.io.Serializable;
import java.util.ArrayList;

//Classe che inizializza la plancia giocatore
public class PlayerBoard implements Serializable {
    private Player player;
    private boolean firstPlayerCard;             //Carta primo giocatore
    private ArrayList<Weapon> weaponsList;      //Armi giocatore
    private ArrayList<PowerUp> powerUpList;      //Potenziamenti giocatore
    private DamageBox damageBox;                 //Plancia danni
    private MunitionsBox munitionsBox;           //Plancia munizioni
    private MarksBox marksBox;                   //Plancia marchi

    //Costruttore che setta tutti gli attributi a null e imposta "false" per la prima carta giocatore
    public PlayerBoard(){
        this.firstPlayerCard = false;
        this.weaponsList = new ArrayList<>();
        this.powerUpList = new ArrayList<>();
        this.damageBox = new DamageBox();
    }

    //Ritorna il colore giocatore
    public Player getPlayer() {
        return player;
    }

    //Setta il colore giocatore
    public void setPlayer(Player player) {
        this.player = player;
    }

    //Ritorna la lista delle armi
    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }

    //Ritorna "true" se il giocatore ha preso la prima carta, "false" altrimenti
    public boolean getFirtsPlayerCard(){
        return firstPlayerCard;
    }

    //Ritorna la lista dei potenziamenti
    public ArrayList<PowerUp> getPowerupList(){
        return powerUpList;
    }

    //Setta la lista dei potenziamenti
    public void setPowerupList(ArrayList<PowerUp> powerupList) {
        this.powerUpList = powerupList;
    }

    //Setta la lista delle armi
    public void setWeaponsList(ArrayList<Weapon> weaponsList) {
        this.weaponsList = weaponsList;
    }

    //Setta a "true" la prima carta giocatore quando la pesca, "false" altrimenti
    public boolean setFirstPlayerCard(boolean firstPlayerCard) {
        return firstPlayerCard;
    }

    //Ritorna la damagebox
    public DamageBox getDamageBox() {
        return damageBox;
    }

    //Setta la damagebox
    public void setDamageBox(DamageBox damageBox) {
        this.damageBox = damageBox;
    }

    //Ritorna la marksBox
    public MarksBox getMarksBox() {
        return marksBox;
    }

    //Setta la marksBox
    public void setMarksBox(MarksBox marksBox) {
        this.marksBox = marksBox;
    }

    //Ritorna la munitionsBox
    public MunitionsBox getMunitionsBox() {
        return munitionsBox;
    }

    //Setta la munitionsBox
    public void setMunitionsBox(MunitionsBox munitionsBox) {
        this.munitionsBox = munitionsBox;
    }
}

