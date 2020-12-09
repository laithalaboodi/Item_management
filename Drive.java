import java.io.*;
import java.util.*;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;

public class Drive implements GlobalConstants {
  
  public static void main(String[] args) {
    ArrayList <Item> inventory = new ArrayList <Item>();
    
    fillInventoryFromFile(inventory);
    welcomeMessage();
    runMainMenu(inventory);
    endingMessage();
  }
  //-----------------------------------------------------------------------------------------------------------------
  /**/
  public static void fillInventoryFromFile(ArrayList <Item> inventory){
    try{
      File fileOne = new File(FILE_NAME);
      File fileTwo = new File (FILE_NAME_TWO);
      File fileThree = new File(FILE_NAME_THREE);
      File fileFour = new File(FILE_NAME_FOUR);
   
      Scanner inventoryNumbers = new Scanner(fileOne);
      Scanner inventoryDescriptions = new Scanner(fileTwo);
      Scanner priceNumber = new Scanner(fileThree);
      Scanner totalQuantity = new Scanner(fileFour);
      while(inventoryNumbers.hasNext() && inventoryDescriptions.hasNextLine()){
        int itemNumber = inventoryNumbers.nextInt();
        String description = inventoryDescriptions.nextLine();
        String price = priceNumber.nextLine();
        int quantity=totalQuantity.nextInt();
        inventory.add( new Item(itemNumber, description,price,quantity) );
      }
      inventoryNumbers.close();
      inventoryDescriptions.close();
      priceNumber.close();
      totalQuantity.close();
    }catch(FileNotFoundException e){System.err.println("**Error occured importing inventory list**");}
  }
  //-----------------------------------------------------------------------------------------------------------------
  //welcome message method
  public static void welcomeMessage(){
    System.out.println(BORDER);
    System.out.println(STARTING_PROMPT);
    System.out.println(BORDER);
    System.out.println(STARTING_PROMPT_2);
    System.out.println(MESSAGE_1);
    System.out.println(BORDER);
  }
  //-----------------------------------------------------------------------------------------------------------------
  public static void displayMenuAssets(){
    System.out.println(BORDER);
    System.out.println("Main Menu");
    System.out.println(BORDER);
    System.out.println("1) Add to Inventory");
    System.out.println("2) Remove from Inventory");
    System.out.println("3) Display Inventory");
    System.out.println("4) Save and Exit");    
    System.out.println(BORDER);
  }
  //-----------------------------------------------------------------------------------------------------------------
  public static void runMainMenu(ArrayList <Item> inventory){
    displayMenuAssets();
    int menuSelection = Routines.getIntWithinRange("Enter Selection below:",QUIT, MAX_MENU_ITEM);
    
    switch (menuSelection){
      case 1: addToInventory(inventory);
      break;
      case 2: removeFromInventory(inventory);
      break;
      case 3: displayInventory(inventory);
      break;  
      case 4: writeToFiles(inventory);
      break;              
    }
    
  }
//-----------------------------------------------------------------------------------------------------------------
//getting the user input for adding an item to the inventory 
  public static void addToInventory(ArrayList <Item> inventory){
    int addedItem=0;
    // Appending the new element at the end of the list
    for (int i=0; i<MAX_INVENTORY; i++){
      addedItem=Routines.getIntWithinRange( "Enter item ID number, or enter -1 to exit:",  QUIT,  MAX_INVENTORY);
      
      while(itemNumberExists(addedItem, inventory)){
        System.err.println("****That Item Number cannot be used. It is associated with another item****");
        addedItem=Routines.getIntWithinRange( "Enter item ID number, or enter -1 to exit:",  QUIT,  MAX_INVENTORY);
      }
      
      if(addedItem != QUIT){
        inventory.add(new Item(addedItem,getNameOfItem(),getPrice(),getQuantity()));
      }
      //If user enters QUIT (sentinel value), program exits the loop
      if(addedItem == QUIT){
        i=MAX_INVENTORY+1;
      }
    }
    runMainMenu(inventory);
  }
//-----------------------------------------------------------------------------------------------------------------
  public static boolean itemNumberExists(int testCase, ArrayList <Item> inventory){
    boolean numberExists = false;
    for(int i = 0; i < inventory.size(); i++){
      if(inventory.get(i).getItemNumber() == testCase){
        numberExists = true;
      }
    }
    return numberExists;
  }
 //----------------------------------------------------------------------------------------------------------------- 
  public static String getNameOfItem(){
    String itemName= Routines.getString("Please enter the Name or Description");
    return itemName;
  }
//-----------------------------------------------------------------------------------------------------------------
  public static String getPrice(){
    String price= Routines.getString("Please enter the price");
    return price; 
  }
//-----------------------------------------------------------------------------------------------------------------
  public static int getQuantity(){
    int quantity = Routines.getIntWithinRange( "Enter quantity (0 to 500):",  0,  500);
    return quantity; 
  }
//getting the user input for remving an item from the inventory
  public static void removeFromInventory(ArrayList <Item> inventory){
    int removedItem=0;
    while (removedItem!=QUIT){
      removedItem=Routines.getIntWithinRange( "Enter item ID number to remove, or enter -1 to exit:",  QUIT,  MAX_INVENTORY);
      for(int i = 0; i < inventory.size(); i++){
        if(removedItem!=QUIT && inventory.get(i).getItemNumber() == removedItem){
          System.out.println("--You have removed Item # " + removedItem + " from the inventory--");
          inventory.remove(i);
        }
      }
    }
    runMainMenu(inventory);
  }
//-----------------------------------------------------------------------------------------------------------------
  /*Displays the ArrayList at its current state, even if data has not been written to file yet*/
  public static void displayInventory(ArrayList <Item> inventory){
    System.out.println(BORDER);
    System.out.println("Current Inventory");
    System.out.println(BORDER);
    
    for(Item i : inventory){
      System.out.println(i);
      
    }
    runMainMenu(inventory);
  }
//-----------------------------------------------------------------------------------------------------------------
  public static void writeToFiles(ArrayList <Item> inventory){
    boolean amendFileOne = false, amendFileTwo = false, amendFileThree = false, amendFileFour = false;
    try{ 
      File fileOne = new File(FILE_NAME);         //item numbers
      File fileTwo = new File (FILE_NAME_TWO);    //item descriptions
      File fileThree = new File(FILE_NAME_THREE); //price 
      File fileFour = new File(FILE_NAME_FOUR);   //quantity
      
      //if(fileOne.exists()){amendFileOne = true;}
      //if(fileTwo.exists()){amendFileTwo = true;}
      //if(fileThree.exists()){amendFileThree = true;}
     // if(fileFour.exists()){amendFileFour = true;}
      
      BufferedWriter outputFileOne = new BufferedWriter( new FileWriter(FILE_NAME, amendFileOne));
      BufferedWriter outputFileTwo = new BufferedWriter( new FileWriter(FILE_NAME_TWO, amendFileTwo));
      BufferedWriter outputFileThree = new BufferedWriter( new FileWriter(FILE_NAME_THREE, amendFileThree));
      BufferedWriter outputFileFour = new BufferedWriter( new FileWriter(FILE_NAME_FOUR, amendFileFour));
      
      for(int i = 0; i < inventory.size(); i++){
        outputFileOne.write(inventory.get(i).getItemNumber() + " ");
        outputFileTwo.write(inventory.get(i).getDescription());
        outputFileTwo.newLine();
        outputFileThree.write(inventory.get(i).getItemPrice());
        outputFileThree.newLine();
        outputFileFour.write(inventory.get(i).getItemQuantity() + " ");
      }
      
      outputFileOne.close();
      outputFileTwo.close();
      outputFileThree.close();
      outputFileFour.close();
    }catch(Exception e){System.err.println("Cannot to to files.");}
  }
//-----------------------------------------------------------------------------------------------------------------  
//ending message method
  public static void endingMessage(){
    System.out.println();
    System.out.println(BORDER);
    System.out.println(MESSAGE_2);
    System.out.println(BORDER);
  }
//------------------------------------------------------------------------------------------------------------------
  public static void writeRandomIntegers(java.io.File textFile){
    try{
      //boolean useOldFile = getIfUsingOldFile(textFile);
      // if(!useOldFile){  
      java.io.PrintWriter output = new java.io.PrintWriter(textFile);
      for(int i = 0; i < 100; i++){
        output.print((int)(Math.random() * 100) + 0+" ");
        //output.print("abc ");
      }
      output.close();
      //}
    }catch(Exception e){System.err.println("==Problem writing to file==");}
  }
//------------------------------------------------------------------------------------------------------------------
  
  
}
