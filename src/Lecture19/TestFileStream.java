
import java.io.*;

public class TestFileStream {
  public static void main(String[] args) throws IOException {
    try (
      // Create an output stream to the file
      FileOutputStream output = new FileOutputStream("temp.dat");
    ) {
      byte[] bytes = {1, 3, 5, 10, 15, 20, 25, 30, 35, 40};
      output.write(bytes);
      // Output values to the file
      // for (int i = 1; i <= 10; i++)
      //   output.write(i);
    }

    try (
      // Create an input stream for the file
      FileInputStream input = new FileInputStream("temp.dat");
    ) {
      // Read values from the file
      int value;
      // while ((value = input.read()) != -1)
      //   System.out.print(value + " ");
      byte[] bytes = new byte[10];
      input.read(bytes);
      for (int i = 0; i < bytes.length; i++)
        System.out.print(bytes[i] + " ");

    }
  }
}
