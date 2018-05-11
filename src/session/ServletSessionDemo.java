package session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletSessionDemo extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    Integer counter = (Integer) session.getAttribute("counter");
    if (counter == null) {
      counter = new Integer(1);
    } else {
      counter = new Integer(counter.intValue() + 1);
    }
    session.setAttribute("counter", counter);
    session.setMaxInactiveInterval(3600);
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("你已经访问了" + counter + "次");
    if (session.isNew()) {
      out.println("这是一个新建的session<br>");
    } else {
      out.println("这不是一个新建的session<br>");
    }
    Enumeration enumer = session.getAttributeNames();
    String name = null;
    while (enumer.hasMoreElements()) {
      name = (String) enumer.nextElement();
      out.println("session name:" + name);
      out.println("session value:" + session.getAttribute(name) + "<br>");
    }
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
