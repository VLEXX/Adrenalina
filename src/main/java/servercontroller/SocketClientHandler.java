/**
 * @author Federico Scatà
 */
package servercontroller;

import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageString;
import model.playerdata.Player;

import java.io.*;

import java.net.Socket;
import java.util.Scanner;

public class SocketClientHandler implements Runnable {
    private Socket socket;
    private InitializeAllPlay allPlay;
    private Player player;

    public SocketClientHandler(Socket socket, InitializeAllPlay allPlay) {
        this.socket = socket;
        this.allPlay = allPlay;
        this.player=null;
    }

    public void run() {
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            PrintWriter outMessage = new PrintWriter(this.socket.getOutputStream());
            Scanner inMessage = new Scanner(socket.getInputStream());
            ServerManagerFunction serverManagerFunction = new ServerManagerFunction();
            allPlay.addPlayerCounter();

            player=serverManagerFunction.chooseCharacterManager(outMessage, objectInputStream, this.allPlay, objectOutputStream);
            while(true){
                if(allPlay.getPlayercountertemp()==0){
                    boolean ok = true;
                    objectOutputStream.writeObject(ok);
                    break;
                }
            }
            allPlay.getVoteMap().addPlayerCounter();
            serverManagerFunction.manageVoteMap(inMessage, allPlay, outMessage, objectInputStream, objectOutputStream);
            while(true){
                if(allPlay.getStateSelectedMap().getSelectedmap()!=null){
                    boolean ok = true;
                    objectOutputStream.writeObject(ok);
                    break;
                }
            }
            allPlay.resetPlayerCounterTemp();
            MessageString message = new MessageString("Map Selected: " + allPlay.getStateSelectedMap().getSelectedmap().getMapname() + "\n\n");
            objectOutputStream.writeObject(message);


            UpdateThread updateThread = new UpdateThread(allPlay, player, objectOutputStream);
            updateThread.updateClient();

            StartGame startGame = new StartGame(allPlay, player, objectInputStream, objectOutputStream);
            startGame.start();

            updateThread.start();

            socket.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
