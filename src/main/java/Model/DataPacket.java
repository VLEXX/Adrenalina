/**
 * @author Federico Scatà
 */
package Model;

import Model.WeaponsCard.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPacket {
    private Cell cell;
    private Player player;
    private StatesEnum statesEnum;
    private Weapon weapon;
    private Weapon replaceWeapon;
    private ArrayList<PowerUp> paymentPowerUp;

    public DataPacket(){
        this.cell=null;
        this.player=null;
        this.statesEnum=null;
        this.weapon=null;
        this.replaceWeapon=null;
        this.paymentPowerUp = new ArrayList<>();
    }

    /**
     * @return
     */
    public Cell getCell() {
        return cell;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * @param cell
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public StatesEnum getStatesEnum() {
        return statesEnum;
    }

    public void setStatesEnum(StatesEnum statesEnum) {
        this.statesEnum = statesEnum;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setReplaceWeapon(Weapon replaceWeapon) {
        this.replaceWeapon = replaceWeapon;
    }

    public Weapon getReplaceWeapon() {
        return replaceWeapon;
    }

    public void setPaymentPowerUp(ArrayList<PowerUp> paymentPowerUp) {
        this.paymentPowerUp = paymentPowerUp;
    }

    public ArrayList<PowerUp> getPaymentPowerUp() {
        return paymentPowerUp;
    }
}
