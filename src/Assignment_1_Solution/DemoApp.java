package Assignment_1_Solution;

public class DemoApp {
    public static void main(String[] args) {
        Course[] courses = new Course[] {
            new Course("CS101", "Programming I", 3),
            new Course("CS102", "OOP with Java", 4),
            new Course("CS103", "Discrete Structures", 3)
        };

        Student[] students = new Student[] {
            new Student("23-CS-001", "Ayesha", "ayesha@comsats.edu.pk"),
            new Student("23-CS-002", "Bilal",  "bilal@comsats.edu.pk"),
            new Student("23-CS-003", "Fatima", "fatima@comsats.edu.pk")
        };

        // Enroll personal course codes (does NOT auto-sync to course rosters by design)
        students[0].addCourse("CS101");
        students[0].addCourse("CS102");
        students[1].addCourse("CS102");
        students[2].addCourse("CS103");

        // Only CS102 roster is filled (to show independence from student enrollments)
        courses[1].addStudent(students[0]);
        courses[1].addStudent(students[1]);

        System.out.println("--- Students ---");
        for (Student s : students)
            System.out.println(s.summary());

        System.out.println("\n--- Courses ---");
        for (Course c : courses)
            System.out.println(c.info());

        Classroom room = new Classroom("CS-BLOCK-101", 3, 4);
        room.assignSeat(0, 1, students[0]);
        room.assignSeat(1, 2, students[1]);
        room.assignSeat(2, 3, students[2]);

        System.out.println("\nOccupied seats: " + room.countOccupied());
        System.out.println("Locate 23-CS-002: " + room.locate("23-CS-002"));

        int[][] heatmap = new int[3][4];
        Student[][] seatingRef = room.getSeatingUnsafe();
        for (int r = 0; r < heatmap.length; r++) {
            for (int c = 0; c < heatmap[r].length; c++) {
                heatmap[r][c] = (seatingRef[r][c] == null) ? 0 : 1;
            }
        }

        System.out.println("\nHeatmap (enhanced-for read):");
        for (int[] row : heatmap) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }

        Student[] aliasRow0 = seatingRef[0];
        aliasRow0[1] = null;
        System.out.println("\nAfter aliasRow0[1]=null, occupied seats: " + room.countOccupied());

        aliasRow0 = new Student[4];
        System.out.println("Reassign aliasRow0: occupied seats still: " + room.countOccupied());

        int[][] seatCodesCopy = room.copySeatCodes();
        if (seatCodesCopy.length > 0 && seatCodesCopy[0].length > 0) seatCodesCopy[0][0] = 9999;
        System.out.println("Original seat code [0][0]: " + room.getSeatCodesUnsafe()[0][0]);
        System.out.println("Copy seat code [0][0]    : " + seatCodesCopy[0][0]);

        room.reconfigureJagged(new int[]{2, 5, 3});
        Student[][] jag = room.getSeatingUnsafe();
        for (int r = 0; r < jag.length; r++) {
            for (int c = 0; c < jag[r].length; c++) {
                if ((r + c) % 2 == 0) jag[r][c] = students[0];
            }
        }

        System.out.println("\nJagged seating (enhanced-for read):");
        for (Student[] row : jag) {
            for (Student s : row)
                System.out.print((s == null ? "[empty]" : s.getRollNo()) + " ");
            System.out.println();
        }

        Student target = students[0];
        String[] codes = target.getEnrolledCopy();
        int totalCH = 0;
        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];
            for (int j = 0; j < courses.length; j++) {
                if (courses[j].getCode().equals(code)) {
                    totalCH += courses[j].getCreditHours();
                    break;
                }
            }
        }
        System.out.println("\nTotal credits for " + target.getRollNo() + ": " + totalCH);
    }
}