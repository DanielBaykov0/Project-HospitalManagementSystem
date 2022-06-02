package baykov.com;

import java.sql.DriverManager;

public class Connection {
    public static java.sql.Connection getConn() {

        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "wolf", "hangmanzaq");
        } catch (Exception e) {
            e.printStackTrace();
            return getConn();
        }
    }
}
