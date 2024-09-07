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
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

//失败
@WebServlet(name="searchcontact",urlPatterns = "/searchcontact")
public class SearchContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从会话中获取user_id
        String ct_name = req.getParameter("ct_name");
        ct_name = URLDecoder.decode(ct_name, "UTF-8");
        // 执行搜索逻辑
        List<Person> persons = DBUtil.searchContact(ct_name);
        req.setAttribute("persons", persons);
        RequestDispatcher rd = req.getRequestDispatcher("ContactList.jsp");
        rd.forward(req, resp);
  }
}