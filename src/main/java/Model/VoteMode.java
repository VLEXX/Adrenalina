package Model;

public class VoteMode {
    private int[] VoteResultMode;
    private int FinalResultMode;

    public VoteMode(){
        VoteResultMode = new int[]{0,0,0,0};
        FinalResultMode=-1;
    }

    public int getFinalResult() {
        return FinalResultMode;
    }

    public void setFinalResult(int finalResult) {
        FinalResultMode = finalResult;
    }

    public int[] getVoteResult() {
        return VoteResultMode;
    }

    public void setVoteResult(int index) {
        VoteResultMode[index] = VoteResultMode[index]+1;
    }
}
