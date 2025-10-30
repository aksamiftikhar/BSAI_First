package Lecture16;

import java.io.IOException;
import java.util.InputMismatchException;

public class ExceptionHandlingDemo {
    public static void CheckedUnchecked()
            throws IOException
    {
        try
        {
            int x = 10/0;
        }
        catch(InputMismatchException e)
        {

        }

    }

    public static void main(String [] args)
    {
//        CheckedUnchecked();
        try {
            try {
                int x = 10 / 0;
                System.exit(0);
            } catch (ArithmeticException e) {

                System.out.println("Arithmetic Exception");
                throw new Exception("laksdjflk");
            } finally {
                System.out.println("finally");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Arithmetic Exception");
        }
        catch(Exception e) {
            System.out.println("Exception");
        }
        finally {
            System.out.println("finally");
        }

    }
}
