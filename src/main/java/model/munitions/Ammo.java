/**
 * @author Alex Saletti
 */
package model.munitions;

//Classe che rappresenta le carte munizioni
//Ogni cella dell'array "ammoList" corrisponde alla quantità di munizioni di un determinato colore presenti
//ammoList[0] => Rosse
//ammolist[1] => Gialle
//ammolist[2] => Blu


import java.io.Serializable;

public class Ammo implements Serializable {
    private int[] ammoList; //munizioni presenti

    private boolean PossiblePowerUp; //powerup presente

    //costruttore
    public Ammo() {
        this.ammoList = new int[]{0, 0, 0};
        this.PossiblePowerUp = false;
    }

    //restituisce l'array munizioni
    public int[] getAmmoList() {
        return ammoList;
    }

    //setta una cella dell'array munizioni
    public void setAmmoList(int[] ammo) {
        this.ammoList = ammo;
    }

    //restituisce il potenziamento quando presente
    public boolean getPossiblePowerUp() {
        return PossiblePowerUp;
    }

    //setta il potenziamento
    public void setPossiblePowerUp(boolean possiblePowerUp) {
        PossiblePowerUp = possiblePowerUp;
    }
}
