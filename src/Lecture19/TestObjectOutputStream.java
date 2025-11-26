
import java.io.*;

public class TestObjectOutputStream {
  public static void main(String[] args) throws IOException {
    try ( // Create an output stream for file object.dat
      ObjectOutputStream output =
        new ObjectOutputStream(new FileOutputStream("object.dat"));
    ) {
      // Write a string, double value, and object to the file
      output.writeUTF("John");
      output.writeDouble(85.5);
      // output.writeObject(new java.util.Date());
      // output.writeObject(new myObject(10, 'A', 25.5));
      // output.writeObject(new myObject(10, 'A', 25.5));
      // output.writeObject(new myObject(10, 'A', 25.5));
      myObject obj = new myObject(20, 'B', 50.5);
      output.writeObject(obj);
      // output.writeObject(obj);
      // output.writeObject(obj);
      // output.writeObject(obj);
      // output.writeObject(obj);
      
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