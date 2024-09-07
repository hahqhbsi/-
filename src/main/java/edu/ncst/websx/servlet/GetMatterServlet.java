package edu.ncst.websx.servlet;
import edu.ncst.websx.entity.Matter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//失败
@WebServlet(name="getmatter",urlPatterns = "/getmatter")
public class GetMatterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        String user_id = (String) session.getAttribute("user_id"); // 从会话中获取user_id
        String statustr = req.getParameter("status");
        String ct_id = req.getParameter("ct_id");
            int status;
            status = Integer.parseInt(statustr);
            List<Matter> matters=DBUtil.getMatterUser(user_id,status);
            req.setAttribute("matters", matters);
            RequestDispatcher rd = req.getRequestDispatcher("Matter.jsp");
            rd.forward(req, resp);

  }
}