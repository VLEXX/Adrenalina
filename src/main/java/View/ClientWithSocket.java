package View;

import Model.DataPacket;
import Model.Player;

import java.io.*;
import java.net.Socket;


public class ClientWithSocket implements ClientStrategy{
        private String ip;
        private int port;

        public ClientWithSocket(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        public void startClient() throws IOException, ClassNotFoundException {

            Socket socket = new Socket(ip, port);
            System.out.println("Connection established");

            InputStream inputStream = socket.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            DataPacket d = (DataPacket) objectInputStream.readObject();

            System.out.println(d.getPlayerstatemap().get(Player.YELLOW).getActiveplayer().toString());

            socket.close();
        }
}
