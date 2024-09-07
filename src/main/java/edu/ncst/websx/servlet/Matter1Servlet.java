package edu.ncst.websx.servlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="canclematter",urlPatterns = "/canclematter")
public class Matter1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matter_id = req.getParameter("matter_id");
        // 调用DBUtil.deleteContact(ct_id);
            DBUtil.deleteMatter(matter_id);
            RequestDispatcher rd = req.getRequestDispatcher("Matter.jsp");
            rd.forward(req, resp);
  }
}