//Author: Alex Saletti
package Model;

//Classe che rappresenta le carte munizioni
//Ogni cella dell'array "ammoList" corrisponde alla quantità di munizioni di un determinato colore presenti
//ammoList[0] => Rosse
//ammolist[1] => Gialle
//ammolist[2] => Blu


public class Ammo {
    private int[] ammoList; //munizioni presenti

    private PowerUp PossiblePowerUp; //powerup presente

    //costruttore
    public Ammo(){
        this.ammoList = new int[]{0,0,0};
        this.PossiblePowerUp = null;
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
    public PowerUp getPossiblePowerUp() {
        return PossiblePowerUp;
    }

    //setta il potenziamento
    public void setPossiblePowerUp(PowerUp possiblePowerUp) {
        PossiblePowerUp = possiblePowerUp;
    }
}
