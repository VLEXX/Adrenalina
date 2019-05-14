/**
 * @author Federico Scatà
 */
package model.powerup;

import model.munitions.Munitions;

import java.io.Serializable;

/**
 * Power-Up class with "id" as Power-Up name
 * and "color" as Power-Up type/color
 */
public class PowerUp implements Serializable {
    private final Munitions color;
    private final PowerUpId id;

    /**
     * Constructor
     *
     * @param i
     * @param m
     */
    public PowerUp(PowerUpId i, Munitions m) {
        this.color = m;
        this.id = i;
    }

    /**
     * @return id
     */
    public PowerUpId getId() {
        return id;
    }

    /**
     * @return color
     */
    public Munitions getColor() {
        return color;
    }

    public int munitionsChecker(Munitions m) {
        if (this.color == m)
            return 1;
        else
            return 0;
    }
}