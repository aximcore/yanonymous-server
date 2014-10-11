package yano_server;

/**
 * Created by aximcore on 2014.10.05..
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    Connection conn = null;

    public Mysql() throws SQLException{
        this.kapcs();
    }

    public void kapcs() throws SQLException{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/yano?" + "user=root&password=godmate");
    }
}
