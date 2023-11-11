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

import bean.City;
import bean.Province;
import com.alibaba.fastjson.JSON;
import util.*;

@WebServlet({"/ajaxrequest13/getprovinces", "/ajaxrequest13/getcities"})
public class AjaxRequestServlet13 extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //根据url执行不同功能
        String servletPath = request.getServletPath();
        if ("/ajaxrequest13/getprovinces".equals(servletPath)) {
            System.out.println("28:getprovinces");
            getProvinces(request, response);
        } else if ("/ajaxrequest13/getcities".equals(servletPath)) {
            System.out.println("32:getcities");
            getCities(request, response);
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
            String sql = "select id,code,name,pcode from t_area where pcode is null;";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int code = rs.getInt("code");
                String name = rs.getString("name");
                int pcode = rs.getInt("pcode");
//                System.out.println(id + "-" + code + "-" + name + "-" + pcode);
                provinces.add(new Province(id, code, name, pcode));

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

    public void getCities(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取前端发送过来的pcode
        String pcode = request.getParameter("pcode");
        //查询对应的数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<City> cities = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id,code,name,pcode from t_area where pcode = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(pcode));

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int code = rs.getInt("code");
                String name = rs.getString("name");
//                int pcode = rs.getInt("pcode");
//                System.out.println(id + "-" + code + "-" + name + "-" + pcode);
                cities.add(new City(id, code, name, Integer.parseInt(pcode)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        //将数据封装成json回传前端
        String json = JSON.toJSONString(cities);
        response.getWriter().println(json);
    }
}
