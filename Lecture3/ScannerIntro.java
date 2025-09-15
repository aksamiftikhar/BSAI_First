package Lecture3;
import java.util.Scanner;

class ScannerIntro {

    public static void main(String[] args) {
//        Scanner input  = new Scanner(System.in);
//
//        int x;
//        float f;
//        String name;
//
//        System.out.println("Enter x: ");
//
//        x = input.nextInt();
//        f = input.nextFloat();
//        input.nextLine();
//        name = input.nextLine();
//
//
//        System.out.println(x);
//        System.out.println(f);
//        System.out.println(name);

        myClass.display_static();
        myClass myc = new myClass();
        myc.setX(-100);
        myc.deposit(-100);

        myc.display();
    }
}

class myClass {
    int x;

    public int getX() {
        return x;
    }
    public void setX(int x1) {
        if(x1 > 0) {
            x = x1;
        }
        else
        {
            System.out.println("Invalid Input");
        }
    }
    public void display() {
        System.out.println(x);
    }

    public void deposit(int amount) {

//        x = x + amount;

        int newBal = getX() + amount;
        setX(newBal);
    }
    public static void display_static()
    {
        System.out.println("display_static");
    }
}