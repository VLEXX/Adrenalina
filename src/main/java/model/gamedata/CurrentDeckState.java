/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.munitions.Ammo;
import model.munitions.Munitions;
import model.powerups.*;
import model.playerdata.Player;
import model.weaponscard.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Class with weapons deck, ammo deck, powerup deck and available player deck
 */

public class CurrentDeckState implements Serializable {
    private Stack<Ammo> ammodeck;           //Mazzo delle munizioni
    private Stack<Weapon> weaponsdeck;     //Mazzo delle armi
    private Stack<PowerUp> powerupdeck;     //Mazzo dei potenziamenti
    private ArrayList<Player> players;      //Mazzo per gestire la scelta personaggi

    /**
     * Constructor that instantiates every deck and shuffle them
     */
    public CurrentDeckState() {
        this.ammodeck = new Stack<>();
        for (int i = 0; i < 3; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0, 1, 2});
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 3; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{2, 1, 0});
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 3; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{1, 0, 2});
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 3; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{1, 2, 0});
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 3; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0, 2, 1});
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 3; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{2, 0, 1});
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 2; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0, 2, 0});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 2; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{2, 0, 0});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 2; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0, 0, 2});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 2; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0, 2, 0});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 4; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{1, 1, 0});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 4; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{0, 1, 1});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        for (int i = 0; i < 4; i++) {
            Ammo a = new Ammo();
            a.setAmmoList(new int[]{1, 0, 1});
            a.setPossiblePowerUp(true);
            this.ammodeck.push(a);
        }
        this.weaponsdeck = new Stack<>();
        Cyberblade cyberblade = new Cyberblade();
        this.weaponsdeck.push(cyberblade);
        Electroscythe electroscythe = new Electroscythe();
        this.weaponsdeck.push(electroscythe);
        FlameThrower flameThrower = new FlameThrower();
        this.weaponsdeck.push(flameThrower);
        Furnace furnace = new Furnace();
        this.weaponsdeck.push(furnace);
        GrenadeLauncher grenadeLauncher = new GrenadeLauncher();
        this.weaponsdeck.push(grenadeLauncher);
        HeatSeeker heatSeeker = new HeatSeeker();
        this.weaponsdeck.push(heatSeeker);
        Hellion hellion = new Hellion();
        this.weaponsdeck.push(hellion);
        LockRifle lockRifle = new LockRifle();
        this.weaponsdeck.push(lockRifle);
        MachineGun machineGun = new MachineGun();
        this.weaponsdeck.push(machineGun);
        PlasmaGun plasmaGun = new PlasmaGun();
        this.weaponsdeck.push(plasmaGun);
        PowerGlove powerGlove = new PowerGlove();
        this.weaponsdeck.push(powerGlove);
        RailGun railGun = new RailGun();
        this.weaponsdeck.push(railGun);
        RocketLauncher rocketLauncher = new RocketLauncher();
        this.weaponsdeck.push(rocketLauncher);
        ShockWave shockWave = new ShockWave();
        this.weaponsdeck.push(shockWave);
        ShotGun shotGun = new ShotGun();
        this.weaponsdeck.push(shotGun);
        SledgeHammer sledgeHammer = new SledgeHammer();
        this.weaponsdeck.push(sledgeHammer);
        Thor thor = new Thor();
        this.weaponsdeck.push(thor);
        TractorBeam tractorBeam = new TractorBeam();
        this.weaponsdeck.push(tractorBeam);
        VortexCannon vortexCannon = new VortexCannon();
        this.weaponsdeck.push(vortexCannon);
        Whisper whisper = new Whisper();
        this.weaponsdeck.push(whisper);
        ZX2 zx2 = new ZX2();
        this.weaponsdeck.push(zx2);

        this.powerupdeck = new Stack<>();
        for (Munitions munitions : Munitions.values()) {
            this.powerupdeck.push(new Newton(munitions));
        }
        for (Munitions munitions : Munitions.values()) {
            this.powerupdeck.push(new TagbackGrenade(munitions));
        }
        for (Munitions munitions : Munitions.values()) {
            this.powerupdeck.push(new TargetingScope(munitions));
        }
        for (Munitions munitions : Munitions.values()) {
            this.powerupdeck.push(new Teleporter(munitions));
        }

        this.players = new ArrayList<>();
        players.add(Player.BLUE);
        players.add(Player.BLACK);
        players.add(Player.GREEN);
        players.add(Player.PURPLE);
        players.add(Player.YELLOW);

        Collections.shuffle(this.ammodeck);
        Collections.shuffle(this.weaponsdeck);
        Collections.shuffle(this.powerupdeck);
    }

    /**
     * @return ammo deck
     */
    public Stack<Ammo> getAmmodeck() {
        return this.ammodeck;
    }

    /**
     * @return weapons deck
     */
    public Stack<Weapon> getWeaponsdeck() {
        return this.weaponsdeck;
    }

    /**
     * @return powerup deck
     */
    public Stack<PowerUp> getPowerupdeck() {
        return this.powerupdeck;
    }

    /**
     * @return ArrayList with available Player
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
