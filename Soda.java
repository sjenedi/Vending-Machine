import java.io.IOException;
import java.io.PrintWriter;

public class Soda extends Item {
  private boolean caffeine;
  
  
  public Soda() {
      super();
      caffeine = false;
  }
  
  public Soda(String name, double price, int quantity, boolean caffeine) {

   super(name, price, quantity);
   this.caffeine = caffeine;

}
  
  public boolean getCaffeine()
  {
    return caffeine;
  }


  public void setCaffeine(boolean caffeine)
   {
     this.caffeine = caffeine;
   }
  
  @Override
  public void display() {
      super.display();
    if(caffeine == true)
      System.out.print("caffeine");
    else
      System.out.print("No caffeine");   
  }
  
  @Override
  public String toString(){

  return "Soda, "+ super.toString() +"," +caffeine;

  }
  

  public void store(PrintWriter outFile) throws IOException{

    outFile.write(toString());
   

  }

}
