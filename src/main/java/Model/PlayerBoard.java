//Author: Giulia Rivara
package Model;

import java.util.ArrayList;

//Classe che inizializza la plancia giocatore
public class PlayerBoard {
    private boolean firstPlayerCard;              //Carta primo giocatore
    private ArrayList <Weapons> weaponsList;      //Armi giocatore
    private ArrayList <PowerUp> powerupList;      //Potenziamenti giocatore

    //Costruttore che setta tutti gli attributi a null e imposta "false" per la prima carta giocatore
    public PlayerBoard(){
        this.firstPlayerCard = false;
        this.weaponsList = new ArrayList<>();
        this.powerupList = new ArrayList<>();
    }

    //Ritorna la lista delle armi
    public ArrayList<Weapons> getWeaponsList() {
        return weaponsList;
    }

    //Ritorna "true" se il giocatore ha preso la prima carta, "false" altrimenti
    public boolean getFirtsPlayerCard(){
        return firstPlayerCard;
    }

    //Ritorna la lista dei potenziamenti
    public ArrayList<PowerUp> getPowerupList(){
        return powerupList;
    }

    //Setta la lista dei potenziamenti
    public void setPowerupList(ArrayList<PowerUp> powerupList) {
        this.powerupList = powerupList;
    }

    //Setta la lista delle armi
    public void setWeaponsList(ArrayList<Weapons> weaponsList) {
        this.weaponsList = weaponsList;
    }

    //Setta a "true" la prima carta giocatore quando la pesca, "false" altrimenti
    public boolean setFirstPlayerCard(boolean firstPlayerCard) {
        return firstPlayerCard;
    }
}

