package shoppingcart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletGetShoppingCart extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    if (shoppingCart == null) {
      out.println("购物车为空<br>");
      out.println("<a href=\"/displayItem.html\">继续浏览<br>");
    } else {
      out.println("显示购物车内容<br>");
      out.println("<a href=\"/displayItem.html\">继续浏览</a><br>");
      printCartItems(out, shoppingCart);
    }
    out.println("</body></html>");
    out.flush();
    out.close();
  }

    private void printCartItems(PrintWriter out, ShoppingCart shoppingCart) {
      ArrayList<CartItem> items = shoppingCart.getCartList();
      if (items != null && items.size() > 0) {
        out.println("<table><tr>");
        out.println("<td>ID</td>");
        out.println("<td>名称</td>");
        out.println("<td>数量</td>");
        out.println("<td>价格</td>");
        out.println("</tr>");
        for (CartItem cart : items) {
          out.println("<tr>");
          out.println("<td>" + cart.getId() + "</td>");
          out.println("<td>" + cart.getName() + "</td>");
          out.println("<td>" + cart.getQuantity() + "</td>");
          out.println("<td>" + cart.getPrice() + "</td>");
          out.println("</tr>");
        }
      }
      out.println("<tr><td colspan=\"4\">总计：" + shoppingCart.getTotal() + "元" + "</td></tr>");
      out.println("</table>");
    }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}
