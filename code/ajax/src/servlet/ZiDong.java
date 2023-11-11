package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.Question;
import com.alibaba.fastjson.JSON;
import util.*;

@WebServlet({"/zidongrequest"})
public class ZiDong extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取前端传回来的关键字
        String keyword = request.getParameter("keyword");
        System.out.println("获得的前端关键字: " + keyword);

        //查询数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> questionList = new ArrayList<>();
        int queryRows = 0;
        if(!("".equals(keyword))){
            try {
                conn = JDBCUtil.getConnection();
                String sql = "select id,question from t_questions where question like ?";
                ps = conn.prepareStatement(sql);

                ps.setString(1, keyword + "%");// 细节不建议%开头,这样会让字段失效,查询效率降低

                rs = ps.executeQuery();
                while (rs.next()) {
                    queryRows++;
                    int id = rs.getInt("id");
                    String question = rs.getString("question");
//                System.out.println("id=" + id + ", question=" + question);
                    //添加到List中
                    questionList.add(new Question(id, question));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtil.close(rs, ps, conn);
            }
        }
        //封装数据成json
        String questionJSON = JSON.toJSONString(questionList);

        //响应回前端
        response.getWriter().print(questionJSON);
    }
}
