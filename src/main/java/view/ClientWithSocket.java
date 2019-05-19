/**
 * @author Federico Scatà
 */
package view;

import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import servercontroller.UpdateThread;
import view.viewstates.*;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
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

                ViewActionState actionState = new ViewActionState();
                ViewEndturnState endTurnState = new ViewEndturnState();
                ViewMoveState moveState = new ViewMoveState();
                ViewWaitingState waitingState = new ViewWaitingState();
                ViewShootFirstState shootFirstState = new ViewShootFirstState();
                ViewShootSecondState shootSecondState = new ViewShootSecondState();
                ViewShootThirdState shootThirdState = new ViewShootThirdState();
                ViewPickupState pickUpState = new ViewPickupState();
                ViewPowerupState powerupState = new ViewPowerupState();
                ViewSpawnState spawnState = new ViewSpawnState();
                HashMap<StatesEnum, ViewState> stateHashMap = new HashMap<>();
                stateHashMap.put(StatesEnum.ACTION, actionState);
                stateHashMap.put(StatesEnum.END, endTurnState);
                stateHashMap.put(StatesEnum.MOVE, moveState);
                stateHashMap.put(StatesEnum.WAIT, waitingState);
                stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
                stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
                stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
                stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
                stateHashMap.put(StatesEnum.POWERUP, powerupState);
                stateHashMap.put(StatesEnum.SPAWN, spawnState);

                ViewDatabase viewDatabase = new ViewDatabase();
                clientManager.manageChoice(inMessage, stdin, objectOutputStream, objectInputStream, viewDatabase);
                clientManager.manageVote(outMessage, inMessage, stdin, objectOutputStream, objectInputStream);

                ViewStartGame startGame = new ViewStartGame(viewDatabase.getThisplayer(), objectInputStream, objectOutputStream, stdin, viewDatabase, stateHashMap);
                startGame.start();

            }
            catch (ConnectException e){
                System.out.println("\n" + "\u001B[31m" + "Because the Server is dark and full of connections." + "\u001B[0m");
            }
        }
}
