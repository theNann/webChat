package org.www;

import org.pyn.message.OnlineNumMessage;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

/**
 * Created by pyn on 2016/12/14.
 */
public class ClientManager {
    private Set<MyServerEndpoint> connections = new CopyOnWriteArraySet<>();

    private static ClientManager instance = new ClientManager();
    private ClientManager() {

    }

    public static ClientManager getInstance() {
        return instance;
    }

    public void addClient(MyServerEndpoint c) {
        connections.add(c);

        OnlineNumMessage onlineNumMessage = new OnlineNumMessage();
        onlineNumMessage.setOnlineNum(connections.size());
        broadcast(onlineNumMessage.encode());
    }

    public void removeClient(MyServerEndpoint c) {
        connections.remove(c);

        OnlineNumMessage onlineNumMessage = new OnlineNumMessage();
        onlineNumMessage.setOnlineNum(connections.size());
        broadcast(onlineNumMessage.encode());
    }

    public void broadcast(String message) {
        for(MyServerEndpoint client : connections) {
            client.send(message);
        }
    }
}
