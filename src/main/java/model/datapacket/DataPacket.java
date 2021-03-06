/**
 * @author Federico Scatà
 */
package model.datapacket;

import model.map.Cell;
import model.map.Position;
import model.map.SpawnPoint;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
import model.weaponscard.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * it contains information that is necessary to do an action in various states
 */
public class DataPacket implements Serializable {
    private Cell cell;
    private Player player;
    private StatesEnum statesEnum;
    private Weapon weapon;
    private Weapon replaceWeapon;
    private ArrayList<Weapon> weaponsToBeRecharged;
    private ArrayList<PowerUp> paymentPowerUp;
    private ArrayList<Player> targetPlayersFirst;
    private ArrayList<Player> targetPlayersSecond;
    private ArrayList<Player> targetPlayersThird;
    private boolean firstAttack;
    private boolean secondAttack;
    private boolean thirdAttack;
    private HashMap<Player, Integer> marksToAdd;
    private Position position;
    private PowerUpId powerUpId;
    private Munitions powerUpColor;
    private Player targetPlayerPowerup;
    private boolean powerupAction;
    private Munitions munitions;
    private PowerUp powerUpToKeepSpawn;
    private PowerUp powerUpSpawn;
    private boolean weaponlistempty;
    private int token;
    private boolean frenzy;
    private SpawnPoint spawnPointToAttack;

    public DataPacket() {
        this.spawnPointToAttack=null;
        this.cell = null;
        this.player = null;
        this.statesEnum = null;
        this.weapon = null;
        this.replaceWeapon = null;
        this.weaponsToBeRecharged = new ArrayList<>();
        this.paymentPowerUp = new ArrayList<>();
        this.targetPlayersFirst = new ArrayList<>();
        this.targetPlayersSecond = new ArrayList<>();
        this.targetPlayersThird = new ArrayList<>();
        this.firstAttack=false;
        this.secondAttack = false;
        this.thirdAttack = false;
        this.marksToAdd= new HashMap<>();
        this.position=null;
        this.powerUpId=null;
        this.targetPlayerPowerup=null;
        this.powerupAction = false;
        this.munitions = null;
        this.powerUpToKeepSpawn = null;
        this.powerUpSpawn = null;
        this.weaponlistempty=false;
        this.token=0;
        this.powerUpColor=null;
        this.frenzy=false;
    }

    public void setSpawnPointToAttack(SpawnPoint spawnPointToAttack) {
        this.spawnPointToAttack = spawnPointToAttack;
    }

    public SpawnPoint getSpawnPointToAttack() {
        return spawnPointToAttack;
    }

    public boolean isFrenzy() {
        return frenzy;
    }

    public void setFrenzy(boolean frenzy) {
        this.frenzy = frenzy;
    }

    public Munitions getPowerUpColor() {
        return powerUpColor;
    }

    public void setPowerUpColor(Munitions powerUpColor) {
        this.powerUpColor = powerUpColor;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public boolean isWeaponlistempty() {
        return weaponlistempty;
    }

    public void setWeaponlistempty(boolean weaponlistempty) {
        this.weaponlistempty = weaponlistempty;
    }

    public PowerUp getPowerUpSpawn() {
        return powerUpSpawn;
    }

    public void setPowerUpSpawn(PowerUp powerUpSpawn) {
        this.powerUpSpawn = powerUpSpawn;
    }

    public void setPowerUpToKeepSpawn(PowerUp powerUp) {
        this.powerUpToKeepSpawn = powerUp;
    }

    public PowerUp getPowerUpToKeepSpawn() {
        return powerUpToKeepSpawn;
    }

    public Munitions getMunitions() {
        return munitions;
    }

    public void setMunitions(Munitions munitions) {
        this.munitions = munitions;
    }

    public boolean isPowerupAction() {
        return powerupAction;
    }

    public void setPowerupAction(boolean powerupAction) {
        this.powerupAction = powerupAction;
    }

    public Player getTargetPlayerPowerup() {
        return targetPlayerPowerup;
    }

    public void setTargetPlayerPowerup(Player targetPlayerPowerup) {
        this.targetPlayerPowerup = targetPlayerPowerup;
    }

    public PowerUpId getPowerUpId() {
        return powerUpId;
    }

    public void setPowerUpId(PowerUpId powerUpId) {
        this.powerUpId = powerUpId;
    }

    public HashMap<Player, Integer> getMarksToAdd() {
        return marksToAdd;
    }



    /**
     * @return cell
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * @param cell
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return
     */
    public StatesEnum getStatesEnum() {
        return statesEnum;
    }

    /**
     * @param statesEnum
     */
    public void setStatesEnum(StatesEnum statesEnum) {
        this.statesEnum = statesEnum;
    }

    /**
     * @return weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * @param weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * @return replaceWeapon
     */
    public Weapon getReplaceWeapon() {
        return replaceWeapon;
    }

    /**
     * @param replaceWeapon
     */
    public void setReplaceWeapon(Weapon replaceWeapon) {
        this.replaceWeapon = replaceWeapon;
    }

    /**
     * @return weaponsToBeRecharged
     */
    public ArrayList<Weapon> getWeaponsToBeRecharged() {
        return weaponsToBeRecharged;
    }

    /**
     * @return paymentPowerUp
     */
    public ArrayList<PowerUp> getPaymentPowerUp() {
        return paymentPowerUp;
    }

    /**
     * @param paymentPowerUp
     */
    public void setPaymentPowerUp(ArrayList<PowerUp> paymentPowerUp) {
        this.paymentPowerUp = paymentPowerUp;
    }

    public ArrayList<Player> getTargetPlayersFirst() {
        return targetPlayersFirst;
    }

    public void setSecondAttack(boolean secondAttack) {
        this.secondAttack = secondAttack;
    }

    public void setThirdAttack(boolean thirdAttack) {
        this.thirdAttack = thirdAttack;
    }

    public boolean isSecondAttack() {
        return secondAttack;
    }

    public boolean isThirdAttack() {
        return thirdAttack;
    }

    public ArrayList<Player> getTargetPlayersSecond() {
        return targetPlayersSecond;
    }

    public ArrayList<Player> getTargetPlayersThird() {
        return targetPlayersThird;
    }

    public void setFirstAttack(boolean firstAttack) {
        this.firstAttack = firstAttack;
    }

    public boolean isFirstAttack() {
        return firstAttack;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
