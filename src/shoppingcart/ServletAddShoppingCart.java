package shoppingcart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * shoppingChart对象作为session的属性保存在session中
 */
public class ServletAddShoppingCart extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();
    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");
    if (shoppingCart == null) {
      shoppingCart = new ShoppingCart();
      session.setAttribute("shoppingcart", shoppingCart);
    }
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String quantity = request.getParameter("quantity");
    String price = request.getParameter("price");
    out.println(id + stringValidateNull(id) + name + stringValidateNull(name) + quantity + price + stringValidateNull(price));
    if (stringValidateNull(id) || stringValidateNull(name) || stringValidateNull(price)) {
      out.println("参数有空");
      printError(request, response);
      return;
    }
    try {
      if (stringValidateNull(quantity)) {
        shoppingCart.addCartItem(new CartItem(id, name, 1,
            Double.parseDouble(price)));
      } else {
        shoppingCart.addCartItem(new CartItem(id, name,
            Integer.parseInt(quantity),
            Double.parseDouble(price)));
      }
    } catch (NumberFormatException e) {
      out.println("数量有问题");
      printError(request, response);
      return;
    }
    response.sendRedirect("/ServletGetShoppingCart");
  }

  private boolean stringValidateNull(String str) {
    if (str == null || str.equals("")) {
      System.out.println(str);
      return true;
    } else {
      return false;
    }
  }

  private void printError(HttpServletRequest request,
      HttpServletResponse response)
      throws IOException, ServletException{
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("缺少商品或者参数不正确，添加失败");
    out.println("<a href=\"/displayItem.html\">继续浏览<br>");
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
