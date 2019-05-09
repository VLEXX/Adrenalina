/**
 * @author Federico Scatà
 */
package Model;

import java.io.Serializable;

/**
 * Classe in cui viene memorizzata la classifica corrente del gioco con i relativi punteggi.
 * //Ogni cella dell'array "score" corrisponde ad un personaggio
 * //score[0] => YELLOW
 * //score[1] => PURPLE
 * //score[2] => GREEN
 * //score[3] => BLUE
 * //score[4] => BLACK
 */
public class ChartScore implements Serializable {
    private int[] score;

    /**
     * Costruttore che inizializza l'array con tutti 0
     */
    public ChartScore(){
        this.score = new int[]{0,0,0,0,0};
    }

    /**
     * Restituisce l'array "score"
     *
     * @return array score della classe
     */
    public int[] getScore() {
        return this.score;
    }

    /**
     * A seconda del Player "p" in ingresso aggiunge punti
     * "score" al giocatore, nella cella dell'array corrispondente:
     *
     * @param p di tipo Player, colore del giocatore
     * @param score di tipo intero che viene sommato al punteggio attuale corrispondente al Player
     */
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
