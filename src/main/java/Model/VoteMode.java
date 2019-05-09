/**
 * @author Federico Scatà
 */
package Model;

//Classe che gestisce la votazione per la scelta della modalità in cui giocare
public class VoteMode {
    private int[] voteresultmode;   //Array che memorizza i voti ottenuti da ciascuna modalità (VoteResult[0] => BASE, VoteResult[1] => Dominazione, ...)
    private int finalresultmode;    //Memorizza la modalità finale votata (da 1 a 3)

    //Costruttore che inizialzza l'array a 0 e la modalità finale votata a -1 (le modalità vanno da 1 a 3)
    public VoteMode(){
        voteresultmode = new int[]{0,0,0,0};
        finalresultmode = -1;
    }

    //Ritorna la modalità finale votata
    public int getFinalResult() {
        return finalresultmode;
    }

    //Setta la modalità finale votata
    public void setFinalResult(int finalResult) {
        finalresultmode = finalResult;
    }

    //Ritorna l'array con i voti
    public int[] getVoteResult() {
        return voteresultmode;
    }

    //Setta il voto per la modalità, a seconda dell'indice passato (che corrisponde alla modalità votata, 0,1,2)
    public void setVoteResult(int index) {
        voteresultmode[index] = voteresultmode[index]+1;
    }
}
