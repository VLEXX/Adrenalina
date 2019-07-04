/**
 * @author Federico Scatà
 */
package view;

import model.gamedata.ChartScore;

/**
 * Class that contains Chart Score of the game
 */
public class ViewChartScore {
    private ChartScore c;

    public ViewChartScore(){
        this.c = new ChartScore();
    }

    public void setChartScore(ChartScore c) {
        this.c = c;
    }

    public ChartScore getChartScore() {
        return c;
    }
}
