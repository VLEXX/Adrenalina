//Author: Federico Scatà
package Model;

public class VoteMode {
    private int[] VoteResult;
    private int FinalResult;

    public VoteMode(){
        VoteResult = new int[]{0,0,0,0};
        FinalResult=-1;
    }

    public int getFinalResult() {
        return FinalResult;
    }

    public void setFinalResult(int finalResult) {
        FinalResult = finalResult;
    }

    public int[] getVoteResult() {
        return VoteResult;
    }

    public void setVoteResult(int index) {
        VoteResult[index] = VoteResult[index]+1;
    }
}
