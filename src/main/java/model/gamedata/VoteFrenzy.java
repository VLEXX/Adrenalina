package model.gamedata;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VoteFrenzy extends UnicastRemoteObject implements Serializable {

    private int yes;
    private int no;
    private int num;

    public VoteFrenzy() throws RemoteException{
        this.yes=0;
        this.no=0;
        this.num=0;
    }

    public synchronized void setNum(int num) {
        this.num = num;
    }

    public synchronized int getNum() {
        return num;
    }

    public synchronized void decreaseNum(){
        this.num--;
    }

    public synchronized void addYes() throws RemoteException{
        this.yes++;
    }

    public synchronized void addNo() throws RemoteException{
        this.no++;
    }

    public synchronized boolean getResult() throws RemoteException{
        if(yes>no){
            return true;
        }
        else{
            return false;
        }
    }

}
