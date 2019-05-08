package ServerController;

import Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StartGame extends Thread {
    private InitializeAllPlay allPlay;
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public StartGame(InitializeAllPlay i, Player p, ObjectInputStream oi, ObjectOutputStream oo){
        this.allPlay=i;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
    }

    public synchronized void run(){
        while(true){
            try {
                DataPacket dataPacket = (DataPacket) objectInputStream.readObject();
                Message message = allPlay.getPlayerState(player).doAction(dataPacket,allPlay);
                if(!message.getMessage().equals("ok")){
                    objectOutputStream.writeObject(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
