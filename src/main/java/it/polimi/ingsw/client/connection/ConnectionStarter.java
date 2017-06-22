package it.polimi.ingsw.client.connection;

import it.polimi.ingsw.client.LoginController;
import it.polimi.ingsw.client.connection.middleware.ClientSender;
import it.polimi.ingsw.client.connection.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.connection.rmi.RMIClient;
import it.polimi.ingsw.client.connection.socket.SocketClient;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionStarter {
    private final static Logger LOGGER = Logger.getLogger(ConnectionStarter.class.getName());

    private String connectionType;

    private ClientSender clientSender;

    public ConnectionStarter(String connectionType) {
        this.connectionType = connectionType;
        clientSender = new ClientSenderHandler(true);
    }

    public void startConnection(String playerName, String psw) {
        if ("RMI".equals(connectionType)) {
            clientSender = new ClientSenderHandler(false);
            startRMIClient();
        } else {
            startSocketClient();
        }
        clientSender.login(playerName, psw);
    }

    private void startSocketClient() {
        try {
            SocketClient socketClient = SocketClient.getInstance();
            socketClient.startSocketClient();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot connect via socket", e);
        }
    }

    private void startRMIClient() {
        try {
            RMIClient rmiClient = RMIClient.getInstance();
            rmiClient.startRMIClient();
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot connect via RMI", e);
        }
    }
}