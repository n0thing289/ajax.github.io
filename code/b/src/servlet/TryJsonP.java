package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet({"/jsonp"})
public class TryJsonP extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //ajax跨域解决方案2 使用jsonp
        response.setContentType("text/html;charset=UTF-8");
        String fun = request.getParameter("fun");
//        response.getWriter().print("alert('hi')");
        response.getWriter().print("sayHello({\"name\":\"jack\", \"age\":18, \"addr\":\"北京\"})");
    }
}
