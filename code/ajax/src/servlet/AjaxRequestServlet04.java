package servlet;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;
import javax.servlet.annotation.*;

import util.*;

@WebServlet({"/verifyusername"})
public class AjaxRequestServlet04 extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //接收参数
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");

        //连接数据库验证
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(username) as c from user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();
            rs.next();

            count = rs.getInt("c");
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }

        //响应一个标志
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (count == 0) {//getInt()如果没有查到是给0
            out.println("<font color='green'>用户名可用</font>");
        } else {
            out.println("<font color='red'>用户名已存在</font>");
        }
    }
}
