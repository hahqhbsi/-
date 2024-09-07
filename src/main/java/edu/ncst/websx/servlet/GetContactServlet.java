package edu.ncst.websx.servlet;
import edu.ncst.websx.entity.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.List;
//失败
@WebServlet(name="getContact",urlPatterns = "/getContact")
public class GetContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Connection conn=null;
        PreparedStatement stat=null;
        HttpSession session=req.getSession();
        String user_id = (String) session.getAttribute("user_id"); // 从会话中获取user_id
        String status = req.getParameter("status");


        if(status.equals("0"))
        {
            List<Person> persons=DBUtil.getContact(user_id);
            req.setAttribute("persons", persons);
            RequestDispatcher rd = req.getRequestDispatcher("ContactList.jsp");
            rd.forward(req, resp);
        }
       else if (status.equals("1"))
        {
            List<Person> persons=DBUtil.getfmContact(user_id,"女");
            req.setAttribute("persons", persons);
            RequestDispatcher rd = req.getRequestDispatcher("ContactList.jsp");
            rd.forward(req, resp);
        }
        else if (status.equals("2"))
        {
            List<Person> persons=DBUtil.getfmContact(user_id,"男");
            req.setAttribute("persons", persons);
            RequestDispatcher rd = req.getRequestDispatcher("ContactList.jsp");
            rd.forward(req, resp);
        }


  }
}