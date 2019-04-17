package ServerController;

import Model.CurrentDeckState;

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
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            PrintWriter outMessage = new PrintWriter(this.socket.getOutputStream());
            ServerManagerFunction serverManagerFunction = new ServerManagerFunction();
            CurrentDeckState currentDeckState = new CurrentDeckState();
            
            serverManagerFunction.chooseCharacterManager(outMessage, objectInputStream, currentDeckState, objectOutputStream);


            socket.close();

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
