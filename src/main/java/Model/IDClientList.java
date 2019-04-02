//Author: Federico Scatà
package Model;

public class IDClientList {
    private int[] ClientList;

    public IDClientList(){
        this.ClientList = new int[]{0,0,0,0,0};
    }

    public int[] getClientList() {
        return this.ClientList;
    }

    public void setClientList(int index, int id) {
        this.ClientList[index] = id;
    }
}
