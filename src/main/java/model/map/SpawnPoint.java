/**
 * @author Alex Saletti
 */
package model.map;

import model.munitions.Munitions;
import model.playerdata.Player;
import model.weaponscard.Weapon;

import java.io.Serializable;
import java.util.ArrayList;

public class SpawnPoint implements Serializable {
    private Weapon[] SpawnWeaponsList;  //lista armi presenti allo spawn
    private Munitions spawnColor;
    private ArrayList<Player> damageToSP;
    private short[] pointArray;



    public SpawnPoint(Munitions m) {                //costruttore
        this.SpawnWeaponsList = new Weapon[]{null, null, null};
        this.spawnColor = m;
        this.damageToSP = new ArrayList<>();
        this.pointArray = new short[]{8,6,4,2,1};
    }

    public Munitions getSpawnColor() {
        return spawnColor;
    }

    //inserice l'arma nella posizione indicata dall'indice
    public void addWeapon(Weapon w, int i) {
        SpawnWeaponsList[i] = w;
    }

    //restituisce l'arma nella posizione i settando a null la cella occupata dall'arma
    public Weapon removeWeapon(int i) {
        Weapon tmp = SpawnWeaponsList[i];
        SpawnWeaponsList[i] = null;
        return tmp;
    }

    //restituisce l'array delle armi presenti
    public Weapon[] getSpawnWeaponsList() {
        return SpawnWeaponsList;
    }


    //danneggia lo spawnpoint in modalità dominazione
    public ArrayList<Player> getSPDamage(){
        return this.damageToSP;
    }

    public short[] getPointArray() {
        return pointArray;
    }
}
