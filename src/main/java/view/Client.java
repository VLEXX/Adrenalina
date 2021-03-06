/**
 * @author Federico Scatà
 */
package view;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Scanner;

/**
 * Class that manage clientWithSocket and ClientWithRMI
 */
public class Client {

    private ClientStrategy clientstrategy;

    public Client(){
        clientstrategy = null;
    }

    public static void main(String[] args){
        System.out.println("'Socket' or 'RMI'?");
        Scanner in = new Scanner(System.in);
        String s = null;
        String ip;
        Client client = new Client();
        while(s!="socket"||s!="Socket"||s!="SOCKET"||s!="rmi"||s!="RMI"){
            s = in.nextLine();
            if (s.equals("socket") || s.equals("Socket") || s.equals("SOCKET")) {
                System.out.println("Insert Server IP: \n");
                ip = in.nextLine();
                client.clientstrategy = new ClientWithSocket(ip, 5858);
                try {
                    client.clientstrategy.startClient();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
            }
            if (s.equals("rmi") || s.equals("RMI")) {
                System.out.println("Insert Server IP: \n");
                ip = in.nextLine();
                client.clientstrategy = new ClientWithRMI(ip);
                try {
                    client.clientstrategy.startClient();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("WRONG INPUT");
                System.out.println("Please choose between 'Socket' or 'RMI'...");
            }
        }
    }
}
