package edu.ncst.websx.servlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="change",urlPatterns = "/change")
public class ChangeServlet extends HttpServlet {
   protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
   {
       System.out.println("1");
       String ct_id = req.getParameter("ct_id");
       String ct_name = req.getParameter("ct_name");
       String ct_ad = req.getParameter("ct_ad");
       String ct_yb = req.getParameter("ct_yb");
       String ct_qq =req.getParameter("ct_qq");
       String ct_wx =req.getParameter("ct_wx");
       String ct_em =req.getParameter("ct_em");
       String ct_mf =req.getParameter("ct_mf");//?
       String ct_birth =req.getParameter("ct_birth");
       String ct_phone =req.getParameter("ct_phone");
       try {
           DBUtil.updateContact(ct_id, ct_name, ct_ad, ct_yb, ct_qq,ct_wx, ct_em,ct_mf,ct_birth,ct_phone,0);
           RequestDispatcher rd = req.getRequestDispatcher("ContactList.jsp");
           rd.forward(req, resp);

       } catch (SQLException e) {
           e.printStackTrace(); // 打印错误堆栈信息到服务器日志
           req.setAttribute("error", e.getMessage()); // 设置错误信息作为请求属性
           req.getRequestDispatcher("ContactList.jsp").forward(req, resp);
       }

   }
}
