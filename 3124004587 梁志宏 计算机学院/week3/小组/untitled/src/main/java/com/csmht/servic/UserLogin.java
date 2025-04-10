package com.csmht.servic;

import com.csmht.dao.Pool;
import com.csmht.dao.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;


@WebServlet("/login")
public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("loginUserName");
        String password = request.getParameter("loginPassWord");
        String mun=null;
            System.out.println(username);
        try {
            Connection conn = Pool.Pool.getPool();
           ResultSet rs =  JDBC.find(conn,"user","id",username,"mima",password);
            Pool.Pool.returnConn(conn);
           boolean pd = false;
           while(rs.next()){
               pd = true;
           }

           if(pd){
               HttpSession session = request.getSession();
               session.setAttribute("username",username);

                response.sendRedirect("User/UserMav.html");
           }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
