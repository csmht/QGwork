package com.csmht.servic;

import com.csmht.dao.JDBC;
import com.csmht.dao.Pool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
*                     _ooOoo_
*                    o8888888o
*                    88" . "88
*                    (| -_- |)
*                    O\  =  /O
*                 ____/`---'\____
*               .'  \\|     |//  `.
*              /  \\|||  :  |||//  \
*             /  _||||| -:- |||||-  \
*             |   | \\\  -  /// |   |
*             | \_|  ''\---/''  |   |
*             \  .-\__  `-`  ___/-. /
*           ___`. .'  /--.--\  `. . __
*        ."" '<  `.___\_<|>_/___.'  >'"".
*       | | :  `- \`.;`\ _ /`;.`/ - ` : | |
*       \  \ `-.   \_ __\ /__ _/   .-` /  /
*    ======`-.____`-.___\_____/___.-`____.-'======
*                  `=---='
*    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
*    佛祖保佑        永无BUG
*    佛曰:
*    写字楼里写字间，写字间里程序员；
*    程序人员写程序，又拿程序换酒钱。
*    酒醒只在网上坐，酒醉还来网下眠；
*    酒醉酒醒日复日，网上网下年复年。
*    但愿老死电脑间，不愿鞠躬老板前；
*    奔驰宝马贵者趣，公交自行程序员。
*    别人笑我忒疯癫，我笑自己命太贱；
*    不见满街漂亮妹，哪个归得程序员？
**/


@WebServlet("/newUser")
public class NewUserRouter extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/plain;charset=UTF-8");
//        response.setStatus(405);
//        PrintWriter out = response.getWriter();
//        out.println("405 Method Not Allowed: Must use post");
//        out.close();
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String req = null;
//        {
//            StringBuilder sb = new StringBuilder();
//            BufferedReader reader = request.getReader();
//            for(String line = reader.readLine(); line != null; line = reader.readLine()){
//                sb.append(line);
//            }
//            req = sb.toString();
//        }
//
//
//        String username = request.getParameter("newUserName");
//        String password = request.getParameter("newPassWord");
//        System.out.printf("username: %s\n", username);
//        System.out.printf("password: %s\n", password);
//
//        String mun = request.getParameter("newmun");
//        String ans = "{\"success\": false}";

        String username = request.getParameter("newUserName");
        String password = request.getParameter("newPassWord");
        String mun = request.getParameter("newmun");
        boolean ans = false;
        try {
            int n = 0;
            Connection conn = Pool.Pool.getPool();
            n += JDBC.add(conn,"student","id",username,"num",mun);
            n += JDBC.add(conn,"user","id",username,"mima",password);
            Pool.Pool.returnConn(conn);


            if(n!=0){
                ans = true;
            }
        } catch (Exception ignored) {
            System.out.println(ignored.toString());
        }finally {
//            response.setContentType("text/plain;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println(ans);
//            out.close();
            response.getWriter().write(ans+"");

        }


    }
}
