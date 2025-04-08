package com.csmht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/text")
public class loginuser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
//        System.out.println("fuck");
        boolean op = false;
        try {
            Connection conn = Pool.getPool();
            ResultSet rs = JDBC.find(conn,"user","id",username);
            Pool.returnConn(conn);
            while(rs.next()){
                op = true;
            }

        } catch (SQLException ignored) {
            System.out.println("SQLException e) ");
            op = false;
        }


        response.getWriter().write(op ? "true" : "false");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
