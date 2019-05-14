package servercontroller;

import model.gamedata.InitializeAllPlay;
import model.playerdata.Player;
import model.datapacket.UpdatePacket;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class UpdateThread extends Thread implements ObserverUpdate {
    private InitializeAllPlay allPlay;
    private boolean endgame;
    private Player player;
    private ObjectOutputStream objectOutputStream;
    private boolean activeUpdate;

    public UpdateThread(InitializeAllPlay i, Player p, ObjectOutputStream o){
        this.allPlay=i;
        this.endgame=false;
        this.player=p;
        this.objectOutputStream=o;
        this.activeUpdate=false;
    }

    public void setEndgame(boolean endgame) {
        this.endgame = endgame;
    }

    public synchronized void updateClient() throws IOException {
        UpdatePacket updatePacket = new UpdatePacket(allPlay.getChartScore().getScore(), allPlay.getCurrentPlayerState().get(player), allPlay.getStateSelectedMap().getSelectedmap());
        objectOutputStream.writeObject(updatePacket);
    }
    public void start(){
        UpdatePacket updatePacket;
        while(true){
            if(endgame==true){
                break;
            }
            else{
                if(this.activeUpdate=true){
                    try {
                        updateClient();
                        activeUpdate=false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void update(boolean active) {
        this.activeUpdate=active;
    }

    public boolean isActiveUpdate() {
        return activeUpdate;
    }
}
