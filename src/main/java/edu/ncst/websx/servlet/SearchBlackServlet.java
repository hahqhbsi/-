package edu.ncst.websx.servlet;

import edu.ncst.websx.entity.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

//失败
@WebServlet(name="searchblack",urlPatterns = "/searchblack")
public class SearchBlackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从会话中获取user_id
        String ct_name = req.getParameter("ct_name");
        ct_name = URLDecoder.decode(ct_name, "UTF-8");
        // 执行搜索逻辑
        List<Person> persons = DBUtil.searchBlack(ct_name);
        req.setAttribute("persons", persons);
        RequestDispatcher rd = req.getRequestDispatcher("BlackList.jsp");
        rd.forward(req, resp);
  }
}