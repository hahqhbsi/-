package edu.ncst.websx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name="addmatter",urlPatterns = "/addmatter")
public class AddMatterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String ct_name = req.getParameter("ct_name");
        String ct_id=DBUtil.beforeAddMatter(ct_name);
        String matter_time = req.getParameter("matter_time");
        String matter = req.getParameter("matter");
        Integer matter_id1 = DBUtil.getMaxMatterID(); // 假设这个方法返回一个Integer类型的值
        String matter_id = matter_id1.toString(); // 使用toString()方法转换为字符串

        DBUtil.addMatter(ct_id,matter_id,matter_time,matter);
       //String ct_id, String matter_id, String matter_time,String matter
        req.setAttribute("message","成功");
        req.getRequestDispatcher("Matter.jsp").forward(req, resp);


  }
}