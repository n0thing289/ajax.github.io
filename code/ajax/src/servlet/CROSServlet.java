package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
public class CROSServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        //1. 设置响应头

        //2. 利用<script></script>的src属性可用跨域,
        // 那么在src中写一个servlet的地址, src=http://localhost:8080/ajax/a/ajax04?fun=sayHello
        // 然后servlet向它响应一段js代码, response.getWriter().print(request.getParameter("fun") + "({"name":"jack"})")
        // 这样这个标签里面就有js代码并且会执行了
        //小结. 利用<script> src属性的的特点: 不刷新, 没有同源策略.
        //返回的字符串会放入script标签内部, 也就是js代码,会进行解释执行
    }
}
