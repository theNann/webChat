package org.pyn.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.pyn.bean.Zone;
import org.pyn.dao.ZoneDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by pyn on 2016/12/16.
 */
public class ZoneServlet extends HttpServlet{
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
        String currentPage = req.getParameter("page");
        Integer p = new Integer(currentPage);
        System.out.println("currentPage: " + p);
//        req.setAttribute("page", p);
        ZoneDao zoneDao = new ZoneDao();
        LinkedList<Zone> res = zoneDao.select();
        int len = res.size();
        int pages;
        if(len % 3 == 0) {
            pages = len/3;
        } else {
            pages = len/3 + 1;
        }
        JSONArray json = new JSONArray();
        if(p > pages) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "no");
            json.put(jsonObject);
        } else {
            int index = len - (p-1)*3 -1;
            int last = len - (p-1)*3 - 3;
            if(last < 0) {
                last = 0;
            }
            for (int i = index; i >= last; i--) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "yes");
                jsonObject.put("account", res.get(i).getAccount());
                jsonObject.put("shuoshuo", res.get(i).getShuoshuo());
                jsonObject.put("date", res.get(i).getDate());
                json.put(jsonObject);
            }
        }
        out.print(json);
    }
}
