//Author: Federico Scatà
package Model;

//Classe che gestisce la votazione per la scelta della mappa in cui giocare
public class VoteMap {
    private int[] VoteResult;   //Array che memorizza i voti ottenuti da ciascuna mappa (VoteResult[0] => Mappa1, VoteResult[1] => Mappa2, ...)
    private int FinalResult;    //Memorizza la mappa finale votata (da 1 a 4)

    //Costruttore che inizialzza l'array a 0 e la mappa finale votata a -1 (le mappe vanno da 1 a 4)
    public VoteMap(){
        VoteResult = new int[]{0,0,0,0};
        FinalResult = -1;
    }

    //Ritorna la mappa votata finala
    public int getFinalResult() {
        return FinalResult;
    }

    //Setta la mappa votata finale
    public void setFinalResult(int finalResult) {
        FinalResult = finalResult;
    }

    //Ritorna l'array dei voti
    public int[] getVoteResult() {
        return VoteResult;
    }

    //Setta il voto a seconda dell'indice(che corrisponde alla mappa votata, 0,1,2,3)
    public void setVoteResult(int index) {
        VoteResult[index] = VoteResult[index]+1;
    }
}
