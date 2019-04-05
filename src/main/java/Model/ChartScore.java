//Author: Federico Scatà
package Model;

//Classe in cui viene memorizzata la classifica corrente del gioco con i relativi punteggi.
//Ogni cella dell'array "score" corrisponde ad un personaggio
//score[0] => Yellow
//score[1] => Purple
//score[2] => Green
//score[3] => Blue
//score[4] => Black

public class ChartScore {
    private int[] score;

    //Costruttore che inizializza l'array con tutti 0
    public ChartScore(){
        this.score = new int[]{0,0,0,0,0};
    }

    //Restituisce l'array "score"
    public int[] getScore() {
        return this.score;
    }

    //A seconda del Player "p" in ingresso aggiunge punti "score" al giocatore, nella cella
    //  dell'array corrispondente
    public void setScore(Player p, int score) {
        if(p == Player.Yellow){
            this.score[0]= this.score[0]+score;
        }
        if(p == Player.Purple){
            this.score[1]= this.score[1]+score;
        }
        if(p == Player.Green){
            this.score[2]= this.score[2]+score;
        }
        if(p == Player.Blue){
            this.score[3]= this.score[3]+score;
        }
        if(p == Player.Black){
            this.score[4]= this.score[4]+score;
        }
    }
}
