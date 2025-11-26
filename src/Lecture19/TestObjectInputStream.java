
import java.io.*;

public class TestObjectInputStream {
  public static void main(String[] args)
    throws ClassNotFoundException, IOException {
    try ( // Create an input stream for file object.dat
      ObjectInputStream input =
        new ObjectInputStream(new FileInputStream("object.dat"));
    ) {
      // Read a string, double value, and object from the file
      String name = input.readUTF();
      double score = input.readDouble();
      myObject obj = (myObject)(input.readObject());
      System.out.println(name + " " + score + " " + obj);
    }
  }
}


class myObject implements Serializable
{
  int a;
  char b;
  double x;
  transient myObject2 obj2 = new myObject2(5, 'Z', 99.9);
  myObject(int a, char b, double x)
  {
    this.a = a;
    this.b = b;
    this.x = x;
  }
  public String toString()
  {
    return "a: " + a + " b: " + b + " x: " + x + " obj2: [" + obj2 + "]";
  }
}

 class myObject2
{
  int p;
  char q;
  double r;
  myObject2(int p, char q, double r)
  {
    this.p = p;
    this.q = q;
    this.r = r;
  }
}