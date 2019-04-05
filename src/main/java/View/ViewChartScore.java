package View;

import Model.Player;

public class ViewChartScore {
    private int[] Score;

    public ViewChartScore(){
        this.Score = new int[]{0,0,0,0,0};
    }

    public int[] getScore() {
        return Score;
    }

    public void setScore(Player p, int score) {
        if(p == Player.YELLOW){
            this.Score[0]= this.Score[0]+score;
        }
        if(p == Player.PURPLE){
            this.Score[1]= this.Score[1]+score;
        }
        if(p == Player.GREEN){
            this.Score[2]= this.Score[2]+score;
        }
        if(p == Player.BLUE){
            this.Score[3]= this.Score[3]+score;
        }
        if(p == Player.BLACK){
            this.Score[4]= this.Score[4]+score;
        }
    }
}
