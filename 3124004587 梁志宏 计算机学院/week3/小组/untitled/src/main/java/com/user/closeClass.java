package com.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/User/close")
public class closeClass extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String id = request.getParameter("id");

        boolean pd = false;

        try {
            HttpSession sei = request.getSession();
            String username = sei.getAttribute("username").toString();

              con = com.csmht.Pool.getPool();
              con.setAutoCommit(false);
            ResultSet rs = com.csmht.JDBC.find(con,"course","order",id);
            String[] a = {"max"};
            com.csmht.JDBC.Edit(con,"course",a, String.valueOf((rs.getInt("max")-1)));
            com.csmht.JDBC.add(con,"less","id",username,"course",rs.getString("course"));

              con.commit();
              pd = true;
        } catch (SQLException e) {
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
                com.csmht.Pool.returnConn(con);
            }
        }
        response.getWriter().write(pd+"");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
