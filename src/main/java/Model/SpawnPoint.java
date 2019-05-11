//Author: Alex Saletti
package Model;

import Model.WeaponsCard.Weapon;

public class SpawnPoint {
    private Weapon[] SpawnWeaponsList;  //lista armi presenti allo spawn


    public SpawnPoint() {                //costruttore
        this.SpawnWeaponsList = new Weapon[]{null, null, null};
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
