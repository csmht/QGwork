import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Prin{
    public static void Cread(){
        System.out.print("\033[H\033[2J");
        System.out.println("欢迎使用学生选课系统");
        System.out.println("1.学生登录");
        System.out.println("2.管理员登录");
        System.out.println("3.注册学生账号");
        System.out.println("4.return");
        System.out.print("请输入您的选择：");
    }

    public static void StuCread(){
        System.out.print("\033[H\033[2J");
        System.out.println("===学生菜单====");
        System.out.println("1.查看课程");
        System.out.println("2.选课");
        System.out.println("3.退课");
        System.out.println("4.查看已选课程");
        System.out.println("5.退出");
        System.out.print("请输入您的选择：");
    }

    public static void GuanCread(){
        System.out.print("\033[H\033[2J");
        System.out.println("===管理员菜单====");
        System.out.println("1.查看所有学生");
        System.out.println("2.修改学生手机号");
        System.out.println("3.查询所有课程");
        System.out.println("4.修改课程学分");
        System.out.println("5.查询某课程的学生名单");
        System.out.println("6.添加课程");
        System.out.println("7.删除课程");
        System.out.println("8.退出");
        System.out.print("请输入您的选择：");
    }

    public static void GuanSeeAll(String op) throws ClassNotFoundException, SQLException {
        System.out.print("\033[H\033[2J");


        Connection conn = Pool.setPool();
        Statement stmt = null;
        String a = "student";
        if(op.equals("学生")){
            a = "student";
        }else if(op.equals("课程")){
            a = "course";}

        try {//获取连接
            stmt = conn.createStatement();
            String sql = "SELECT * FROM "+ a;//打开相应的数据库
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("所有" + op + "信息：");
            if(resultSet == null){
                System.out.println("没有" + op + "信息");
                return;
            }
            //遍历结果集

            if(op.equals("课程")){
                while (resultSet.next()) {
                    System.out.println(op+"名：" + resultSet.getString("id") + ", " + op + "学分：" + resultSet.getInt("xf") );
                }
            }else{
            while (resultSet.next()) {
                System.out.println(op+"号：" + resultSet.getString("id") + ", " + op + "姓名：" + resultSet.getString("name") + ", " + op + "学分：" + resultSet.getInt("xf")+ "，电话" + resultSet.getString("num"));
            }}


        } catch (SQLException ignored) {

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {

            }
        }
    }

    public void StuSee(String op,String id) throws ClassNotFoundException, SQLException {
        System.out.print("\033[H\033[2J");

        Connection conn = Pool.setPool();
        Statement stmt = null;
        String sql = "SELECT * FROM less WHERE pd = 0";
        List<String> ans = new ArrayList<>();

        if(op.equals("可选")){
            sql = "SELECT * FROM course WHERE pd = 0";
        }else if(op.equals("已选")){
            sql = "SELECT * FROM course FROM less WHERE student-id = '" + id + "'";
        }


        try {//获取连接
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("所有" + op + "课程信息：");
            if(resultSet == null){
                System.out.println("没有" + op + "课程信息");
                return;
            }
            //遍历结果集
            while (resultSet.next()) {
                System.out.println("课程号：" + resultSet.getString("num") + ", 课程名：" + resultSet.getString("name") + ", 学分：" + resultSet.getInt("xf"));
            }
        } catch (SQLException ignored) {

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {

            }
        }
    }



}