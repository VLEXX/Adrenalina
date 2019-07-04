/**
 * @author Federico Scatà
 */
package view;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Interface Design Pattern Strategy to manage ClientWithSocket and ClientWithRMI
 */
public interface ClientStrategy {
    void startClient() throws IOException, ClassNotFoundException, NotBoundException, RemoteException;
}
