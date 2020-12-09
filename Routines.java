import java.util.Scanner;

public class Routines {
 //--------------------------------------------------------------------------------------------------------------
 public static int getIntWithinRange(String prompt, int min, int max) {
  Scanner keyboard = new Scanner(System.in);
  System.out.println(prompt);
  String number = keyboard.nextLine();
  
  while(!isInteger(number) || Integer.valueOf(number) < min || Integer.valueOf(number) > max) {
   System.out.println("Sorry, '"+number+"' is not a value between "+min+" and "+max+".");
   System.out.println(prompt);
   number = keyboard.nextLine();
  }
  return Integer.valueOf(number);
 }
 //--------------------------------------------------------------------------------------------------------------
 public static String getString(String prompt){
   Scanner keyboard = new Scanner(System.in);
   System.out.println(prompt);
   return keyboard.nextLine();
 }
 //--------------------------------------------------------------------------------------------------------------
 public static double getDouble(String prompt){
   Scanner keyboard = new Scanner(System.in);
   System.out.println(prompt);
   String number = keyboard.nextLine();
   
   while(!isDouble(number)){
     System.out.println("Sorry, '" +number+"' is not a double.");
     System.out.println(prompt);
     number = keyboard.nextLine();    
   }
   return Double.valueOf(number);
 }
 //--------------------------------------------------------------------------------------------------------------
 private static boolean isInteger(String testCase) {
  try {Integer.parseInt(testCase); return true;}
  catch(Exception e) {return false;}
 }
 //--------------------------------------------------------------------------------------------------------------
 private static boolean isDouble(String testCase) {
  try {Double.parseDouble(testCase); return true;}
  catch(Exception e) {return false;}
 } 
 //--------------------------------------------------------------------------------------------------------------
 public static void closeScanner(){
   Scanner keyboard = new Scanner(System.in);
   keyboard.close();
 }
}
