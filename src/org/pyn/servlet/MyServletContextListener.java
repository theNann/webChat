package org.pyn.servlet;

import org.pyn.dao.OnlineUserDao;
import org.pyn.message.Message;
import org.pyn.message.OnlineNumMessage;
import org.www.MyServerEndpoint;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * Created by pyn on 2016/12/14.
 */
public class MyServletContextListener implements ServletContextAttributeListener{
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        OnlineUserDao onlineUserDao = new OnlineUserDao();
        OnlineNumMessage onlineNumMessage = new OnlineNumMessage();
        onlineNumMessage.setType("onlineNumMessage");
        onlineNumMessage.setOnlineNum(onlineUserDao.selectUsers());
        MyServerEndpoint myServerEndpoint = new MyServerEndpoint();
        myServerEndpoint.broadcast(onlineNumMessage.encode());
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        OnlineUserDao onlineUserDao = new OnlineUserDao();
        OnlineNumMessage onlineNumMessage = new OnlineNumMessage();
        onlineNumMessage.setType("onlineNumMessage");
        onlineNumMessage.setOnlineNum(onlineUserDao.selectUsers());
        MyServerEndpoint myServerEndpoint = new MyServerEndpoint();
        myServerEndpoint.broadcast(onlineNumMessage.encode());
    }
}
