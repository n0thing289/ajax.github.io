package servlet;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.Stu;
import com.alibaba.fastjson.JSON;
import util.*;

@WebServlet({"/ajaxrequest06"})
public class AjaxRequestServlet06 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //响应给前端数据
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        //连接数据库,封装

        //假设查询好数据后回传给前端
//        out.println("<tr>");
//        out.println("<td>1</td>");
//        out.println("<td>张三</td>");
//        out.println("<td>20</td>");
//        out.println("<td>北京</td>");
//        out.println("</tr>");
//        out.println("<tr>");
//        out.println("<td>2</td>");
//        out.println("<td>李四</td>");
//        out.println("<td>22</td>");
//        out.println("<td>北京海淀区</td>");
//        out.println("</tr>");

        //传json格式字符串
//        String json = "[{\"name\":\"王五\",\"age\":20,\"addr\":\"北京\"},{\"name\":\"王五\",\"age\":20,\"addr\":\"上海\"}]";//
//        out.print(json);


        //连接数据库封装数据
        StringBuilder jsonBuilder = new StringBuilder();
        String json = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select name,age,addr from stu;";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

//            jsonBuilder.append("[");
//            while (rs.next()) {
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                String addr = rs.getString("addr");
//
//                jsonBuilder.append("{\"name\":\"" + name + "\",\"age\":" + age + ",\"addr\":\"" + addr + "\"},");
//            }
//            json = jsonBuilder.toString();
//            json = json.substring(0, json.length() - 1);
//            json += "]";
            List<Stu> stuList = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String addr = rs.getString("addr");
                //封装到stu对象中
                Stu stu = new Stu(name, age, addr);
                stuList.add(stu);
            }
            json = JSON.toJSONString(stuList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }

        System.out.println(json);
        out.println(json);
    }

}
