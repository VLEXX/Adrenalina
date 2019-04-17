package ServerController;

import Model.CurrentDeckState;
import Model.InizializeAllPlay;

import java.io.*;

import java.net.Socket;

public class SocketClientHandler implements Runnable {
    private Socket socket;
    private InizializeAllPlay allPlay;

    public SocketClientHandler(Socket socket, InizializeAllPlay allPlay) {
        this.socket = socket;
        this.allPlay = allPlay;
    }

    public void run() {
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            PrintWriter outMessage = new PrintWriter(this.socket.getOutputStream());
            ServerManagerFunction serverManagerFunction = new ServerManagerFunction();

            serverManagerFunction.chooseCharacterManager(outMessage, objectInputStream, this.allPlay.getCurrentDeckState(), objectOutputStream);

            socket.close();

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
