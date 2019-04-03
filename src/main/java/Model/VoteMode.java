//Author: Federico Scatà
package Model;

//Classe che gestisce la votazione per la scelta della modalità in cui giocare
public class VoteMode {
    private int[] VoteResultMode;   //Array che memorizza i voti ottenuti da ciascuna modalità (VoteResult[0] => Base, VoteResult[1] => Dominazione, ...)
    private int FinalResultMode;    //Memorizza la modalità finale votata (da 1 a 3)

    //Costruttore che inizialzza l'array a 0 e la modalità finale votata a -1 (le modalità vanno da 1 a 3)
    public VoteMode(){
        VoteResultMode = new int[]{0,0,0,0};
        FinalResultMode=-1;
    }

    //Ritorna la modalità finale votata
    public int getFinalResult() {
        return FinalResultMode;
    }

    //Setta la modalità finale votata
    public void setFinalResult(int finalResult) {
        FinalResultMode = finalResult;
    }

    //Ritorna l'array con i voti
    public int[] getVoteResult() {
        return VoteResultMode;
    }

    //Setta il voto per la modalità, a seconda dell'indice passato (che corrisponde alla modalità votata, 0,1,2)
    public void setVoteResult(int index) {
        VoteResultMode[index] = VoteResultMode[index]+1;
    }
}
