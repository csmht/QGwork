import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuanLi {
    public static boolean Guanli() throws SQLException, ClassNotFoundException {
        Prin.GuanCread();
        Scanner scanner = new Scanner(System.in);
        int n;
        try {
            n = scanner.nextInt();
            scanner.nextLine(); // 消耗掉 nextInt() 后的换行符
        } catch (Exception e) {
            System.out.println("输入错误，请重新输入");
            scanner.nextLine();
            return false;
        }
        if (n == 1) {
            try {
                Prin.GuanSeeAll("学生");
            } catch (ClassNotFoundException | SQLException ignored) {

            }
        } else if (n == 2) {                        //修改学生手机号
            System.out.print("\033[H\033[2J");
            System.out.println("请输入学号：");
            String id = scanner.nextLine();
            System.out.println("请输入新手机号：");
            String num = scanner.nextLine();
            try (Connection conn = Pool.setPool(); Statement stmt = conn.createStatement()) {

                String sql = "UPDATE student SET num='" + num + "' WHERE id='" + id + "'";
                stmt.executeUpdate(sql);
                System.out.println("手机号修改成功");
            } catch (SQLException ignored) {

            }

        } else if (n == 3) {
            try {
                Prin.GuanSeeAll("课程");
            } catch (ClassNotFoundException | SQLException ignored) {

            }
        } else if (n == 4) {//修改课程学分
            System.out.print("\033[H\033[2J");
            System.out.println("请输入课程id：");
            String id = scanner.nextLine();
            System.out.println("请输入新学分：");
            int xf;
            try {
                xf = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("输入的学分不是有效的整数，请重新操作。");
                return false;
            }
            try (Connection conn = Pool.setPool(); Statement stmt = conn.createStatement()) {
                String sql = "UPDATE course SET xf=" + xf + " WHERE id='" + id + "'";
                stmt.executeUpdate(sql);
                System.out.println("学分修改成功");
            } catch (SQLException ignored) {

            }


        } else if (n == 5) {//查看课程名单
            System.out.print("\033[H\033[2J");
            System.out.println("请输入课程名：");
            String id = scanner.nextLine();
            try(ResultSet rs = JDBC.FindLess("course",id);) {
                while(rs.next()){
                    System.out.println("名字："+rs.getString("student"));
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }




        } else if (n == 6) {
            System.out.print("\033[H\033[2J");
            System.out.println("请输入课程id：");
            String id = scanner.nextLine();
            System.out.println("请输入课程学分：");
            int xf;
            try {
                xf = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("输入的学分不是有效的整数，请重新操作。");
                return false;
            }
            Less a = new Less(id, false, xf);
            JDBC.doless(a, "add");
            System.out.println("添加成功");
        } else if (n == 7) {
            System.out.print("\033[H\033[2J");
            System.out.println("请输入课程id：");
            String id = scanner.nextLine();
            Less a = new Less(id, false, 0);
            JDBC.doless(a, "delete");
            System.out.println("删除成功");
        } else if (n == 8) {
            return false;
        } else {
            System.out.println("输入错误");
        }

        return true;
    }
}