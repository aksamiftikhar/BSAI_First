package Lecture13;

public class EnumDemo2 {
    public static void main(String[] args) {
        Day d = Day.MONDAY;

        if(d == Day.MONDAY) {

        }
    }
}

enum Day
{
    MONDAY,  TUESDAY, WEDNESDAY, THURSDAY,  FRIDAY, SATURDAY, SUNDAY;
}

