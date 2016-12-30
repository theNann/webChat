package org.pyn.servlet;

import org.pyn.bean.Zone;
import org.pyn.dao.ZoneDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * Created by pyn on 2016/12/15.
 */
public class ShuoshuoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        Zone zone = new Zone();
        zone.setAccount(req.getParameter("account"));
        zone.setShuoshuo(req.getParameter("shuoshuo"));
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        zone.setDate(df.format(d));
//        System.out.println("date: " + df.format(d));
//        System.out.println("account, shuoshuo: " + req.getParameter("account") + " " + req.getParameter("shuoshuo"));
        ZoneDao zoneDao = new ZoneDao();
        zoneDao.insert(zone);
        out.print("yes");
    }
}
