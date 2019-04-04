//Author: Giulia Rivara
package Model;

import java.util.ArrayList;

//Classe che inizializza la plancia giocatore
public class PlayerBoard {
    private boolean FirstPlayerCard;              //Carta primo giocatore
    private ArrayList <Weapons> WeaponsList;      //Armi giocatore
    private ArrayList <PowerUp> PowerUpList;      //Potenziamenti giocatore

    //Costruttore che setta tutti gli attributi a null e imposta "false" per la prima carta giocatore
    public PlayerBoard(){
        this.FirstPlayerCard = false;
        this.WeaponsList = new ArrayList<>();
        this.PowerUpList = new ArrayList<>();
    }

    //Ritorna la lista delle armi
    public ArrayList<Weapons> getWeaponsList() {
        return WeaponsList;
    }

    //Ritorna "true" se il giocatore ha preso la prima carta, "false" altrimenti
    public boolean getFirtsPlayerCard(){
        return FirstPlayerCard;
    }

    //Ritorna la lista dei potenziamenti
    public ArrayList<PowerUp> getPowerupList(){
        return PowerUpList;
    }

    //Setta la lista dei potenziamenti
    public void setPowerupList(ArrayList<PowerUp> powerupList) {
        this.PowerUpList = powerupList;
    }

    //Setta la lista delle armi
    public void setWeaponsList(ArrayList<Weapons> weaponsList) {
        this.WeaponsList = weaponsList;
    }

    //Setta a "true" la prima carta giocatore quando la pesca, "false" altrimenti
    public boolean setFirstPlayerCard(boolean firstPlayerCard) {
        return firstPlayerCard;
    }
}

