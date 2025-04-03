package com.csmht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/login")
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("loginUserName");
        String password = request.getParameter("loginPassWord");
            System.out.println(username);
        try {
           ResultSet rs =  JDBC.find("user","id",username,"mima",password);
           boolean pd = false;
           while(rs.next()){
               pd = true;
           }

           if(pd){
                response.sendRedirect("User/UserMav.html");
           }else{

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
