public class UniversityStudent {

    // ---------- Static (class) fields ----------
    private static String universityName = "COMSATS Lahore";
    private static int totalStudents = 0;

    // A constant (same for everyone)
    public static final int MAX_CREDITS_PER_SEM = 18;

    // ---------- Instance (non-static) fields ----------
    private final String rollNo;   // fixed for each student after construction
    private String name;
    private String email;
    private int currentCredits;

    // ---------- Constructor ----------
    public UniversityStudent(String rollNo, String name, String email) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        totalStudents++; // updating shared class state
    }

    // ---------- Instance (non-static) methods ----------
    public void enrollCourse(int credits) {
        if (credits <= 0) {
            System.out.println("Invalid number of credits.");
        } else if (currentCredits + credits > MAX_CREDITS_PER_SEM) {
            System.out.println("Cannot enroll. Exceeds semester credit limit of " + MAX_CREDITS_PER_SEM);
        } else {
            currentCredits += credits;
        }
    }

    public void dropCourse(int credits) {
        if (credits <= 0) {
            System.out.println("Invalid number of credits.");
        } else if (currentCredits - credits < 0) {
            System.out.println("Cannot drop below 0 credits.");
        } else {
            currentCredits -= credits;
        }
    }

    public String profile() {
        return "[" + rollNo + "] " + name + " | " + email + " | Credits: " + currentCredits + " | " + universityName;
    }

    // ---------- Static (class) methods ----------
    public static boolean isValidComsatsEmail(String email) {
        return email != null && email.toLowerCase().endsWith("@comsats.edu.pk");
    }

    public static void renameUniversity(String newName) {
        if (newName == null || newName.trim().equals("")) {
            System.out.println("University name cannot be empty.");
        } else {
            universityName = newName;
        }
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    // ---------- Getters/Setters ----------
    public String getRollNo() { return rollNo; } // final → no setter

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (!isValidComsatsEmail(email)) {
            System.out.println("Invalid email. Please use COMSATS email.");
        } else {
            this.email = email;
        }
    }

    // ---------- Tiny demo ----------
    public static void main(String[] args) {
        UniversityStudent s1 = new UniversityStudent("23-CS-001", "Ayesha", "ayesha@comsats.edu.pk");
        UniversityStudent s2 = new UniversityStudent("23-CS-002", "Bilal", "bilal@comsats.edu.pk");
        System.out.println(UniversityStudent.MAX_CREDITS_PER_SEM);
        System.out.println(s1.MAX_CREDITS_PER_SEM);
        s1.enrollCourse(12);
        s2.enrollCourse(15);

        System.out.println(s1.profile());
        System.out.println(s2.profile());

        System.out.println("Total students so far (static): " + UniversityStudent.getTotalStudents());

        UniversityStudent.renameUniversity("COMSATS University Islamabad (Lahore Campus)");
        System.out.println("After rename:");
        System.out.println(s1.profile());
        System.out.println(s2.profile());

        // Try invalid email
        s2.setEmail("bilal@gmail.com"); // just prints a message
    }
}
