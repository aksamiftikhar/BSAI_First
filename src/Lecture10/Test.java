package Lecture10;
import static java.lang.Math.*;
//import static java.lang.Math.sqrt;

public class Test {
    int x;

    final int f;
    {

        f = 100;
        System.out.println("f init in non-static init block = " + f);
    }
    public Test()
    {
        System.out.println("Test constructor");
        System.out.println(f);
    }
    public Test(int x)
    {

    }
    private static int myStatic;
    public int getMyStatic() {
        return myStatic;
    }
    public String toString() {
        return "X is : " + x;
    }

    public static void main(String[] args) {
        Test t1 = new Test();
//        t1.x = 30;

        Test t2 = new Test();

//        System.out.println(Test.myStatic);
//        System.out.println(t1);
//
//        String s1 = t1.toString();
//
//        double f = sqrt(3);
//        double d = round(3.15);

    }

}

class Test2
{
    public Test2()
    {
        Test t1 = new Test();


    }
}

