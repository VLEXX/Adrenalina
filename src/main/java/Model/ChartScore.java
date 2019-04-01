package Model;

public class ChartScore {
    private int[] Score;

    public ChartScore(){
        this.Score = new int[]{0,0,0,0,0};
    }

    public int[] getScore() {
        return this.Score;
    }

    public void setScore(Player p, int score) {
        if(p == Player.Yellow){
            this.Score[0]= this.Score[0]+score;
        }
        if(p == Player.Purple){
            this.Score[1]= this.Score[1]+score;
        }
        if(p == Player.Green){
            this.Score[2]= this.Score[2]+score;
        }
        if(p == Player.Blue){
            this.Score[3]= this.Score[3]+score;
        }
        if(p == Player.Black){
            this.Score[4]= this.Score[4]+score;
        }
    }
}
