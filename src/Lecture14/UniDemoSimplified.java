package Lecture14;


// Relationships are explained in comments right where they are implemented.

import java.util.ArrayList;

/* ---------- Basic entity ---------- */

class Address {
    private String line1, city;
    public Address(String line1, String city) { this.line1 = line1; this.city = city; }
    @Override public String toString() { return line1 + ", " + city; }
}

/* ---------- Student with multiple associations ---------- */

class Student {
    private final String id;   // Using plain String (was StudentId)
    private String name;

    // Association (0..1, unidirectional): Student "knows" Address, but Address does not know Student.
    // Multiplicity 0..1 is modeled by a nullable field.
    private Address address; // (0..1 Association: Student → Address, unidirectional)
//    public void printDocument(Printer p, Document d)
//    {
//        p.print(d);
//    }
    // Association (many-to-many, bidirectional) with Course:
    // Student keeps a collection of Courses and Course keeps a collection of Students.
    // Using ArrayList; we guard duplicates manually with contains checks.
    private final ArrayList<Course> courses = new ArrayList<>(); // (* Association: Student ↔ Course)

    // Reflexive Association (0..1) within the same class:
    // A Student may have a mentor who is also a Student.
    private Student mentor; // (0..1 Reflexive Association: Student → Student)

    public Student(String id, String name) {
        this.id = id; this.name = name;
    }


    public void setAddress(Address address) { this.address = address; }
    public void setMentor(Student mentor) { this.mentor = mentor; }

    // Package-private helpers to keep bidirectional association in sync
    void linkCourse(Course c) {
        if (!courses.contains(c)) courses.add(c);
//        if(this.courses.size() == 0) {courses.add(c);}
//        else { return;
//             //throw new IllegalAccessException("course already registered");}
//        }
    }
    void unlinkCourse(Course c) { courses.remove(c); }

    @Override public String toString() { return "Student(" + id + ", " + name + ")"; }
}

/* ---------- Professor with reflexive association ---------- */

class Professor {
    private String name;

    // Reflexive Association (0..1): a Professor can have a manager who is also a Professor.
    private Professor manager; // (0..1 Reflexive Association: Professor → Professor)

    public Professor(String name) { this.name = name; }
    public void setManager(Professor manager) { this.manager = manager; }
    @Override public String toString() { return "Prof(" + name + ")"; }
}

/* ---------- Course with composition (owns Module) and bidirectional association to Student ---------- */

class Course {
    private final String code;   // Using plain String (was CourseCode)
    private String title;

    // Association (many-to-many, bidirectional): Course ↔ Student
    private final ArrayList<Student> enrolled = new ArrayList<>(); // (* Association list)

    // Composition (filled diamond semantics): Course "owns" Module; Modules cannot exist without Course.
    // Lifetime bound: if Course is destroyed, its Modules are destroyed with it.
    private final ArrayList<Module> modules = new ArrayList<>(); // (1..* Composition: Course ●─ Module)

    public Course(String code, String title) {
        this.code = code; this.title = title;
    }

    public void addModule(String name, int hours) {
        // Part created/owned by the whole → Composition
        modules.add(new Module(this, name, hours));
    }

    public void enroll(Student s) {
        // Maintain bidirectional association by adding on both sides (guard duplicates).
        if (!enrolled.contains(s)) {
            enrolled.add(s);                 // Course → Student association
            s.linkCourse(this);              // Student → Course association
        }
    }

    public void drop(Student s) {
        enrolled.remove(s);                  // remove link from Course → Student
        s.unlinkCourse(this);                // remove link from Student → Course
    }

    public String code() { return code; }

    /* ----- composition part type ----- */
    static final class Module {
        private final Course owner;           // back reference ties lifetime to Course
        private final String name;
        private final int contactHours;
        private Module(Course owner, String name, int hours) {
            this.owner = owner; this.name = name; this.contactHours = hours;
        }
        @Override public String toString() { return name + " (" + contactHours + "h)"; }
    }
}

/* ---------- Department with aggregation of Professors ---------- */

class Department {
    private final String name;

    // Aggregation (hollow diamond semantics): Department has Professors,
    // but Professors are independent and can be moved or exist elsewhere.
    private final ArrayList<Professor> staff = new ArrayList<>(); // (* Aggregation: Department ◇─ Professor)

    public Department(String name) { this.name = name; }

    public void hire(Professor p) {
        if (!staff.contains(p)) staff.add(p);        // add to "whole" without owning lifetime
    }
    public void release(Professor p) {
        staff.remove(p);                              // removing does not destroy the Professor
    }

    @Override public String toString() { return "Dept(" + name + ")"; }
}

/* ---------- University with aggregation of Departments and a simple association to Courses ---------- */

class University {
    private final String name;

    // Simple Association (0..*): University holds Courses without qualification or keyed lookup.
    private final ArrayList<Course> courses = new ArrayList<>(); // (0..* Association: University → Course)

    // Aggregation: University aggregates Departments; departments live independently.
    private final ArrayList<Department> departments = new ArrayList<>(); // (Aggregation: University ◇─ Department)

    public University(String name) { this.name = name; }

    public void addCourse(Course c) {
        // Avoid duplicate object entries (no qualified/keyed lookup).
        if (!courses.contains(c)) {
            courses.add(c);
        }
    }

    public void addDepartment(Department d) {
        if (!departments.contains(d)) departments.add(d);
    }
}

/* ---------- Single public class with main() to demo everything ---------- */

public class UniDemoSimplified {
    public static void main(String[] args) {
        // University with a simple association to Courses (no qualified association)
        University uni = new University("COMSATS");
        Course oop = new Course("CS-201", "Object-Oriented Programming");
        oop.addModule("Encapsulation & Classes", 6);         // Composition: Course ●─ Module
        oop.addModule("Inheritance & Polymorphism", 8);
        uni.addCourse(oop);

        // Students and Associations
        Student aisha = new Student("S-1001", "Aisha");      // String id (was StudentId)
        aisha.setAddress(new Address("F-Block", "Lahore"));  // 0..1 Association (unidirectional)

        Student bilal = new Student("S-1002", "Bilal");      // String id (was StudentId)
        bilal.setMentor(aisha);                              // Reflexive Association (Student → Student, 0..1)

        // Bidirectional many-to-many Association: Course ↔ Student (using ArrayList on both sides)
        oop.enroll(aisha);
        oop.enroll(bilal);

        // Department Aggregation of Professors
        Department cs = new Department("Computer Science");
        Professor profKhan = new Professor("Dr. Khan");
        cs.hire(profKhan);                                   // Aggregation: Department ◇─ Professor
        Professor hod = new Professor("Dr. Fatima");
        profKhan.setManager(hod);                            // Reflexive Association (Professor → Professor, 0..1)

        // (Dependency-related classes and calls removed per request)
        // (Qualified association lookup removed per request)
    }
}
