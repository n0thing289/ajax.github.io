package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Province;
import com.alibaba.fastjson.JSON;
import util.*;

@WebServlet({"/ajaxrequest13/getprovinces"})
public class AjaxRequestServlet13 extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String servletPath = request.getServletPath();
        //根据url执行不同功能
        if ("/ajaxrequest13/getprovinces".equals(servletPath)) {
            System.out.println("okla");
            getProvinces(request, response);
        }
    }

    /**
     * @param request
     * @param response
     * @description 获取所有的省份信息
     */
    public void getProvinces(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Province> provinces = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id,code,name,pcode from t_area where pcode = -1;";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int code = rs.getInt("code");
                String name = rs.getString("name");
                int pcode = rs.getInt("pcode");

                provinces.add(new Province(id,code,name,pcode));
//                System.out.println(id + "-" + code + "-" + name + "-" + pcode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }

        //将查询的数据封装风json，然后响应会客户端
        String json = JSON.toJSONString(provinces);
        response.getWriter().println(json);
    }
}
