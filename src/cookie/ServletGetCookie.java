package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletGetCookie extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    Cookie[] cookies = request.getCookies();
    out.println();
    for (Cookie cookie : cookies) {
      if (cookie != null) {
        if (cookie.getName().equals("username")) {
          out.println("用户名" + cookie.getValue());
        }
        if (cookie.getName().equals("password")) {
          out.println("密码：" + cookie.getValue());
        }
        if (cookie.getName().equals("lastTime")) {
          out.println("上次登陆时间" + cookie.getValue());
        }
      }
    }
    out.print("</body></html>");
    out.flush();
    out.close();

  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);

  }
}
