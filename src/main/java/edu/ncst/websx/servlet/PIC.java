package edu.ncst.websx.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
@MultipartConfig // 用于声明Servlet支持文件上传
@WebServlet(name="test",urlPatterns = "/test")
public class PIC extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FileItemFactory factory = new DiskFileItemFactory();
        // 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 开始解析请求信息
        List items = null;
        try {
            items = upload.parseRequest(request);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
        // 对所有请求信息进行判断
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            if (!item.isFormField()) {
                String fileName = item.getName();
                System.out.println(fileName);
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
                request.setAttribute("realFileName", fileName);
                String basePath = request.getRealPath("/images");
                File file = new File(basePath, fileName);

                try {
                    item.write(file);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("msg", "文件上传失败，写入文件出错！");
                    getServletContext().getRequestDispatcher("/AddNew.jsp").forward(request, response);
                    return; // 结束处理
                }
                Integer ct_idD= DBUtil.getMaxContactID();
                String ct_id=ct_idD.toString();
                Integer pic_idD= DBUtil.getMaxContactPicID();
                String  pic_id=pic_idD.toString();
                try {
                    DBUtil.addContactPicToDatabase(ct_id, pic_id, fileName);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("msg", "文件上传成功，但数据库操作出错！");
                    getServletContext().getRequestDispatcher("/AddNew.jsp").forward(request, response);
                    return; // 结束处理
                }
            }
        }
        request.setAttribute("msg", "文件上传成功，但数据库操作出错！");
        getServletContext().getRequestDispatcher("/AddNew.jsp").forward(request, response);
        }

}
