package Model;

public class ChartScore {
    private Player[] Chart;
    private int[] Score;

    public ChartScore(){
        this.Chart = new Player[]{null, null, null, null, null};
        this.Score = new int[]{0,0,0,0,0};
    }

    public int[] getScore() {
        return this.Score;
    }

    public void setScore(int[] score) {
        this.Score = score;
    }

    public Player[] getChart() {
        return this.Chart;
    }

    public void setChart(Player[] chart) {
        this.Chart = chart;
    }
}
