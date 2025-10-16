package Assignment_1_Solution;

public class Course {
    public static final int MAX_CLASS_SIZE = 40;

    private final String code;
    private String title;
    private int creditHours;

    private Student[] roster;
    private int rosterCount;

    public Course(String code, String title, int creditHours) {
        this.code = code;
        this.title = title;
        this.creditHours = creditHours;
        this.roster = new Student[MAX_CLASS_SIZE];
        this.rosterCount = 0;
    }

    public boolean addStudent(Student s) {
        if (s == null) return false;
        if (rosterCount >= MAX_CLASS_SIZE) return false;
        for (int i = 0; i < rosterCount; i++) {
            if (roster[i].getRollNo().equals(s.getRollNo())) return false;
        }
        roster[rosterCount++] = s;
        return true;
    }

    public boolean removeStudentByRoll(String rollNo) {
        for (int i = 0; i < rosterCount; i++) {
            if (roster[i].getRollNo().equals(rollNo)) {
                for (int j = i; j < rosterCount - 1; j++) roster[j] = roster[j + 1];
                roster[--rosterCount] = null;
                return true;
            }
        }
        return false;
    }

    public Student findStudent(String rollNo) {
        for (int i = 0; i < rosterCount; i++) {
            if (roster[i].getRollNo().equals(rollNo)) return roster[i];
        }
        return null;
    }

    public String info() {
        return code + " | " + title + " | " + creditHours + " CH | Roster: " + rosterCount;
    }

    // getters
    public String getCode() { return code; }
    public int getCreditHours() { return creditHours; }
    public Student[] getRosterUnsafe() { return roster; }
    public int getRosterCount() { return rosterCount; }
}