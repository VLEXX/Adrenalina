//Author: Federico Scatà
package Model;

import java.util.ArrayList;
import java.util.Stack;

//Classe che memorizza lo stato corrente dei mazzi delle Armi, Munizioni e Potenziamenti
public class CurrentDeckState {
    private Stack<Ammo> ammodeck;           //Mazzo delle munizioni
    private Stack<Weapons> weaponsdeck;     //Mazzo delle armi
    private Stack<PowerUp> powerupdeck;     //Mazzo dei potenziamenti
    private ArrayList<Player> players;      //Mazzo per gestire la scelta personaggi

    //costruttore
    public CurrentDeckState(){
        this.ammodeck=new Stack<>();
        for(int i=0; i<3; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0,1,2});
            this.ammodeck.push(a);
        }
        for(int i=0; i<3; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{2,1,0});
            this.ammodeck.push(a);
        }
        for(int i=0; i<3; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{1,0,2});
            this.ammodeck.push(a);
        }
        for(int i=0; i<3; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{1,2,0});
            this.ammodeck.push(a);
        }
        for(int i=0; i<3; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0,2,1});
            this.ammodeck.push(a);
        }
        for(int i=0; i<3; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{2,0,1});
            this.ammodeck.push(a);
        }
        for(int i=0; i<2; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0,2,0});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for(int i=0; i<2; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{2,0,0});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for(int i=0; i<2; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0,0,2});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for(int i=0; i<2; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0,2,0});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for(int i=0; i<4; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{1,1,0});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for(int i=0; i<4; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0,1,1});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for(int i=0; i<4; i++){
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{1,0,1});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        this.weaponsdeck=new Stack<>();
        for(Weapons weapons: Weapons.values()){
            this.weaponsdeck.push(weapons);
        }
        this.powerupdeck = new Stack<>();
        for(int i=0; i<2; i++) {
            for (PowerUp powerup : PowerUp.values()) {
                this.powerupdeck.push(powerup);
            }
        }

        this.players = new ArrayList<>();
        players.add(Player.BLUE);
        players.add(Player.BLACK);
        players.add(Player.GREEN);
        players.add(Player.PURPLE);
        players.add(Player.YELLOW);
    }

    //ritorna il mazzo delle munizioni
    public Stack<Ammo> getAmmodeck() {
        return this.ammodeck;
    }

    //ritorna il mazzo delle armi
    public Stack<Weapons> getWeaponsdeck() {
        return this.weaponsdeck;
    }

    //ritorna il mazzo dei potenziamenti
    public Stack<PowerUp> getPowerupdeck() {
        return this.powerupdeck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
