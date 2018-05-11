package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletSetCookie extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    String output = null;
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (username != null && !username.isEmpty()) {
      Cookie cookie1 = new Cookie("username", username);
      cookie1.setMaxAge(24 * 60 * 60 *30);

      Cookie cookie2 = new Cookie("password", password);
      cookie2.setMaxAge(24 * 60 * 60 *30);

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Cookie cookie3 = new Cookie("lastTime", sdf.format(new Date()));
      cookie3.setMaxAge(24 * 60 * 60 *30);
      response.addCookie(cookie1);
      response.addCookie(cookie2);
      response.addCookie(cookie3);
      output = "成功写入cookie。<a href = \"/ServletGetCookie\">查看cookie</a>";
    } else {
      output = "用户名为空，请重新输入.<br><a href = \"/cookieInput.html\">重新登陆</a>";
    }
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head>set cookie</head><body>");
    out.println(output);
    out.println("</body></html>");
    out.flush();
    out.close();
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);

  }
}
