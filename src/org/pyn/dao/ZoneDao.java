package org.pyn.dao;

import org.pyn.bean.Zone;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by pyn on 2016/12/15.
 */
public class ZoneDao {
    public void insert(Zone zone) {
        Connection conn = DBHelper.getConnection();
        PreparedStatement psta;
        String sql = "insert into zones(account,shuoshuo,date) values(?,?,?)";
        try {
            psta = conn.prepareStatement(sql);
            psta.setString(1,zone.getAccount());
            psta.setString(2,zone.getShuoshuo());
            psta.setString(3,zone.getDate());
            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Zone> select() {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from zones";
        Statement sta;
        ResultSet rs;
        LinkedList<Zone> res = new LinkedList<>();
        try {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            while(rs.next()) {
                Zone zone = new Zone();
                zone.setAccount(rs.getString("account"));
                zone.setShuoshuo(rs.getString("shuoshuo"));
                zone.setDate(rs.getString("date"));
                res.add(zone);
            }
            rs.close();
            sta.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
