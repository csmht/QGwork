package com.user;

import com.alibaba.fastjson2.JSON;
import com.csmht.Pool;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import static com.csmht.user.User;


@WebServlet("/User/findclass")
public class find extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String n = request.getParameter("class");



        ResultSet rs = null;
        String username = null;
        HttpSession session  = request.getSession();
        username = session.getAttribute("username").toString();

        try {
            Connection conn = Pool.getPool();
            if (n.equals("all")) {
                rs = com.csmht.JDBC.find(conn,"course");
            } else if (n.equals("can")) {
                rs = com.csmht.JDBC.find(conn,"course","pd","0");
            } else if (n.equals("user")) {
                List<String> a = new ArrayList<>();
                a.add("course");
                a.add("less.course");
                a.add("course.course");
                rs = com.csmht.JDBC.find(conn,"less",a,"id",username);
            }
            Pool.returnConn(conn);

            Course course = new Course();
            if (rs != null) {
                while (rs.next()){
                        int a = rs.getInt("max");
                       String b = rs.getString("course");
                    Connection conn1 = Pool.getPool();
                        if(n.equals("can")&& (rs.getInt("max")==0|| com.csmht.JDBC.find(conn1,"less","id",username,"course",rs.getString("course")).next())){
                            continue;
                        }
                    Pool.returnConn(conn1);
                        course.id.add(rs.getString("course.order"));
                        course.name.add(rs.getString("course"));
                        course.xf.add(rs.getString("xf"));
                        course.max.add(rs.getString("max"));
                        course.pd.add(rs.getString("pd"));
                }

               String json = JSON.toJSONString(course);
                response.getWriter().write(json);
            }
        }catch (Exception e) {

            System.out.println("/User/findclass" + e);
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
