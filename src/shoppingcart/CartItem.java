package shoppingcart;

/***
 * 某种商品：商品id唯一标识，商品数量表示当前种类商品的数量；
 */
public class CartItem {
  private String name;
  private String id;
  private String desc;
  private int quantity;
  private double price;

  public CartItem(String id, String name, int quantity, double price) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

    public double getSum() {
    return this.price * this.quantity;
    }
}
