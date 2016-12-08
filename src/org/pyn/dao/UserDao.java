package org.pyn.dao;

import org.pyn.bean.User;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by pyn on 2016/12/5.
 */
public class UserDao {
    public void insert(User user) {
        Connection conn;
        PreparedStatement psta;
        String sql;

        conn = DBHelper.getConnection();

        String account = user.getAccount();
        String password = user.getPassword();
        sql = "insert into users values(?,?)";
        try {
            psta = conn.prepareStatement(sql);
            psta.setString(1,account);
            psta.setString(2,password);
            psta.executeUpdate();
            psta.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<User> select() {
        LinkedList<User> result = new LinkedList<>();
        Connection conn;
        String sql;
        ResultSet rs;
        Statement sta;
        conn = DBHelper.getConnection();
        try {
            sta = conn.createStatement();
            sql = "select * from users";
            rs = sta.executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                user.setAccount(rs.getString("account"));
                user.setPassword(rs.getString("password"));
                result.add(user);
            }
            sta.close();
            conn.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public LinkedList<User> selectByAccount(User user) {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from users where account = ?";
        PreparedStatement psta;
        ResultSet rs;
        LinkedList<User> result = new LinkedList<>();
        try {
            psta = conn.prepareStatement(sql);
            psta.setString(1,user.getAccount());
            rs = psta.executeQuery();
            while(rs.next()) {
                User tmp = new User();
                tmp.setAccount(rs.getString("account"));
                tmp.setPassword(rs.getString("password"));
                result.add(tmp);
            }
            psta.close();
            conn.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
