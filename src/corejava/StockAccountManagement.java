package corejava;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StockAccountManagement {
   private static JSONArray stockList = new JSONArray();
   private static Scanner sc = new Scanner(System.in);

   public static void main(String[] args) {

      menu();
   }
   public static void menu()
   {
      int choice;
      System.out.println("Enter The Choice:\n" +
              "[1] Add Stock" +
              "[2] Display Report");
      choice = sc.nextInt();
      switch (choice) {
         case 1:
            addStock();
            break;
         case 2:
            displayReport();
            break;
      }
   }
   public static void addStock() {
      String name;
      int noOfShare;
      float price;
      JSONObject jsonObject = new JSONObject();
      sc.nextLine();
      System.out.println("Welcome To Add Stock Section");
      System.out.println("Enter The Stock Name");
      name = sc.nextLine();
      System.out.println("Enter the Number of SHARE");
      noOfShare = sc.nextInt();
      System.out.println("Enter the Stock Price");
      price = sc.nextFloat();
      jsonObject.put("name", name);
      jsonObject.put("noOfShares", noOfShare);
      jsonObject.put("stockPrice", price);
      stockList.add(jsonObject);
      try {
         FileWriter file = new FileWriter("src/data/stock.json");
         file.write(stockList.toJSONString());
         file.close();
      } catch (IOException e) {
// TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("added: " + jsonObject);
      menu();
   }

   public static void displayReport() {
      JSONParser parser = new JSONParser();
      try {
         JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/data/stock.json"));
         for (int i = 0; i < jsonArray.size(); i++) {
            System.out.printf("******** Stock %s ********\n", i + 1);
            JSONObject obj = (JSONObject) jsonArray.get(i);
            String name = (String) obj.get("name");
            long shares = (long) obj.get("noOfShares");
            Double price = (Double) obj.get("stockPrice");
            System.out.println("Stock Name : " + name);
            System.out.println("Number of Shares : " + shares);
            System.out.println("Stock price : " + price);
            System.out.println("Asset Value : " + price*shares);
            System.out.printf("*********************\n");

         }
      } catch (FileNotFoundException e) {
//e.printStackTrace();
         System.out.println("File Not Found");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
      menu();
   }
}