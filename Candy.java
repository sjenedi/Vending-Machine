import java.io.IOException;
import java.io.PrintWriter;

public class  Candy extends Item {
  private boolean nuts;
  
   public Candy() {
     nuts = false;
   }

   public Candy(String name, double price, int quantity, boolean nuts) {
   super(name, price, quantity);  
     this.nuts = nuts;
   }
   
   public boolean getNuts()
   {
     return nuts;
   }


   public void setNuts(boolean nuts)
    {
      this.nuts = nuts;
    }
   
   @Override
   public void display() {
     super.display();
     if(nuts == true)
       System.out.print("nuts");
     else
       System.out.print("No nuts");
       
   }
   
   @Override
   public String toString(){

   return "Candy,"+super.toString() + "," + nuts;

   }

   public void store(PrintWriter outFile) throws IOException{

     outFile.write(toString());

   }
}
