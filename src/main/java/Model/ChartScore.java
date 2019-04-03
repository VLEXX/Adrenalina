//Author: Federico Scatà
package Model;

//Classe in cui viene memorizzata la classifica corrente del gioco con i relativi punteggi.
//Ogni cella dell'array "Score" corrisponde ad un personaggio
//Score[0] => Yellow
//Score[1] => Purple
//Score[2] => Green
//Score[3] => Blue
//Score[4] => Black

public class ChartScore {
    private int[] Score;

    //Costruttore che inizializza l'array con tutti 0
    public ChartScore(){
        this.Score = new int[]{0,0,0,0,0};
    }

    //Restituisce l'array "Score"
    public int[] getScore() {
        return this.Score;
    }

    //A seconda del Player "p" in ingresso aggiunge punti "score" al giocatore, nella cella
    //  dell'array corrispondente
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
