import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudenWork {
    public static boolean SW() {
        Prin.StuCread();
        Scanner scanner = new Scanner(System.in);
        int n;
        try {
            n = scanner.nextInt();
            scanner.nextLine(); // 消耗掉 nextInt() 后的换行符
        } catch (Exception e) {
            System.out.println("输入错误，请重新输入");
            scanner.nextLine();
            return true;
        }
        try (Connection conn = Pool.setPool();Statement stam = conn.createStatement()){
            String sql;
            if (n == 1) {
                Prin.GuanSeeAll("课程");
            } else if (n == 2) {
                System.out.print("\033[H\033[2J");
                System.out.println("可选课程为：");
                sql = "SELECT c.id, c.`xf` FROM course c WHERE c.pd = 0 AND c.id NOT IN (SELECT l.course FROM `less` l WHERE l.student = '"+ Text.id +"');";
                ResultSet rs = stam.executeQuery(sql);
                while (rs.next()) {
                System.out.println(rs.getString("id") + "  学分："+rs.getInt("xf"));
                }
                System.out.println("请输入选择课程名：");
                String cose = scanner.nextLine();
                sql = "SELECT * FROM course WHERE course.id = '"+ cose +"'";
                ResultSet rs1 = stam.executeQuery(sql);
                rs1.next();
                boolean pd = rs1.getBoolean("pd");
                if(pd){
                    System.out.println("该课程已经开课，无法选择");
                }else {
                sql = "INSERT INTO less (student, course) VALUES ('"  + Text.id + "', '" + cose + "')";
                stam.executeUpdate(sql);
                System.out.println("修改成功");}
            } else if (n == 3) {
                System.out.print("\033[H\033[2J");
                System.out.println("可退课程为：");
                sql = "SELECT l.course, c.`xf` FROM LESS l JOIN course c ON l.course = c.id WHERE l.student = '" + Text.id +"' AND c.pd = 0";
                ResultSet rs = stam.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(rs.getString("course"));
                }
                System.out.println("请输入课程名");
                String course = scanner.nextLine();
                sql = "DELETE FROM less WHERE student = '"+ Text.id +"' AND course = '" + course +"'";
                stam.executeUpdate(sql);
                System.out.println("删除成功");
            } else if (n == 4) {
                sql = "SELECT l.course, c.`xf` FROM LESS l JOIN course c ON l.course = c.id WHERE l.student = '" + Text.id +"'";
                ResultSet rs4 = stam.executeQuery(sql);
                while (rs4.next()) {
                    System.out.println("名字："+rs4.getString("course")+"   学分"+rs4.getInt("xf"));
                }
            } else if (n == 5) {
                return false;
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }


        return true;
    }
}
