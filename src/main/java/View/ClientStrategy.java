package View;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface ClientStrategy {
    void startClient() throws IOException, ClassNotFoundException, NotBoundException, RemoteException;
}
