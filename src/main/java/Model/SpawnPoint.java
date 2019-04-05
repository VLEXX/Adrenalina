//Author: Alex Saletti
package Model;

public class SpawnPoint {
    private Weapons[] SpawnWeaponsList;  //lista armi presenti allo spawn


    public SpawnPoint(){                //costruttore
        this.SpawnWeaponsList = new Weapons[]{null,null,null};
    }

    //inserice l'arma nella posizione indicata dall'indice
    public void addWeapon(Weapons w,int i){
        SpawnWeaponsList[i]=w;
    }

    //restituisce l'arma nella posizione i settando a null la cella occupata dall'arma
    public Weapons removeWeapon(int i){
        Weapons tmp = SpawnWeaponsList[i];
        SpawnWeaponsList[i]=null;
        return tmp;
    }

    //restituisce l'array delle armi presenti
    public Weapons[] getSpawnWeaponsList() {
        return SpawnWeaponsList;
    }
}
