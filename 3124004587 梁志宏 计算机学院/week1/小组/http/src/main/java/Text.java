import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Text {
    private static final Scanner scanner = new Scanner(System.in);
    public static String id;
    //学生登录
    public static boolean Stuin() {
        System.out.print("\033[H\033[2J");
        System.out.println("===学生登录====");
        System.out.print("请输入学号：");
        String id = scanner.nextLine();
        Text.id = id;
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        List<User> a = new ArrayList<>();
        try (ResultSet d = JDBC.find("user", id);){

            while(d.next()) {
                a.add(new User(d.getString("mima"), d.getString("id")));
            }

        } catch (Exception e){

            System.out.println("Login failed");
            return false;
        }

        return !a.isEmpty() && a.getFirst().getMima().equals(password) && a.getFirst().getStuid().equals(id);
    }
    //管理员登录
    public static boolean Guanin() {
        System.out.print("\033[H\033[2J");
        System.out.println("===管理员登录====");
        System.out.print("请输入管理员密码：");
        String password = scanner.nextLine();
        return password.equals("666666");
    }
    //注册
    public static boolean zc() throws SQLException, ClassNotFoundException {
        System.out.print("\033[H\033[2J");
        System.out.println("===注册学生账号====");
        System.out.print("请输入学号：");
        String id = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        System.out.print("请再次输入密码：");
        String password2 = scanner.nextLine();
        if (!password.equals(password2)) {
            System.out.println("两次输入的密码不一致");
            return false;
        }
        User a = new User(password, id);
        JDBC.insert(a, id);
        return true;
    }
}