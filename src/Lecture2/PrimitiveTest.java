package Lecture2;

public class PrimitiveTest {
    int x;
    char ch;
    float f;
    boolean bool;

    String str;

    public static void main(String[] args) {
        PrimitiveTest obj = new PrimitiveTest();
        obj.x = 1;

        PrimitiveTest obj2 = new PrimitiveTest();
        obj2.x = 2;

        PrimitiveTest obj3 = obj;
        obj.x = 3;

        System.out.println(obj.x);
        System.out.println(obj2.x);
        System.out.println(obj3.x);


        System.out.println(obj.x);
        System.out.println(obj.ch);
        System.out.println(obj.f);
        System.out.println(obj.bool);
        System.out.println(obj.str);

//        int x;
//        float f;
//
//        System.out.println(x);
    }
}





