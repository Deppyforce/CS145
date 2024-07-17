/**
 * Tanagorn Suksamran
 * CS145
 * Lab 4
 * Date Object Class
 */

public class Date {
    // data
    private int month;
    private int day;
    private int year;
    
    // constructor: confirm proper value for month and day given the year
    public Date(int month, int day, int year) {
        int[] daysInMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // if year is invalid, throw exception
        if (year < 0) {
            throw new IllegalArgumentException("invalid year.");
        }

        // if leapyear, set day count in feb to 29
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            daysInMonth[1] = 29;
        }

        // if month not in range, throw exception
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("invalid month.");
        }

        // if day not in range, throw exception
        if (day < 1 || day > daysInMonth[month-1]) {
            throw new IllegalArgumentException("invalid day.");
        }
        
        // tests passed, set values
        this.month = month;
        this.day = day;
        this.year = year;
    }

    // return month
    public int getMonth() {
        return this.month;
    }

    // return day
    public int getDay() {
        return this.day;
    }

    // return year
    public int getYear() {
        return this.year;
    }

    // returns string representation as "m/d/y" 
    public String toString() {
        return String.format("%d/%d/%d", this.month, this.day, this.year);
    }
}
