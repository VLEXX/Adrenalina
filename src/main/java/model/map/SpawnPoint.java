//Author: Alex Saletti
package model.map;

import model.munitions.Munitions;
import model.weaponscard.Weapon;

import java.io.Serializable;

public class SpawnPoint implements Serializable {
    private Weapon[] SpawnWeaponsList;  //lista armi presenti allo spawn
    private Munitions spawnColor;




    public SpawnPoint(Munitions m) {                //costruttore
        this.SpawnWeaponsList = new Weapon[]{null, null, null};
        this.spawnColor = m;
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
}
