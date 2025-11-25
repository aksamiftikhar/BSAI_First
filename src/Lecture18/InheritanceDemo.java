package Lecture18;

import java.util.Scanner;

// Base class
class Person {
    // Dummy instance field
    private String name;

    public Person() {
        this.name = "Unknown Person";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return "Person[name=" + name + "]";
    }

    public String toString() {
        return getInfo();
    }
}

// Person -> Student
class Student extends Person {
    // Dummy instance field for Student
    private String major;

    public Student() {
        super();
        this.major = "Undeclared";
    }

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String getInfo() {
        return "Student[name=" + getName() + ", major=" + major + "]";
    }
}

// Person -> Student -> GraduateStudent
class GraduateStudent extends Student {
    // Dummy instance field for GraduateStudent
    private String thesisTitle;

    public GraduateStudent() {
        super();
        this.thesisTitle = "Untitled Thesis";
    }

    public GraduateStudent(String name, String major, String thesisTitle) {
        super(name, major);
        this.thesisTitle = thesisTitle;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    @Override
    public String getInfo() {
        return "GraduateStudent[name=" + getName()
                + ", major=" + getMajor()
                + ", thesisTitle=" + thesisTitle + "]";
    }
}

// Person -> Employee
class Employee extends Person {
    // Dummy instance field for Employee
    private double salary;

    public Employee() {
        super();
        this.salary = 0.0;
    }

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getInfo() {
        return "Employee[name=" + getName() + ", salary=" + salary + "]";
    }
}

// Person -> Employee -> Faculty
class Faculty extends Employee {
    // Dummy instance field for Faculty
    private String rank; // e.g. "Assistant Professor"

    public Faculty() {
        super();
        this.rank = "Lecturer";
    }

    public Faculty(String name, double salary, String rank) {
        super(name, salary);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String getInfo() {
        return "Faculty[name=" + getName()
                + ", salary=" + getSalary()
                + ", rank=" + rank + "]";
    }
}

// Person -> Employee -> Staff
class Staff extends Employee {
    // Dummy instance field for Staff
    private String jobTitle;

    public Staff() {
        super();
        this.jobTitle = "Staff Member";
    }

    public Staff(String name, double salary, String jobTitle) {
        super(name, salary);
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String getInfo() {
        return "Staff[name=" + getName()
                + ", salary=" + getSalary()
                + ", jobTitle=" + jobTitle + "]";
    }
}

// Test class with main
public class InheritanceDemo {
    public static void main(String[] args) {
//        Person p = new Person("Ali");
//        Student s = new Student("Ahmed", "CS");
//        GraduateStudent gs = new GraduateStudent("Sara", "AI", "Deep Learning for Urdu");
//        Employee e = new Employee("Bilal", 85000);
//        Faculty f = new Faculty("Dr. Khan", 200000, "Associate Professor");
//        Staff st = new Staff("Imran", 50000, "Lab Assistant");
//
////        System.out.println(p.getInfo());
//        testGenerics(gs);
//
////        testGenerics2(s);
////        testGenerics2(gs);
//        testGenerics2(f);

        StringBuilder sb = new StringBuilder("ABCD");
        System.out.println(sb.hashCode());
        sb.append( "EFG" );
        System.out.println(sb.hashCode());

        String s = "ABCD";
        System.out.println(s.hashCode());
        s+="EFG";

        CharSequence cs = s;
        System.out.println(cs instanceof StringBuilder);
        System.out.println(s instanceof CharSequence);
        Object o = new Employee();
        System.out.println(o instanceof Person);
        Student[] sArr = new Student[2];
        sArr[0] = null;
        sArr[1] = null;
        System.out.println(sArr.getClass().isArray());


        System.out.println(s.hashCode());

        System.out.println(sb);
        System.out.println(s);

    }

    // Erasure: public static void testGenerics(Object anyType);
    public static <T> void testGenerics(T anyType)
    {
        System.out.println(anyType);
    }

    // Erasure: public static Studnet testGenerics(Student anyType);
    public static <T extends Employee> T testGenerics2(T anyType)
    {

        return null;
    }

//    public static <T super Staff> void testGenerics3(? anyType)
//    {
//
//    }



}
