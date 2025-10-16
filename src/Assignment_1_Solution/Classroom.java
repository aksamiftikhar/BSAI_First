package Assignment_1_Solution;

public class Classroom {
    public static final int EMPTY = 0;

    private final String roomId;
    private int rows;
    private int cols;

    private int[][] seatCodes;     // 2D primitive
    private Student[][] seating;   // 2D reference

    public Classroom(String roomId, int rows, int cols) {
        this.roomId = roomId;
        this.rows = rows;
        this.cols = cols;
        this.seatCodes = new int[rows][cols];
        this.seating   = new Student[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++)
                seatCodes[r][c] = EMPTY;
        }
    }

    public boolean assignSeat(int r, int c, Student s) {
        if (!inBounds(r, c) || s == null) return false;
        if (seating[r][c] != null) return false;
        seating[r][c] = s;
        seatCodes[r][c] = r * 100 + c;
        return true;
    }

    public boolean vacateSeat(int r, int c) {
        if (!inBounds(r, c)) return false;
        if (seating[r][c] == null) return false;
        seating[r][c] = null;
        seatCodes[r][c] = EMPTY;
        return true;
    }

    public int countOccupied() {
        int count = 0;
        for (Student[] row : seating) {
            for (Student st : row)
                if (st != null)
                    count++;
        }
        return count;
    }

    public String locate(String rollNo) {
        for (int r = 0; r < seating.length; r++) {
            for (int c = 0; c < seating[r].length; c++) {
                if (seating[r][c] != null && seating[r][c].getRollNo().equals(rollNo)) {
                    return "row=" + r + ",col=" + c;
                }
            }
        }
        return "not found";
    }

    public int[][] copySeatCodes() {
        int[][] copy = new int[seatCodes.length][];
        for (int r = 0; r < seatCodes.length; r++) {
            copy[r] = new int[seatCodes[r].length];
            for (int c = 0; c < seatCodes[r].length; c++)
                copy[r][c] = seatCodes[r][c];
        }
        return copy;
    }

    public void reconfigureJagged(int[] rowLengths) {
        seating = new Student[rowLengths.length][];
        seatCodes = new int[rowLengths.length][];
        for (int r = 0; r < rowLengths.length; r++) {
            seating[r] = new Student[rowLengths[r]];
            seatCodes[r] = new int[rowLengths[r]];
        }
        this.rows = rowLengths.length;
        this.cols = -1;
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && c >= 0 && r < seating.length && c < seating[r].length;
    }

    // getters
    public String getRoomId() { return roomId; }
    public Student[][] getSeatingUnsafe() { return seating; }
    public int[][] getSeatCodesUnsafe() { return seatCodes; }
}