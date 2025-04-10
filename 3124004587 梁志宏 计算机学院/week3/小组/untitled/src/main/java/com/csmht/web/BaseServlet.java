package com.csmht.web;

import com.csmht.servic.UserBus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

//@WebServlet("/User")
public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBus userBus = new UserBus();
        String methodName = request.getParameter("method");

        System.out.println(Arrays.toString(this.getClass().getConstructors()));

        try {
            if(methodName!=null){
                Method method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
                method.invoke(this,request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
