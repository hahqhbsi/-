package edu.ncst.websx.servlet;

import edu.ncst.websx.entity.Matter;
import edu.ncst.websx.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/websx?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeResources(ResultSet rs, PreparedStatement stat, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String getUser(String user_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String userPassword = null;
        try {
            conn = getConnection();
            String sql = "SELECT user_password FROM t_user WHERE user_id = ?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, user_id);
            rs = stat.executeQuery();

            if (rs.next()) {
                userPassword = rs.getString("user_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return userPassword;
    }
    public static String getUserPic(String user_id)//方法功能为获取登录用户头像图片信息，接收用户ID，返回用户图片名称。
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String pic_name=null;
        try {
            conn = getConnection();
            String sql = "SELECT pic_name FROM user_pics WHERE user_id = ?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, user_id);
            rs = stat.executeQuery();

            if (rs.next()) {
                pic_name = rs.getString("pic_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return pic_name;
    }
    public static List<Person> getContact(String user_id)    // 添加联系人的方法
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Person>persons=new ArrayList<>();
        try {
            conn = getConnection();
            // 假设每页的起始索引是(startIndex, endIndex)
            String sql = "SELECT ct_name, ct_mf,ct_phone, ct_id FROM p_contact WHERE user_id=? AND ct_delete=0";
            stat = conn.prepareStatement(sql);
            stat.setString(1, user_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                Person p=new Person();
                p.setCt_name(rs.getString("ct_name"));
                p.setCt_mf(rs.getString("ct_mf"));
                p.setCt_phone(rs.getString("ct_phone"));
                p.setCt_id(rs.getString("ct_id"));
                persons.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return persons;
    }
    public static List<Person> getfmContact(String user_id,String ct_mf) //得到男，女
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Person>persons=new ArrayList<>();
        try {
            conn = getConnection();
            // 假设每页的起始索引是(startIndex, endIndex)
            String sql = "SELECT ct_name, ct_mf,ct_phone, ct_id FROM p_contact WHERE user_id=? AND ct_delete=0 AND ct_mf=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, user_id);
            stat.setString(2, ct_mf);
            rs = stat.executeQuery();
            while (rs.next()) {
                Person p=new Person();
                p.setCt_name(rs.getString("ct_name"));
                p.setCt_mf(rs.getString("ct_mf"));
                p.setCt_phone(rs.getString("ct_phone"));
                p.setCt_id(rs.getString("ct_id"));
                persons.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return persons;
    }
    public static List<Person> searchContact(String ct_name)  // 查找联系人的方法
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Person>persons=new ArrayList<>();
        try {
            conn = getConnection();
            // 假设每页的起始索引是(startIndex, endIndex)
            String sql = "SELECT ct_name, ct_mf,ct_phone, ct_id FROM p_contact WHERE ct_name=? AND ct_delete=0";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_name);
            rs = stat.executeQuery();
            while (rs.next()) {
                Person p=new Person();
                p.setCt_name(rs.getString("ct_name"));
                p.setCt_mf(rs.getString("ct_mf"));
                p.setCt_phone(rs.getString("ct_phone"));
                p.setCt_id(rs.getString("ct_id"));
                persons.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return persons;
    }
    public static List<Person> searchBlack(String ct_name)
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Person>persons=new ArrayList<>();
        try {
            conn = getConnection();
            // 假设每页的起始索引是(startIndex, endIndex)
            String sql = "SELECT ct_name, ct_mf,ct_phone, ct_id FROM p_contact WHERE ct_name=? AND ct_delete=1";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_name);
            rs = stat.executeQuery();
            while (rs.next()) {
                Person p=new Person();
                p.setCt_name(rs.getString("ct_name"));
                p.setCt_mf(rs.getString("ct_mf"));
                p.setCt_phone(rs.getString("ct_phone"));
                p.setCt_id(rs.getString("ct_id"));
                persons.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return persons;
    }
    public static Person getOneContact(String ct_id, Integer ct_delete) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Person person = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM p_contact WHERE ct_id = ? AND ct_delete = ?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_id);
            stat.setInt(2, ct_delete);
            rs = stat.executeQuery();
            if (rs.next()) {
                person = new Person();
                person.setCt_name(rs.getString("ct_name"));
                person.setCt_mf(rs.getString("ct_mf"));
                person.setCt_em(rs.getString("ct_em"));
                person.setCt_yb(rs.getString("ct_yb"));
                person.setCt_wx(rs.getString("ct_wx"));
                person.setCt_qq(rs.getString("ct_qq"));
                person.setCt_ad(rs.getString("ct_ad"));
                person.setCt_birth(rs.getString("ct_birth"));
                person.setCt_phone(rs.getString("ct_phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return person;
    }

    public static String getContactPic(String ct_id)//方法功能为获取联系人头像图片信息，接收用户ID，返回用户图片名称。
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String pic_name=null;
        try {
            conn = getConnection();
            String sql = "SELECT pic_name FROM p_pics WHERE ct_id = ?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_id);
            rs = stat.executeQuery();

            if (rs.next()) {
                pic_name = rs.getString("pic_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return pic_name;
    }
    public static Integer getMaxContactID()//返回已经自增的
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Integer maxId=0;
        try {
            conn = getConnection();
            String sql = "SELECT  MAX(ct_id) FROM p_contact";
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                maxId=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return maxId+1;
    }
    public static Integer getMaxContactPicID()
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Integer maxId=0;
        try {
            conn = getConnection();
            String sql = "SELECT  MAX(pic_id) FROM p_pics";
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                maxId=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return maxId+1;
    }
    public static void updateContact(String ct_id, String ct_name, String ct_ad, String ct_yb, String ct_qq, String ct_wx, String ct_em, String ct_mf, String ct_birth ,String ct_phone,Integer ct_delete) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "UPDATE p_contact SET ct_name = ?,ct_ad = ?,ct_yb = ?,ct_qq = ?,ct_wx = ?,ct_em = ?,ct_mf = ?,ct_birth = ?,ct_phone = ?,ct_delete =? WHERE ct_id=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_name);
            stat.setString(2, ct_ad);
            stat.setString(3, ct_yb);
            stat.setString(4, ct_qq);
            stat.setString(5, ct_wx);
            stat.setString(6, ct_em);
            stat.setString(7, ct_mf);
            stat.setString(8, ct_birth);
            stat.setString(9, ct_phone);
            stat.setInt(10, ct_delete);
            stat.setString(11, ct_id);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }

    }
    public static void updateContactPic(String ct_id,String pic_id,String pic_name)
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "UPDATE p_pics SET pic_id = ?,pic_name =? WHERE ct_id=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, pic_id);
            stat.setString(2, pic_name);
            stat.setString(3, ct_id);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
    }
    public static void addContact(String user_id, String ct_id, String ct_name, String ct_ad, String ct_yb, String ct_qq, String ct_wx, String ct_em, String ct_mf, String ct_birth ,String ct_phone,Integer ct_delete) throws SQLException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO p_contact (user_id, ct_id, ct_name, ct_ad, ct_yb,  ct_qq,ct_wx, ct_em, ct_mf, ct_birth,ct_phone,ct_delete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stat = conn.prepareStatement(sql);
            stat.setString(1, user_id);
            stat.setString(2, ct_id);
            stat.setString(3, ct_name);
            stat.setString(4, ct_ad);
            stat.setString(5, ct_yb);
            stat.setString(6, ct_qq);
            stat.setString(7, ct_wx);
            stat.setString(8, ct_em);
            stat.setString(9, ct_mf);
            stat.setString(10, ct_birth);
            stat.setString(11, ct_phone);
            stat.setInt(12, ct_delete);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
    }
    public static void addContactPic(String ct_id,String pic_id,String pic_name)
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO p_pics (ct_id,pic_id,pic_name) VALUES (?, ?, ?)";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_id);
            stat.setString(2,pic_id);
            stat.setString(3, pic_name);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
    }
    public static void deleteContact(String ct_id)//拉黑
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "UPDATE p_contact SET ct_delete =1 WHERE ct_id=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_id);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
    }
    public static void cancelDeleteContact(String ct_id)//解除拉黑
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "UPDATE p_contact SET ct_delete =0 WHERE ct_id=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_id);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
    }

    public static List<Matter> getMatterUser(String user_id, Integer matter_delete) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Matter>matter=new ArrayList<>();
        try {
            conn = getConnection();
            String sql = "SELECT ct_name, matter_time, matter, matter_id FROM p_contact JOIN p_matter ON p_contact.ct_id = p_matter.ct_id WHERE user_id = ? AND matter_delete = ? ";
            stat = conn.prepareStatement(sql);
            stat.setString(1, user_id);
            stat.setInt(2, matter_delete);
            rs = stat.executeQuery();
            while (rs.next()) {
                Matter m=new Matter();
                m.setMatter(rs.getString("matter"));
                m.setCt_name(rs.getString("ct_name"));
                m.setMatter_time(rs.getString("matter_time"));
                m.setMatter_id(rs.getString("matter_id"));
                matter.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return matter;
    }
    //上面是用户的事项，下面的是跟这个联系人有关的事件
    public static  List<Matter>getMatterContact(String ct_id, int matter_delete) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Matter> matter = new ArrayList<>();
        try {
            conn = getConnection();
            String sql = " SELECT ct_name, matter, matter_delete FROM p_contact JOIN p_matter ON p_contact.ct_id = p_matter.ct_id WHERE p_contact.ct_id = ? AND matter_delete=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_id);
            stat.setInt(2, matter_delete);
            rs = stat.executeQuery();

            if (rs.next()) {
                Matter m = new Matter();
                Person p = new Person();
                m.setMatter(rs.getString("matter"));
                p.setCt_name(rs.getString("ct_name"));
                m.setMatter_time(rs.getString("matter_time"));
                m.setMatter_id(rs.getString("matter_id"));
                matter.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return matter;
    }
    public static void deleteMatter(String matter_id)//取消事件
    {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "UPDATE p_matter SET matter_delete =1 WHERE matter_id=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, matter_id);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
    }
    public static void finishMatter(String matter_id)//完成事件
    {

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "UPDATE p_matter SET matter_delete =2 WHERE matter_id=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, matter_id);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
    }
    public static String beforeAddMatter(String ct_name)//查找ct_id,matter里的
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String id=null;
        try {
            conn = getConnection();
            String sql = "SELECT ct_id FROM p_contact WHERE ct_name=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_name);
            rs = stat.executeQuery();
            if (rs.next()) {
                id = rs.getString("ct_id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return id;
    }
    public static void addMatter(String ct_id, String matter_id, String matter_time,String matter)
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO p_matter (ct_id,matter_id,matter_time,matter,matter_delete) VALUES (?, ?, ?,?,0)";
            stat = conn.prepareStatement(sql);
            stat.setString(1, ct_id);
            stat.setString(2,matter_id);
            stat.setString(3,  matter_time);
            stat.setString(4,  matter);
            stat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
    }
    public static Integer getMaxMatterID()//返回已经自增的
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Integer maxId=0;
        try {
            conn = getConnection();
            String sql = "SELECT  MAX(matter_id) FROM p_matter";
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                maxId=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return maxId+1;
    }

    public static List<Person> getBlack(String user_id ,Integer ct_delete)
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Person>gb=new ArrayList<>();
        try {
            conn = getConnection();
            // 假设每页的起始索引是(startIndex, endIndex)
            String sql = "SELECT * FROM p_contact WHERE user_id=? AND ct_delete=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, user_id);
            stat.setInt(2, ct_delete);
            rs = stat.executeQuery();
            while (rs.next()) {
                Person p=new Person();
                p.setCt_name(rs.getString("ct_name"));
                p.setCt_mf(rs.getString("ct_mf"));
                p.setCt_phone(rs.getString("ct_phone"));
                p.setCt_id(rs.getString("ct_id"));
                gb.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return gb;
    }
    public static List<Person> getfmBlack(String user_id,String ct_mf) //得到男，女
    {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Person>persons=new ArrayList<>();
        try {
            conn = getConnection();
            // 假设每页的起始索引是(startIndex, endIndex)
            String sql = "SELECT ct_name, ct_mf,ct_phone, ct_id FROM p_contact WHERE user_id=? AND ct_delete=1 AND ct_mf=?";
            stat = conn.prepareStatement(sql);
            stat.setString(1, user_id);
            stat.setString(2, ct_mf);
            rs = stat.executeQuery();
            while (rs.next()) {
                Person p=new Person();
                p.setCt_name(rs.getString("ct_name"));
                p.setCt_mf(rs.getString("ct_mf"));
                p.setCt_phone(rs.getString("ct_phone"));
                p.setCt_id(rs.getString("ct_id"));
                persons.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return persons;
    }
    public static List<Matter> searchMatter(String matter) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Matter>matters=new ArrayList<>();
        try {
            conn = getConnection();
            String sql = "SELECT ct_name, matter_time, matter, matter_id FROM p_contact JOIN p_matter ON p_contact.ct_id = p_matter.ct_id WHERE matter = ? ";
            stat = conn.prepareStatement(sql);
            stat.setString(1, matter);
            rs = stat.executeQuery();
            while (rs.next()) {
                Matter m=new Matter();
                m.setMatter(rs.getString("matter"));
                m.setCt_name(rs.getString("ct_name"));
                m.setMatter_time(rs.getString("matter_time"));
                m.setMatter_id(rs.getString("matter_id"));
                matters.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stat, conn);
        }
        return matters;
    }
    public static void addContactPicToDatabase(String ct_id, String pic_id, String pic_name) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO p_pics (ct_id, pic_id, pic_name) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ct_id);
            pstmt.setString(2, pic_id);
            pstmt.setString(3, pic_name);
            pstmt.executeUpdate();
        } finally {
            // 关闭资源
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }






}
