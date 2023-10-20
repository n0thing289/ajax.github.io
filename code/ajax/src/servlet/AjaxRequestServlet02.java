package servlet;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.annotation.*;

@WebServlet({"/ajaxrequest02"})
public class AjaxRequestServlet02 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收到了请求，回复前端
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取前端参数
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        out.println("<font color='red'>username = " + username + "<br>password = " + password + "<br>next station ：下一站天后</font>");
    }
}
