package servlet;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.annotation.*;

@WebServlet({"/ajaxrequest01"})
public class AjaxRequestServlet01 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AjaxRequest已收到");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("朱志成大渣男! 如果你不爱我,就把我的心还我,你用爱换走青春,我还留下了什么~");
    }
}
