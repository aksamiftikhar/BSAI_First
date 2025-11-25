

import java.util.Scanner; 
import java.io.FileNotFoundException;

public class ReadData {
  public static void main(String[] args)  {
    // Create a File instance
    java.io.File file = new java.io.File("scores.txt");

    try ( Scanner input = new Scanner(file)) // try with resources
    {
    // Read data from a file
    while (input.hasNext()) {
      String firstName = input.next();
      String mi = input.next();
      String lastName = input.next();
      int score = input.nextInt();
      System.out.println(
        firstName + " " + mi + " " + lastName + " " + score);
    }
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("File not found: " + ex.getMessage());
    }


    // Close the file
  }
}
