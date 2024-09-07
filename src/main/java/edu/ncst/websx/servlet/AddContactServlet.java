package edu.ncst.websx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
@MultipartConfig // 用于声明Servlet支持文件上传
@WebServlet(name="add",urlPatterns = "/addnew")
public class AddContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // String user_id = req.getParameter("user_id");
        HttpSession session=req.getSession();
        String user_id = (String) session.getAttribute("user_id"); // 从会话中获取user_id
        if (user_id == null) {
            System.out.println("user_id is null"); // 这将在服务器控制台输出
        } else {
            req.setAttribute("message",user_id); // 这将在服务器控制台输出user_id的值
        }
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        Integer ct_id =DBUtil.getMaxContactID();
        String ct_name = req.getParameter("ct_name");
        String ct_ad = req.getParameter("ct_ad");
        String ct_yb = req.getParameter("ct_yb");
        String ct_qq =req.getParameter("ct_qq");
        String ct_wx =req.getParameter("ct_wx");
        String ct_em =req.getParameter("ct_em");
        String ct_mf =req.getParameter("ct_mf");//?
        String ct_birth =req.getParameter("ct_birth");
        String ct_phone =req.getParameter("ct_phone");
        String nct_id=Integer.toString(ct_id);

        try {

            DBUtil.addContact(user_id, nct_id, ct_name, ct_ad, ct_yb, ct_qq,ct_wx, ct_em,ct_mf,ct_birth,ct_phone,0);
           DBUtil.addContact(user_id,nct_id,ct_name,ct_ad, ct_yb, ct_qq,ct_wx, ct_em,"女",ct_birth,ct_phone,0);
            // 这里可以添加成功消息到session或转发到其他页面
            req.setAttribute("message","成功");
            req.getRequestDispatcher("ContactList.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}