package com.csmht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.csmht.user.User;


@WebServlet("/login")
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("loginUserName");
        String password = request.getParameter("loginPassWord");
        String mun=null;
            System.out.println(username);
        try {
            Connection conn = Pool.getPool();
           ResultSet rs =  JDBC.find(conn,"user","id",username,"mima",password);
            Pool.returnConn(conn);
           boolean pd = false;
           while(rs.next()){
               pd = true;
           }

           if(pd){
               HttpSession session = request.getSession();
               session.setAttribute("username",username);

                response.sendRedirect("User/UserMav.html");
           }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
