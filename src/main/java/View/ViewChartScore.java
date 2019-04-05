package View;

import Model.Player;

public class ViewChartScore {
    private int[] score;

    public ViewChartScore(){
        this.score = new int[]{0,0,0,0,0};
    }

    public int[] getScore() {
        return score;
    }

    public void setScore(Player p, int score) {
        if(p == Player.YELLOW){
            this.score[0]= this.score[0]+score;
        }
        if(p == Player.PURPLE){
            this.score[1]= this.score[1]+score;
        }
        if(p == Player.GREEN){
            this.score[2]= this.score[2]+score;
        }
        if(p == Player.BLUE){
            this.score[3]= this.score[3]+score;
        }
        if(p == Player.BLACK){
            this.score[4]= this.score[4]+score;
        }
    }
}
