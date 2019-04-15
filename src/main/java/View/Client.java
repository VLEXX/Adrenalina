package View;

import Model.DataPacket;
import Model.Player;

import java.io.*;
import java.net.Socket;


public class Client {
        private String ip;
        private int port;

        public Client(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }
        public static void main(String[] args) throws IOException, ClassNotFoundException {
            Client client = new Client("127.0.0.1", 5858);
            client.startClient();
        }

        private void startClient() throws IOException, ClassNotFoundException {
            Socket socket = new Socket(ip, port);
            System.out.println("Connection established");

            InputStream inputStream = socket.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            DataPacket d = (DataPacket) objectInputStream.readObject();

            System.out.println(d.getPlayerstatemap().get(Player.YELLOW).getActiveplayer().toString());

            socket.close();
        }
}
