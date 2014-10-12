package yano_server;

/**
 * Created by aximcore on 2014.10.05..
 */

import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    Connection conn = null;
    Statement st;

    public Mysql() throws SQLException{
        this.kapcs();
    }

    public void kapcs(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/yano?" + "user=root&password=godmate");
            st = conn.createStatement();
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void Exec(String sql){
        try {
            st.executeUpdate(sql);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ExecChosen(String username,String chosen){
        try {
            st.executeUpdate("INSERT INTO users (username,chosen) values ('"+username+"', '"+chosen+"');");
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
