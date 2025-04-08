package com.csmht;

import java.sql.*;
import java.util.LinkedList;

public class Pool {
    public static Pool Pool = new Pool();
    private static final LinkedList<Connection> connQ = new LinkedList<>();
    private static final String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "0603";
    static int poolmin = 1;
    static int maxPool = 2;

    static {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 初始化连接池
        for (int i = 0; i < poolmin; i++) {

                Connection conn = DriverManager.getConnection(url, user, password);
                connQ.add(conn);
        }
        } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public static Connection getPool() throws SQLException {
        while (connQ.isEmpty()) {
            connQ.add(DriverManager.getConnection(url, user, password));
        }return connQ.removeFirst();
    }

    public static void returnConn(Connection conn) {
        if (conn != null && connQ.size() < maxPool) {
            try {
                if (!conn.isClosed()) {
                    connQ.add(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
