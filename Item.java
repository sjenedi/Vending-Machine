
public abstract class Item {

  private String name;
  private double price;
  private int quantity;

  public String getName()
  {
    return name;
  }

 public double getPrice()
  {
    return price;
  }

 public int getQuantity()
 {
   return quantity;
 }


 public void setName(String name)
  {
    this.name = name;
  }

 public void setPrice(double price)
  {
     this.price = price;
     if (price < 0)
       this.price = 0;
  }

 public void setQuantity(int quantity)
 {
    this.quantity = quantity;
    if (quantity < 0)
      this.quantity = 0;
 }
  
  public Item() {
    setName(" ");
    setPrice(0);
    setQuantity(0);
  }
  
  public Item(String name, double price, int quantity) {
    setName(name);
    setPrice(price);
    setQuantity(quantity);
  }
  
  public void reduce(int amount) {
    if (amount > 0 && amount <= quantity)
      quantity = quantity - amount; 
  }
  
  public void display() {
  System.out.print(name + ","+String.format("$%1.1f", price) + ", "+quantity+" remaining");
  }
  
  @Override
  public String toString() {
      return name + "," + String.format("%1.2f", price)  + " " + quantity ;
             
  }
  
}
