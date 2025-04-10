package com.csmht.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Pool {
    public static Pool Pool = new Pool();
    private static final LinkedList<Connection> waitConn = new LinkedList<>();
    private static final LinkedList<Connection> useConn = new LinkedList<>();
    private static final String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "0603";
    static int minPool = 1;
    static int maxPool = 2;
    static long maxTime = 3*1000;
    static connTime connTime = new connTime();

    static {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0 ;i < minPool ; i++){
                Connection conn = DriverManager.getConnection(url, user, password);
                waitConn.add(conn);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        Timer timer = new Timer();
        timer.schedule(new connInspect(), 0, 1000);
    }

    public synchronized Connection getPool() throws SQLException, InterruptedException {
        while(waitConn.size()+useConn.size()<maxPool){
            waitConn.add(DriverManager.getConnection(url, user, password));
        }

        if(waitConn.isEmpty()){
            wait();
            return getPool();
        }
        Connection conn = waitConn.removeFirst();
        useConn.add(conn);
        connTime.In(conn);
        return conn;

    }

    public synchronized void returnConn(Connection conn) {

        try {
            if (conn != null) {
                // 回滚未提交的事务
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                }

                conn.setAutoCommit(true);

                conn.createStatement().execute("SELECT * FROM empty;");

                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        useConn.remove(conn);
        try {
            if(conn!=null && waitConn.size()<=maxPool){

                    if(!conn.isClosed()){
                        waitConn.add(conn);
                        notify();
                    }

            }else {
                if(conn != null){
                    conn.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connTime.delate(conn);

    }


    public static void closePool() {
        while (!waitConn.isEmpty()) {
            try {
                Connection conn = waitConn.removeLast();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class connInspect extends TimerTask {

        @Override
        public void run() {

            try {
                for (Connection conn : com.csmht.dao.connTime.map.keySet()){
                    long time = System.currentTimeMillis() - com.csmht.dao.connTime.map.get(conn);
                    if(time>= maxTime){

                            Pool.returnConn(conn);


                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

}
