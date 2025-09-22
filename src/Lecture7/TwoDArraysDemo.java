package Lecture7;

// Save as: TwoDArraysDemo.java

// Simple reference types for 2D arrays of objects
class Seat {
    String id;
    boolean reserved;
    Seat(String id) { this.id = id; }
    public String info() { return id + (reserved ? " [R]" : " [ ]"); }
}

class Student {
    String roll;
    Student(String roll) { this.roll = roll; }
}

public class TwoDArraysDemo {
    public static void main(String[] args) {
        System.out.println("=== 2D Arrays Demo (Primitive + Reference Types) ===\n");

        // ------------------------------------------------------------
        // 1) 2D arrays are "arrays of arrays"
        //    Rectangular example with primitives (int)
        // ------------------------------------------------------------
        int rows = 3, cols = 4;
        int[][] grid = new int[rows][cols]; // rectangular: every row has same length

        // grid is an array of length 3; each element grid[r] is itself an int[] of length 4
        System.out.println("Rectangular int[][]:");
        System.out.println("grid.length (rows)      = " + grid.length);
        System.out.println("grid[0].length (cols)   = " + grid[0].length);
        System.out.println("grid is array of arrays = " + (grid instanceof Object));
        System.out.println("grid[0] is an array     = " + (grid[0] instanceof Object));

        // Fill with simple values using classic for loops (index needed when writing)
        System.out.println("\nFilling grid with r*10 + c using classic for loops:");
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = r * 10 + c;
            }
        }

        // Read using enhanced for loops (foreach). Note: inner variable 'val' is a COPY for primitives.
        System.out.println("Reading grid with enhanced for loops:");
        for (int[] row : grid) {           // row is an int[] reference
            for (int val : row) {          // val is a copy of the int element
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Try to increment values using enhanced-for variable (this WON'T modify the array)
        for (int[] row : grid) {
            for (int val : row) {
                val = val + 100; // changes only the local copy 'val', not the array element
            }
        }
        System.out.println("\nAfter val += 100 attempt inside enhanced for (no change expected):");
        printIntGrid(grid);

        // Correct way to modify: use indices (classic for)
        System.out.println("\nModify grid correctly via indices (add 100 to each cell):");
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] += 100;
            }
        }
        printIntGrid(grid);

        // ------------------------------------------------------------
        // 2) Jagged arrays (a.k.a. ragged): rows have different lengths
        // ------------------------------------------------------------
        System.out.println("\nJagged int[][] (different row sizes):");
        int[][] jagged = new int[3][];   // only number of rows fixed
        jagged[0] = new int[1];          // row 0 length = 1
        jagged[1] = new int[3];          // row 1 length = 3
        jagged[2] = new int[2];          // row 2 length = 2

        // Fill with (r+1)*100 + c
        for (int r = 0; r < jagged.length; r++) {
            for (int c = 0; c < jagged[r].length; c++) {
                jagged[r][c] = (r + 1) * 100 + c;
            }
        }
        printIntGrid(jagged);

        // ------------------------------------------------------------
        // 3) 2D arrays of REFERENCE types (objects): Seat[][]
        //    Demonstrate that each cell holds a REFERENCE to a Seat object
        // ------------------------------------------------------------
        System.out.println("\n2D array of reference types: Seat[][] (rectangular 2x3)");
        Seat[][] hall = new Seat[2][3]; // rectangular: 2 rows, 3 columns

        // Each cell is null until we put a Seat reference there
        System.out.println("Initially, hall[0][0] is null? " + (hall[0][0] == null));

        // Fill with Seat objects
        for (int r = 0; r < hall.length; r++) {
            for (int c = 0; c < hall[r].length; c++) {
                hall[r][c] = new Seat("R" + r + "C" + c);
            }
        }
        printHall(hall);

        // Mark one seat reserved via one reference...
        Seat s = hall[0][1];     // s holds a REFERENCE to the Seat at (0,1)
        s.reserved = true;       // mutating the object through 's' affects the array cell too
        System.out.println("\nAfter setting hall[0][1] reserved via separate variable 's':");
        printHall(hall);

        // ------------------------------------------------------------
        // 4) "Array of arrays" reference behavior (aliasing rows)
        //    Show that rows themselves are references you can share/alias.
        // ------------------------------------------------------------
        System.out.println("\nRow aliasing (array-of-arrays):");
        int[] row0 = grid[0];      // row0 is a REFERENCE to the first row array
        row0[0] = 999;             // modify the first element of row0
        System.out.println("grid[0][0] now = " + grid[0][0] + " (changed via row0 reference)");

        // Make two rows point to the SAME int[] (aliasing at row-level)
        grid[1] = grid[0];         // Now row 1 and row 0 are the same array object
        System.out.println("grid[1] == grid[0]? " + (grid[1] == grid[0]));
        grid[1][1] = 777;          // changes row 0 too, since they are the same array
        System.out.println("grid[0][1] after writing grid[1][1] = " + grid[0][1]);

        // ------------------------------------------------------------
        // 5) "Pass-by-reference via pass-by-value" intuition (without methods)
        //    In Java, variables hold references; assigning a reference copies the REFERENCE (pass-by-value).
        //    Reassigning the local reference doesn't change the original array, but mutating through it does.
        // ------------------------------------------------------------
        System.out.println("\nPass-by-value of a REFERENCE (locally demonstrated):");
        int[] localRef = grid[0];  // copy the REFERENCE (value) of grid[0] into localRef
        localRef[2] = 555;         // mutate the shared row object
        System.out.println("grid[0][2] after localRef[2]=555: " + grid[0][2] + " (mutated via shared reference)");

        // Reassign localRef to a NEW array; original grid[0] won't change
        localRef = new int[] { 1, 2, 3, 4 };
        System.out.println("After reassigning localRef to a NEW array:");
        System.out.println("grid[0][2] remains: " + grid[0][2] + " (because only localRef changed)");

        // ------------------------------------------------------------
        // 6) Jagged 2D array of reference types (students in classrooms of different sizes)
        // ------------------------------------------------------------
        System.out.println("\nJagged Student[][]:");
        Student[][] sections = new Student[3][];
        sections[0] = new Student[1]; // small section
        sections[1] = new Student[3]; // larger section
        sections[2] = new Student[2]; // medium section

        // Fill only some seats to also show nulls in object arrays
        sections[0][0] = new Student("23-CS-001");
        sections[1][0] = new Student("23-CS-002");
        sections[1][1] = new Student("23-CS-003");
        // sections[1][2] remains null (empty seat)
        // sections[2] rows also partially empty (nulls)

        System.out.println("Printing Student roll numbers using enhanced-for (handle nulls):");
        for (Student[] sec : sections) {
            for (Student st : sec) {
                if (st == null) System.out.print("[empty] ");
                else System.out.print(st.roll + " ");
            }
            System.out.println();
        }

        // ------------------------------------------------------------
        // 7) Enhanced-for caveat with object references:
        //    Reassigning the enhanced-for variable DOES NOT change the array cell.
        // ------------------------------------------------------------
        System.out.println("\nEnhanced-for reassignment does NOT write back to the array:");
        for (Student[] sec : sections) {
            for (Student st : sec) {
                // This creates a NEW Student and puts it into 'st' (the loop variable),
                // but DOES NOT store it back into the array cell.
                if (st == null) {
                    st = new Student("TEMP"); // has no effect on sections array
                }
            }
        }
        // Show that array remains unchanged (nulls still present)
        for (int r = 0; r < sections.length; r++) {
            for (int c = 0; c < sections[r].length; c++) {
                System.out.print((sections[r][c] == null ? "[empty]" : sections[r][c].roll) + " ");
            }
            System.out.println();
        }

        // Correct way to fill empty seats: use indices
        System.out.println("\nFilling empty seats with indices (writes back to array):");
        for (int r = 0; r < sections.length; r++) {
            for (int c = 0; c < sections[r].length; c++) {
                if (sections[r][c] == null) {
                    sections[r][c] = new Student("TEMP-" + r + "-" + c);
                }
            }
        }
        // Verify
        for (Student[] sec : sections) {
            for (Student st : sec) {
                System.out.print(st.roll + " ");
            }
            System.out.println();
        }

        System.out.println("\n=== End of Demo ===");
    }

    // -------- Helper printers (local to this file) --------
    private static void printIntGrid(int[][] arr) {
        for (int[] row : arr) {
            for (int v : row) System.out.print(v + " ");
            System.out.println();
        }
    }

    private static void printHall(Seat[][] hall) {
        for (Seat[] row : hall) {
            for (Seat seat : row) {
                System.out.print(seat.info() + "  ");
            }
            System.out.println();
        }
    }
}
