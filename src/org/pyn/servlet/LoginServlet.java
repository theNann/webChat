package org.pyn.servlet;

import org.pyn.bean.OnlineUser;
import org.pyn.bean.User;
import org.pyn.dao.OnlineUserDao;
import org.pyn.dao.UserDao;
import org.pyn.message.OnlineNumMessage;
import org.www.MyServerEndpoint;
import sun.awt.image.ImageWatched;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by pyn on 2016/12/5.
 */
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        LinkedList<User> result = userDao.selectByAccount(user);
        if(result.size() == 0) {
            out.print("no");
        }else {
            if(result.get(0).getPassword().equals(password)) {
                out.print("yes");
            } else {
                out.print("no");
            }
        }
    }
}
