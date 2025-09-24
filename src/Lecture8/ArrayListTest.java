package Lecture8;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        ArrayList<Student>  students = new ArrayList<Student>();
        Student s1 = new Student();
        s1.name = "ali";

        students.add(s1);

        Student s = students.get(0);
        s.display();

//        INT x = new INT();
//        x.setData(20);
//        ArrayList<INT> al = new ArrayList<INT>();
//        al.add(new INT());

        Integer i = 30;
        int x = i;
        ArrayList<Integer> alInt = new ArrayList<>();

        alInt.add(i);
        alInt.add(3);

        int x1 = alInt.get(0);
    }

}
class INT
{
    private int data;
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

}
class Student
{
    String name;
    public void display()
    {
        System.out.println(name);
    }
}
