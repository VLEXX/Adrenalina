package View;

import Model.ChartScore;

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
