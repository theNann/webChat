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
public class MyServerEndpoint {
    private Session session;
    private Logger sysLogger = Logger.getLogger("sysLog");

    @OnOpen
    public void open(Session session) {
        this.session = session;
        ClientManager.getInstance().addClient(this);
        sysLogger.info("*** WebSocket opened from sessionId " + session.getId());
    }

    @OnMessage
    public void inMessage(String message) {
        sysLogger.info("*** WebSocket Received from sessionId " + this.session.getId() + ": " + message);
        JSONObject jsonObject = new JSONObject(message);
        String type = (String)jsonObject.get("type");
        if(type.equals("chatMessage")) {
            ClientManager.getInstance().broadcast(message);
        }
    }

    @OnClose
    public void end() {
        ClientManager.getInstance().removeClient(this);
        sysLogger.info("*** WebSocket closed from sessionId " + this.session.getId());
    }

    public void send(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
            ClientManager.getInstance().removeClient(this);
        }
    }
}
