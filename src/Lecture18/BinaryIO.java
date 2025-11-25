import java.io.*;

public class BinaryIO {
    public static void main(String[] args) throws IOException {

        // ----- WRITE TO FILE -----
        try (
            // Create an output stream to the file
            FileOutputStream output = new FileOutputStream("temp.dat");
        ) {
            // Output values to the file
            for (int i = 1; i <= 1000; i *= 2) {
                output.write(i);
            }
        }

        // ----- READ FROM FILE -----
        try (
            // Create an input stream for the file
            FileInputStream input = new FileInputStream("temp.dat");
        ) {
            // Read values from the file
            int value;
            while ((value = input.read()) != -1) {
                System.out.print(value + " ");
            }
        }
    }
}
