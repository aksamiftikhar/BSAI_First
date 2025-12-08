package com.example.lecture23_eventhandlingjavafx.chapter15;

public class TestClass  {
    public static void main(String[] args)
    {
        myFunctional myf1 = (x, y) -> {return x + y;};
        System.out.println(myf1.myOnlyMethod(10, 20));

    }
}
interface myFunctional
{
    public int myOnlyMethod(int x, int y);
}