/**
 * @author Federico Scatà
 */
package Model;

import Model.WeaponsCard.Weapon;

import java.util.ArrayList;

public class DataPacket {
    private Cell cell;
    private Player player;
    private StatesEnum statesEnum;
    private Weapon weapon;
    private Weapon replaceWeapon;
    private ArrayList<PowerUp> paymentPowerUp;
    private ArrayList<Player> targetPlayers;
    private boolean secondAttack;
    private boolean thirdAttack;
    private Action action;

    public DataPacket() {
        this.cell = null;
        this.player = null;
        this.statesEnum = null;
        this.weapon = null;
        this.replaceWeapon = null;
        this.paymentPowerUp = new ArrayList<>();
        this.targetPlayers = new ArrayList<>();
        this.secondAttack = false;
        this.thirdAttack = false;
        this.action=null;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
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

    public ArrayList<Player> getTargetPlayers() {
        return targetPlayers;
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
}
