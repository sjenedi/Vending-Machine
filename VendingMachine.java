import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.DecimalFormat;

/**
 * @author Sami Jenedi and Thanmany Lakkireddy
 * class VendingMachine simulates a simple Vending Machine
 * where items may be purchased, and item information is updated
 * and stored.
 */
public class VendingMachine
{
  private ArrayList<Item> items;

  public static void main(String[] args)
  {
    VendingMachine machine = new VendingMachine();
    try
    {
      machine.start("items.txt");
      machine.run();
      machine.stop("items.txt");
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }

  public VendingMachine()
  {
    // Assign a new empty array list to the items field
    items = new ArrayList<Item>();

  }

  /**
   * Method start loads the machine with vending machine data.
   * @param filename The name of the file storing the vending
   *                 machine data.
   * @throws IOException
   */
  public void start(String filename) throws IOException {

    File f = new File(filename);

    // Check if the filename exists. If not, inform the user that the

    // file does not exist and exit with an error code of 1.

    boolean exists = f.exists();

    if (!exists) {

    System.out.println("File : '" + filename + "' does not exist");

    System.exit(1);

    } else {

    try {

    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

    String line;

    // While there are more lines in the file, read each vending

    // machine

    // item from file into the items list

    while ((line = reader.readLine()) != null) {

    // Read the next line from the file and split into an

    // array of strings

    String[] strArr = line.split(",");

    if (strArr[0].equals("Soda")) {

    // IF the line of data read represents a Soda, build a

    // new Soda object with the name/price/quantity/caffeine

    // information and then add it to items

    Soda newSoda = new Soda(strArr[1], Double.parseDouble(strArr[2]), Integer.parseInt(strArr[3]), Boolean.parseBoolean(strArr[4]));
    

    items.add(newSoda);

    } else {

    // ELSE build a new Candy object with the

    // name/price/quantity/nuts

    // information and add it to items

    Candy newCandy = new Candy(strArr[1], Double.parseDouble(strArr[2]), Integer.parseInt(strArr[3]),Boolean.parseBoolean(strArr[4]));

    items.add(newCandy);

    }

    }

    // Close the file

    reader.close();

    } catch (FileNotFoundException e) {

    e.printStackTrace();

    } catch (IOException e) {

    e.printStackTrace();

    }

    }

    }

  /**
   * Method lookup performs a case-insensitive linear search to determine
   * if a vending machine item with the given name exists.
   * @param itemName The name of the vending machine item to find.
   * @return A reference to the item found or null if not found.
   */
  public Item lookup(String itemName)
  {
    for (Item i : items) {

      if (i.getName().equals(itemName)) {

      return i;
        }
    }
  return null;

  }

  /**
   * Method displayItems displays each item in the machine.
  */
  public void displayItems()
  {
    System.out.println("******* Items available ********");

    System.out.println("Type\tName\tPrice\tQuantity\tCAffeine/Nuts");

    for (Item i : items) {

    System.out.println(i.toString());

    }

    System.out.println();
  }

  /**
   * Method run allows a user to purchase items from the machine.
   */
  public void run()
  {

    String choice = "";

    char quit = 'n';

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Process transactions until the user quits

    do {

    displayItems();

    // Prompt for and retrieve the item into a String variable named

    // choice

    try {

    System.out.println("Enter item name");

    choice = reader.readLine().trim();

    Item item = lookup(choice);

    // If item is found, proceed with the purchase

    if (item != null) {

    int availableQunatity = item.getQuantity();

    // If there is at least one item, then process the purchase

    if (availableQunatity > 1) {

    double price = item.getPrice();

    // Prompt for and retrieve the money

    System.out.println("Please insert cash");

    int cashEntered = Integer.parseInt(reader.readLine().trim());

    // Loop until enough money is entered

    while (cashEntered < price) {

    System.out.println("Please insert more cash");

    cashEntered += Integer.parseInt(reader.readLine().trim());

    }

    // Tell the user to take the product and any remaining

    // change.

    System.out.println("Take the product and the change " + (cashEntered - price));

    // Reduce the quantity of the item in stock by 1

    item.setQuantity(availableQunatity - 1);

    } else {

    System.out.println("Item sold out.");

    }

    } else {

    System.out.println("Invalid item.");

    }

    } catch (IOException e) {

    e.printStackTrace();

    }

    // Prompt for and retrieve choice whether the user wants to quit.

    System.out.println("Do you want something else ? (Y/N)");

    try {

    quit = reader.readLine().trim().charAt(0);

    } catch (IOException e) {

    e.printStackTrace();

    }

    } while (quit != 'n' && quit != 'N');

    try {

    reader.close();

    } catch (IOException e) {

    e.printStackTrace();

    }

    } // end run

  /**
   * Method stop writes the items to file.
   * @param filename The name of the file to which the items are written.
   * @throws IOException
   */
  public void stop(String filename) throws IOException {

    FileWriter fw = new FileWriter(filename);

    for (Item i : items) {

    fw.write(i.toString() + "\n");

    }

    fw.close();

    } // end stop
} // end class
