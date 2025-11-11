package Lecture17;

import java.util.Arrays;
public class GenericsTest {
    public static <T> void printArray(T[] myArr)
    {
        for(T item : myArr)
        {
            System.out.println(item);
        }
    }
    public static <T1>  T1 printTwoArrays(T1[] myArr, T1[] myArr2)
    {
        printArray(myArr);
        for(int index = myArr2.length-1; index >= 0; index--)
            System.out.println(myArr2[index]);

        T1 var1 = null;

        return var1;
    }
//    public static <T> void testFunc(T t1, T t2)
//    {
//
//    }
    public static void main(String[] args)
    {
//        String s = "abc";
//        Integer x = 123;
//        testFunc(s, x);

        Integer[] arr = {1, 2, 3};
        String[] strArr = {"ABC", "DEF", "GHI"};

//        printArray(arr);
//        printArray(strArr);

        printTwoArrays(arr, strArr);
//
        Test t1 = new Test(10, 20);
        Test t2 = new Test(20, 30);
        Test t3 = maximum(t1, t2);
        System.out.println(t3);
//
//        if(t1.compareTo(t2) == 0)
//            System.out.println("Test t1 is equal to Test t2");
//        else if(t1.compareTo(t2) > 0)
//            System.out.println("Test t1 is greater than Test t2");
//        else
//            System.out.println("Test t1 is less than Test t2");
//        Stack<String> stack = new Stack<String>();
//        Stack<Test> stack2 = new Stack<Test>();
    }
    public static <T extends Comparable<T>> T maximum(T t1, T t2)
    {
        if(t1.compareTo(t2) > 0)
            return t1;
        else
            return t2;

    }

}

class Stack<T>
{
    T[] data;
}


class Test implements Comparable<Test>
{
    int x;
    int y;
    public String toString()
    {
        return x + ", " + y;
    }
    public Test(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Test o)
    {
        return (x+y) - (o.x+o.y);
    }
}
