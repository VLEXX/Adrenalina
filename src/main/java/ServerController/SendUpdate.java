package ServerController;

import Model.CurrentPlayerState;
import Model.DataPacket;
import Model.Player;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SendUpdate extends Thread {

    private DataPacket datapacket;

    public void run(){
        while(true) {
            for (int i = 0; i < 50; i++) {
                try {
                    datapacket = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE);
                    CurrentPlayerState c = new CurrentPlayerState();
                    c.setActiveplayer(Player.YELLOW);
                    datapacket.updatePlayerstate(Player.YELLOW, c);
                    FileOutputStream file = new FileOutputStream("save.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(datapacket);
                    out.close();
                    wait(1000);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
