package com.csmht.servic;

import com.alibaba.fastjson2.JSON;
import com.csmht.dao.Pool;
import com.csmht.dao.JDBC;
import com.csmht.dao.Course;
import com.csmht.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/User")
public class UserBus extends BaseServlet {

    static {
        System.out.println("UserBus....");
    }



    public void findClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String n = request.getParameter("class");



        ResultSet rs = null;
        String username = null;
        HttpSession session  = request.getSession();
        username = session.getAttribute("username").toString();

        try {
            Connection conn = Pool.Pool.getPool();
            if (n.equals("all")) {
                rs = JDBC.find(conn,"course");
            } else if (n.equals("can")) {
                rs = JDBC.find(conn,"course","pd","0");
            } else if (n.equals("user")) {
                List<String> a = new ArrayList<>();
                a.add("less");
                a.add("less.course");
                a.add("course.course");
                rs = JDBC.find(conn,"course",a,"id",username);
            }
            Pool.Pool.returnConn(conn);

            Course course = new Course();
            if (rs != null) {
                while (rs.next()){
                        int a = rs.getInt("max");
                       String b = rs.getString("course");
                    Connection conn1 = Pool.Pool.getPool();
                        if(n.equals("can")&& (rs.getInt("max")==0|| JDBC.find(conn1,"less","id",username,"course",rs.getString("course")).next())){
                            continue;
                        }
                    Pool.Pool.returnConn(conn1);
                        course.id.add(rs.getString("course.main"));
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



    public void returnClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        String id = request.getParameter("id");
        String course = null;Connection conn = null;
        boolean pd = false;
        try {
            conn = Pool.Pool.getPool();
            conn.setAutoCommit(false);
            ResultSet rs = JDBC.find(conn,"course","main",id);
            rs.next();
            course = rs.getString("course");

            String[] who = {"main",id};
            JDBC.Edit(conn,"course",who,"max",String.valueOf(rs.getInt("max")+1));
            JDBC.delete(conn,"less","id",username,"course",course);

            conn.commit();
            Pool.Pool.returnConn(conn);
            pd = true;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

        response.getWriter().println(pd);
    }


    public void closeClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String id = request.getParameter("id");

        boolean pd = false;

        try {
            HttpSession sei = request.getSession();
            String username = sei.getAttribute("username").toString();

            con = Pool.Pool.getPool();
            con.setAutoCommit(false);
            ResultSet rs = JDBC.find(con,"course","main",id);
            rs.next();
            String[] a = {"main",id};
            JDBC.Edit(con,"course",a,"max",String.valueOf((rs.getInt("max")-1)));
            JDBC.add(con,"less","id",username,"course",rs.getString("course"));

            con.commit();
            pd = true;
        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException e) {");
            }
            System.out.println("SQLException e) {");
        }finally {
            if(con != null) {
                Pool.Pool.returnConn(con);
            }
        }
        response.getWriter().println(pd);

    }

}
