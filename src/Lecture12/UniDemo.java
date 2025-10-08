package Lecture12;// Single-file demo: All classes in one file, using ONLY ArrayList (no Set/Map/HashSet).
// Relationships are explained in comments right where they are implemented.
// Excluded: Notifier/Delegation-related classes (per request).
// To run: javac UniDemo.java && java UniDemo

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/* ---------- Simple value objects ---------- */

// Value object: StudentId
final class StudentId {
    private final String value;
    public StudentId(String value) { this.value = Objects.requireNonNull(value); }
    @Override public String toString() { return value; }
}

// Value object: CourseCode
final class CourseCode {
    private final String code;
    public CourseCode(String code) { this.code = Objects.requireNonNull(code); }
    public String value() { return code; }
    @Override public String toString() { return code; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseCode)) return false;
        CourseCode other = (CourseCode) o;
        return code.equals(other.code);
    }
    @Override public int hashCode() { return code.hashCode(); }
}

/* ---------- Basic entity ---------- */

class Address {
    private String line1, city;
    public Address(String line1, String city) { this.line1 = line1; this.city = city; }
    @Override public String toString() { return line1 + ", " + city; }
}

/* ---------- Student with multiple associations ---------- */

class Student {
    private final StudentId id;
    private String name;

    // Association (0..1, unidirectional): Student "knows" Address, but Address does not know Student.
    // Multiplicity 0..1 is modeled by a nullable field.
    private Address address; // (0..1 Association: Student → Address, unidirectional)

    // Association (many-to-many, bidirectional) with Course:
    // Student keeps a collection of Courses and Course keeps a collection of Students.
    // Using ArrayList; we guard duplicates manually with contains checks.
    private final ArrayList<Course> courses = new ArrayList<>(); // (* Association: Student ↔ Course)

    // Reflexive Association (0..1) within the same class:
    // A Student may have a mentor who is also a Student.
    private Student mentor; // (0..1 Reflexive Association: Student → Student)

    public Student(StudentId id, String name) {
        this.id = id; this.name = name;
    }

    public void setAddress(Address address) { this.address = address; }
    public void setMentor(Student mentor) { this.mentor = mentor; }

    // Package-private helpers to keep bidirectional association in sync
    void linkCourse(Course c) { if (!courses.contains(c)) courses.add(c); }
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
    private final CourseCode code;
    private String title;

    // Association (many-to-many, bidirectional): Course ↔ Student
    private final ArrayList<Student> enrolled = new ArrayList<>(); // (* Association list)

    // Composition (filled diamond semantics): Course "owns" Module; Modules cannot exist without Course.
    // Lifetime bound: if Course is destroyed, its Modules are destroyed with it.
    private final ArrayList<Module> modules = new ArrayList<>(); // (1..* Composition: Course ●─ Module)

    public Course(CourseCode code, String title) {
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

    public CourseCode code() { return code; }

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

/* ---------- University with qualified association to Courses and aggregation of Departments ---------- */

class University {
    private final String name;

    // Qualified Association: Instead of a Map, we use an ArrayList and provide a finder by qualifier (CourseCode).
    // The "qualifier" is the CourseCode used in findCourse(...).
    private final ArrayList<Course> courses = new ArrayList<>(); // (Qualified Association by CourseCode)

    // Aggregation: University aggregates Departments; departments live independently.
    private final ArrayList<Department> departments = new ArrayList<>(); // (Aggregation: University ◇─ Department)

    public University(String name) { this.name = name; }

    public void addCourse(Course c) {
        // Avoid duplicates by CourseCode
        if (findCourse(c.code()) == null) {
            courses.add(c);
        }
    }

    public Course findCourse(CourseCode code) {
        // Qualified lookup simulated by iterating the list
        for (Course c : courses) {
            if (c.code().equals(code)) return c;
        }
        return null;
    }

    public void addDepartment(Department d) {
        if (!departments.contains(d)) departments.add(d);
    }
}

/* ---------- Stateless helper used as a Dependency ("uses") ---------- */

class TranscriptExporter {
    // Dependency use-case: a stateless helper used on-demand inside a method (no field stored).
    public void export(Student s, OutputStream out) {
        String payload = "Transcript for " + s.toString() + " generated on " + LocalDate.now() + "\n";
        try {
            out.write(payload.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Export failed", e);
        }
    }
}

/* ---------- Service that demonstrates Dependency ("uses") ---------- */

class StudentService {
    // Dependency ("uses"): create and use TranscriptExporter only for this method call.
    public void downloadTranscript(Student s, OutputStream out) {
        new TranscriptExporter().export(s, out);  // short-lived helper → Dependency
    }
}

/* ---------- Single public class with main() to demo everything ---------- */

public class UniDemo {
    public static void main(String[] args) {
        // University with Qualified Association to Courses (via findCourse by CourseCode)
        University uni = new University("COMSATS");
        Course oop = new Course(new CourseCode("CS-201"), "Object-Oriented Programming");
        oop.addModule("Encapsulation & Classes", 6);         // Composition: Course ●─ Module
        oop.addModule("Inheritance & Polymorphism", 8);
        uni.addCourse(oop);

        // Students and Associations
        Student aisha = new Student(new StudentId("S-1001"), "Aisha");
        aisha.setAddress(new Address("F-Block", "Lahore"));  // 0..1 Association (unidirectional)

        Student bilal = new Student(new StudentId("S-1002"), "Bilal");
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

        // Dependency ("uses") example
        StudentService service = new StudentService();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        service.downloadTranscript(aisha, out);              // uses TranscriptExporter once
        System.out.println(out);                             // show transcript text

        // Qualified lookup demo (via ArrayList scan)
        Course found = uni.findCourse(new CourseCode("CS-201"));
        System.out.println("Found course: " + (found != null ? found.code() : "none"));
    }
}
