package org.pyn.servlet;

import org.json.JSONObject;
import org.pyn.bean.User;
import org.pyn.dao.UserDao;

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
public class RegisterServlet extends HttpServlet {
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
        UserDao userDao = new UserDao();
        LinkedList<User> res = userDao.select();
        boolean ok = true;
        for(int i = 0; i < res.size(); i++) {
            if(res.get(i).getAccount().equals(account)) {
                ok = false;
                break;
            }
        }

        if(ok) {
            out.print("yes");
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            userDao.insert(user);
        } else {
            out.print("no");
        }
        out.close();
    }
}
