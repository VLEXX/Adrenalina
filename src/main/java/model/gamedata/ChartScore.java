/**
 * @author Federico Scatà
 */
package model.gamedata;

import model.playerdata.Player;

import java.io.Serializable;

/**
 * Class with current game chart. Every array cell corresponds to every Player
 * //score[0] => YELLOW
 * //score[1] => PURPLE
 * //score[2] => GREEN
 * //score[3] => BLUE
 * //score[4] => BLACK
 */
public class ChartScore implements Serializable {
    private int[] score;

    /**
     * Constructor
     */
    public ChartScore() {
        this.score = new int[]{0, 0, 0, 0, 0};
    }

    /**
     * @return array score
     */
    public int[] getScore() {
        return this.score;
    }

    /**
     * @param p     is a Player
     * @param score int summed to score Player
     */
    public void setScore(Player p, int score) {
        if (p == Player.YELLOW) {
            this.score[0] = this.score[0] + score;
        }
        if (p == Player.PURPLE) {
            this.score[1] = this.score[1] + score;
        }
        if (p == Player.GREEN) {
            this.score[2] = this.score[2] + score;
        }
        if (p == Player.BLUE) {
            this.score[3] = this.score[3] + score;
        }
        if (p == Player.BLACK) {
            this.score[4] = this.score[4] + score;
        }
    }
}
