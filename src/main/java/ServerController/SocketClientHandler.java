package ServerController;

import Model.CurrentPlayerState;
import Model.DataPacket;
import Model.Player;

import java.io.*;

import java.net.Socket;

public class SocketClientHandler implements Runnable {
    private Socket socket;

    public SocketClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            DataPacket d = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE);
            CurrentPlayerState c = new CurrentPlayerState();
            c.setActiveplayer(Player.YELLOW);
            d.updatePlayerstate(Player.YELLOW, c);
            objectOutputStream.writeObject(d);

            System.out.println("inviato");

            socket.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
