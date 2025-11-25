import java.io.PrintWriter;
import java.util.Scanner;

public class TextIO {
  public static void main(String[] args) {
    java.io.File file = new java.io.File("quadrative.txt");
    // System.out.println("Does it exist? " + file.exists());
    // System.out.println("The file has " + file.length() + " bytes");
    // System.out.println("Can it be read? " + file.canRead());
    // System.out.println("Can it be written? " + file.canWrite());
    // System.out.println("Is it a directory? " + file.isDirectory());
    // System.out.println("Is it a file? " + file.isFile());
    // System.out.println("Is it absolute? " + file.isAbsolute());
    // System.out.println("Is it hidden? " + file.isHidden());
    // System.out.println("Absolute path is " +
    //   file.getAbsolutePath());
    // System.out.println("Last modified on " +
    //   new java.util.Date(file.lastModified()));

    try
    {
      PrintWriter writer = new PrintWriter(file);

      writer.printf("Hello, World!\n"); 
      
      int a, b, c;
      a = 5;
      b = 10;
      c = a + b;
      writer.printf("a is \n%d\n", a);
      writer.printf("b is \n%d\n", b);
      writer.printf("The sum of a and b is:\n%d\n", c);

      writer.close();
      System.out.println("Data written to file.");
    } catch (Exception e) {
      System.out.println("Could not write to file.");
      return;
    }

    try {
      Scanner sc = new Scanner(file);

      System.out.println("The data in the file is: ");
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        System.out.println(line);
      }
      sc.close();

    } catch (Exception e) {
      System.out.println("File not found.");
      return;
    }

    try(Scanner sc = new Scanner(file)) {
      int x, y, z;
      System.out.println("reading integers from the file:");
      String waste = "";
      waste = sc.nextLine();
      waste = sc.nextLine();
      System.out.println(waste);
      x = sc.nextInt();
      System.out.printf("Read x = %d%n", x);
      waste = sc.nextLine();
      waste = sc.nextLine();
      System.out.printf("waste: %s%n", waste);
      y = sc.nextInt();
      System.out.printf("Read y = %d%n", y);

      waste = sc.nextLine();
      waste = sc.nextLine();
      System.out.printf("waste: %s%n", waste);
      
      z = sc.nextInt();
      System.out.printf("Read z = %d%n", z);
      System.out.printf("x = %d, y = %d, z = %d%n", x, y, z);
    } catch (Exception e) {
      System.out.println("File not found.");
      return;
    }
    
  }
}
