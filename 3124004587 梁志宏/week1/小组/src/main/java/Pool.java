import java.sql.*;
import java.util.LinkedList;

public class Pool {

    private static final LinkedList<Connection> connQ = new LinkedList<>();//连接池

    private static final String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "0603";
    private static final int poolmin = 1;
    private static final int poolmax = 10;

    static {
        // 初始化连接池
        for (int i = 0; i < poolmin; i++) {
            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                connQ.add(conn);
            } catch (Exception ignored) {

            }
        }
    }

    public static synchronized Connection setPool() {
        // 获取连接
        while (connQ.isEmpty()) {
            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                connQ.add(conn);
            } catch (Exception ignored) {

            }
        }
        return connQ.removeFirst();
    }

    public static void closePool() {
        while (!connQ.isEmpty()) {
            try {
                Connection conn = connQ.removeLast();
                conn.close();
            } catch (Exception ignored) {

            }
        }
    }
}