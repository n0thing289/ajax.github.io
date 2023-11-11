package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/try"})
public class TryAjax extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跨域解决方案1: 设置响应头允许跨域访问
        //这句话意思是允许localhost:8081端口 来请求我这里的资源
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("a域请求b域请求成功");
    }
}
