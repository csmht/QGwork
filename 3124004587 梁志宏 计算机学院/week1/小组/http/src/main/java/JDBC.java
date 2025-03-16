import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JDBC {

    //通过学号查找学生数据
    public static ResultSet find(String table, String id) throws ClassNotFoundException, SQLException {

        List<User> a = new ArrayList<>();
        String sql;
        ResultSet rs = null;
        try {
            Statement stmt;
            Connection conn = Pool.setPool();
            sql = "SELECT * FROM user WHERE user.id ='" + id + "'";

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rs;


        }

     public static ResultSet FindLess(String table, String id) throws ClassNotFoundException, SQLException {
        String sql;

        Connection conn = Pool.setPool();
        Statement stmt = conn.createStatement();
            sql = "SELECT * FROM less WHERE less."+  table +" ='" + id + "';";
            return stmt.executeQuery(sql);


     }


    //注册用户
    public static boolean insert(User a,String were) throws SQLException, ClassNotFoundException {
        Connection conn = Pool.setPool();
        Statement stmt = conn.createStatement();
            ResultSet stm =  JDBC.find("user", a.getStuid());

            while (stm.next()) {
                String id = stm.getString("id");
                if (Objects.equals(id, a.getStuid())) {
                    System.out.println("该学号已存在，输入回车继续.....");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    return false;
                }
            }

            String sql = "INSERT INTO user (mima, id) VALUES ('"  + a.getMima() + "', '" + a.getStuid() + "')";
            String sql1 = "INSERT INTO student (id) VALUES ('" + a.getStuid() + "')";
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql);
            return true;


        }


    //增删课程
    public static boolean doless(Less a, String op) throws SQLException {
        Pool pool = new Pool();
        Connection conn = pool.setPool(); Statement stmt = conn.createStatement();
            String sql;
            if (op.equals("add")) {
                sql = "INSERT INTO course (id, pd, xf) VALUES ('" + a.id + "', " + a.pd + ", " + a.pd + ")";
            } else if (op.equals("delete")) {
                sql = "DELETE FROM course WHERE id='" + a.id + "'";
            } else {
                return false;
            }
            int i = stmt.executeUpdate(sql);
            return true;


    }
}


