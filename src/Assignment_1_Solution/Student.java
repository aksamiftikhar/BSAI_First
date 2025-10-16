package Assignment_1_Solution;

public class Student {
    public static final int MAX_COURSES = 5;

    private final String rollNo;
    private String name;
    private String email;

    private String[] enrolledCourseCodes;
    private int enrolledCount;

    public Student(String rollNo, String name, String email) {
        this.rollNo = rollNo;
        this.name = name;
        setEmail(email);
        this.enrolledCourseCodes = new String[MAX_COURSES];
        this.enrolledCount = 0;
    }

    public static boolean isCampusEmail(String email) {
        return email != null && email.toLowerCase().endsWith("@comsats.edu.pk");
    }

    public boolean addCourse(String code) {
        if (code == null || code.trim().equals("")) return false;
        for (int i = 0; i < enrolledCount; i++) {
            if (code.equals(enrolledCourseCodes[i])) return false;
        }
        if (enrolledCount >= MAX_COURSES) return false;
        enrolledCourseCodes[enrolledCount++] = code;
        return true;
    }

    public boolean dropCourse(String code) {
        for (int i = 0; i < enrolledCount; i++) {
            if (code.equals(enrolledCourseCodes[i])) {
                for (int j = i; j < enrolledCount - 1; j++) {
                    enrolledCourseCodes[j] = enrolledCourseCodes[j + 1];
                }
                enrolledCourseCodes[--enrolledCount] = null;
                return true;
            }
        }
        return false;
    }

    public String[] getEnrolledCopy() {
        String[] copy = new String[enrolledCount];
        for (int i = 0; i < enrolledCount; i++) copy[i] = enrolledCourseCodes[i];
        return copy;
    }

    public String summary() {
        return rollNo + " | " + name + " | " + email + " | Enrolled: " + enrolledCount;
    }

    // getters/setters
    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) {
        if (isCampusEmail(email)) this.email = email;
    }
}