package org.pyn.dao;

import org.pyn.bean.OnlineUser;

import java.sql.*;

/**
 * Created by pyn on 2016/12/14.
 */
public class OnlineUserDao {
    public void insert(OnlineUser onlineUser) {
        Connection conn;
        PreparedStatement psta;
        String sql;

        conn = DBHelper.getConnection();

        String account = onlineUser.getAccount();
        sql = "insert into onlineusers values(?)";
        try {
            psta = conn.prepareStatement(sql);
            psta.setString(1,account);
            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int selectUsers() {
        int res = 0;
        Connection conn;
        String sql;
        ResultSet rs;
        Statement st;
        conn = DBHelper.getConnection();
        try {
            sql = "select * from onlineusers";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                res += 1;
            }
            st.close();
            conn.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
