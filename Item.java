/**
 * Auto Generated Java Class.
 */
public class Item {
  
  private int itemNumber;
  private String description;
  private String itemPrice;
  private int itemQuantity;
  
  public Item() {
    itemNumber = 0;
    description = "Generic Item";
    itemPrice = "$ Temp";
    itemQuantity= 0;
  }
  public Item(int item , String info, String price, int quantity) { 
    itemNumber = item;
    description = info;
    itemPrice = price;
    itemQuantity= quantity;
  }
  
  public int getItemNumber(){
    return itemNumber;
  }
  
  public void setItemNumber(int item){
    itemNumber = item;
  }
  
  public String getDescription(){
    return description;
  }
  
  public void setDescrption(String info){
    description = info;
  }
  
  public String getItemPrice(){
    return itemPrice;
  }
  
  public void setItemPrice(String price){
    itemPrice=price;
  }
  
  public int getItemQuantity(){
    return itemQuantity;
  }
  
  public void setItemQuantity(int quantity){
    itemQuantity=quantity;
  }
    
  public String toString(){
    return "Item ID Number  : "  + itemNumber    + "\n" +
           "Description     : "  + description   + "\n" +
           "Price           : "  + itemPrice     + "\n" +
           "Quantity        : "  + itemQuantity  + "\n" ;
  }
}
