/**
 * @author Federico Scatà
 */
package view;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;


public class ClientWithSocket implements ClientStrategy{
        private String ip;
        private int port;

        public ClientWithSocket(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        public void startClient() throws IOException, ClassNotFoundException {
            try {
                Socket socket = new Socket(ip, port);
                System.out.println("Connection established\n\n");

                ClientManager clientManager = new ClientManager();

                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                Scanner stdin = new Scanner(System.in);
                Scanner inMessage = new Scanner(socket.getInputStream());
                PrintWriter outMessage = new PrintWriter(socket.getOutputStream());

                clientManager.manageChoice(inMessage, stdin, objectOutputStream, objectInputStream);
                clientManager.manageVote(outMessage, inMessage, stdin, objectOutputStream, objectInputStream);

                InputMessage inputMessage = new InputMessage(inMessage);
                InputStdin inputStdin = new InputStdin(stdin);

            }
            catch (ConnectException e){
                System.out.println("\n" + "\u001B[31m" + "Because the Server is dark and full of connections." + "\u001B[0m");
            }
        }
}
