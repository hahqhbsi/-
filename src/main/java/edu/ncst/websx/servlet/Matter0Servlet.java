package edu.ncst.websx.servlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="finishmatter",urlPatterns = "/finishmatter")
public class Matter0Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matter_id = req.getParameter("matter_id");
            DBUtil.finishMatter(matter_id);
            RequestDispatcher rd = req.getRequestDispatcher("Matter.jsp");
            rd.forward(req, resp);
  }
}