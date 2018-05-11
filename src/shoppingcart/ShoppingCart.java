package shoppingcart;

import java.util.ArrayList;

/**
 * 购物车：保存CartItem对象，记录用户加入购物车的商品信息，可添加、删除商品、修改商品数量；
 */
public class ShoppingCart {

  private ArrayList<CartItem> cart;

  public ShoppingCart() {
    cart = new ArrayList<CartItem>();
  }

  public ArrayList<CartItem> getCartList() {
    return cart;
  }

  public void addCartItem(CartItem item) {
    if (item != null) {
      for (CartItem old : cart) {
        if (old.getId().equals(item.getId())) {
          old.setQuantity(old.getQuantity() + item.getQuantity());
          return;
        }
      }
      cart.add(item);
    }
  }

  public boolean deleteCartItem(String id) {
    if (id != "") {
      CartItem item = null;
      for (int i = 0; i < cart.size(); i++) {
        item = cart.get(i);
        if (item.getId().equals(id)) {
          cart.remove(i);
          return true;
        }
      }
      return false;
    }
    return false;
  }

  public double getTotal() {
    double sum = 0.0;
    for (CartItem item: cart) {
      sum += item.getSum();
    }
    return sum;
  }

}
