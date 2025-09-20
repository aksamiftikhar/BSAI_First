package Lecture6;

import java.util.Arrays;
import java.util.Scanner;
public class ArraysTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
//        int[] arr2 = new int[3];
//        int[] arr3 = new int[]{1, 2, 3};

        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i] + " ");
        }

        for(int x: arr)
        {
            System.out.println(x + " ");
            if(x==2)
                break;
        }

        String s = "some string";
        String s2 = new String("abc");
        arr[0] = 10;
        System.out.println(arr[0]);
//        System.out.println(arr[3]); // Exception

        System.out.println(arr.length);

        Person[] pArr = new Person[3];
        pArr[0] = new Person("ali");
        pArr[1] = pArr[0];
        pArr[2] = new Person("umar");
    }
}
class Person
{
    String name;

    public Person(String name)
    {
        this.name = name;
    }
}
