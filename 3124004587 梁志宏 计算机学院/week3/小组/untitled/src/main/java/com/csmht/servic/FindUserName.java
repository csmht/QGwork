package com.csmht.servic;

import com.csmht.dao.Pool;
import com.csmht.dao.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;


@WebServlet("/text")
public class FindUserName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        boolean op = false;
        try {
            Connection conn = Pool.Pool.getPool();
            ResultSet rs = JDBC.find(conn, "user", "id", username);
            Pool.Pool.returnConn(conn);
            while (rs.next()) {
                op = true;
            }

        } catch (Exception ignored) {
            System.out.println("Exception e) ");
            op = false;
        }


        response.getWriter().write(op ? "true" : "false");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
