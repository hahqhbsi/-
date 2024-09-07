package edu.ncst.websx.servlet;

import edu.ncst.websx.entity.Matter;
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
@WebServlet(name="searchmatter",urlPatterns = "/searchmatter")
public class SearchMatterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从会话中获取user_id
        String matter = req.getParameter("matter");
        matter = URLDecoder.decode(matter, "UTF-8");
        // 执行搜索逻辑
        List<Matter> matters = DBUtil.searchMatter(matter);
        req.setAttribute("matters", matters);
        RequestDispatcher rd = req.getRequestDispatcher("Matter.jsp");
        rd.forward(req, resp);
  }
}