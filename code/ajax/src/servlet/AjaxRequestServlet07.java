package servlet;

import util.JDBCUtil;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;
import javax.servlet.annotation.*;

@WebServlet({"/ajaxrequest07"})
public class AjaxRequestServlet07 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //连接数据库查询数据
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder xmlBuilder = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select name, age, addr from stu;";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            //<stu><name>hwk</name><age>11</age><addr>北京海淀区</addr></stu>
            xmlBuilder = new StringBuilder();
            xmlBuilder.append("<students>");
            while (rs.next()) {
                xmlBuilder.append("<student>");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String addr = rs.getString("addr");

                xmlBuilder.append("<name>" + name + "</name><age>" + age + "</age><addr>" + addr + "</addr>");
                xmlBuilder.append("</student>");
            }
            xmlBuilder.append("</students>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        //将封装好的xml数据回传给前端
        response.setContentType("text/xml;charset=UTF-8");//必须是text/xml
        PrintWriter out = response.getWriter();
        out.println(xmlBuilder);
    }
}
