package org.www;

import org.json.JSONObject;
import org.pyn.dao.OnlineUserDao;
import org.pyn.message.OnlineNumMessage;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by pyn on 2016/12/8.
 */
@ServerEndpoint(value = "/websocket")
public class MyServerEndpoint{
    private Session session;
    public static final Logger sysLogger = Logger.getLogger("sysLog");
    public static final Set<MyServerEndpoint> connections = new CopyOnWriteArraySet<>();

    @OnOpen
    public void open(Session session) {
        this.session = session;
        connections.add(this);
        sysLogger.info("*** WebSocket opened from sessionId " + session.getId());

        OnlineNumMessage onlineNumMessage = new OnlineNumMessage();
        onlineNumMessage.setOnlineNum(MyServerEndpoint.connections.size());
        MyServerEndpoint.broadcast(onlineNumMessage.encode());
    }

    @OnMessage
    public void inMessage(String message) {
        sysLogger.info("*** WebSocket Received from sessionId " + this.session.getId() + ": " + message);
        JSONObject jsonObject = new JSONObject(message);
        String type = (String)jsonObject.get("type");
        if(type.equals("chatMessage")) {
            broadcast(message);
        }
    }

    public static void broadcast(String message) {
        for(MyServerEndpoint client : connections) {
            try {
                client.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }

        }
    }
    @OnClose
    public void end() {
        connections.remove(this);
        sysLogger.info("*** WebSocket closed from sessionId " + this.session.getId());

        OnlineNumMessage onlineNumMessage = new OnlineNumMessage();
        onlineNumMessage.setOnlineNum(MyServerEndpoint.connections.size());
        MyServerEndpoint.broadcast(onlineNumMessage.encode());
    }
}
