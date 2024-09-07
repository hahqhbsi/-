package edu.ncst.websx.servlet;

import edu.ncst.websx.entity.Person;
import edu.ncst.websx.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name="LoginServlet",urlPatterns = "/loginForm")
public class LoginServlet extends HttpServlet {
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String user_id=req.getParameter("user_id");
        String user_password=req.getParameter("user_password");
        String getpsw=DBUtil.getUser(user_id);
        HttpSession session= req.getSession();
        session.setAttribute("user_id", user_id);
        if(Objects.equals(user_id, " ") || Objects.equals(user_password, " "))
        {
            req.setAttribute("message","空");
        }
        if (Objects.equals(user_password, getpsw))
        {
            req.setAttribute("message","登陆成功");
        }
        else{
            req.setAttribute("message","错误");
        }
        RequestDispatcher rd = req.getRequestDispatcher("/login_result.jsp");
        rd.forward(req, resp);
  }
}