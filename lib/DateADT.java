import java.util.Date;

public interface DateADT extends Comparable
{
/**
  *  Constructs a Date from three integer paramaters representing
  *  the year, month and day.
  *  Precondition: a year month and day are passed
  *  Postcondition: a reference number is created
  *  Exception: InvalidDateException is thrown if an invalid set
  *		of date components is passed.
  */

    public void createDate(int year, int month, int day) throws IllegalArgumentException;

/**
  *  Returns a String representation of the date represented
  *  by the internal referrence number.
  *
  *  Precondition:  A valid internal reference number exists. A
  *                 reference number may be positive (dates past
  *                 January 1, 2000) or a negative number (dates
  *                 before January 1, 2000)
  *
  * Postcondition:  A String representation of the date in ISO format.
  *                 In the format yyyy/mm/dd.
  *
  */
  
    public String toString();

/*
 * Compares the current Date object to another Date object.
 *
 * Precondition: A valid Date object to compare to the
 *               current Date object.
 *
 * Postcondition: Returns a 1 if the current date is greater than
 *                date being compared. Returns 0 if both dates are
 *                equal. Returns -1 if the current date is less than
 *                the date being compared.
 *
 */

    public int compareTo(Object d);

/*
 * Advances or decrements the Date.
 *
 * Precondition: A valid Date object exists and a valid
 *               integer value representing the number of
 *               of days by which to change the date is passed.
 *
 * Postcondition: The Date is advanced by the number of days
 *                (positive value passed) or the date is
 *                decremented by the number of days (negative
 *                value).
 */

    public void changeDate(int days);
    
/*
 * Calculates the number of days between two Dates
 *
 * Precondition: A valid Date object to compare to the
 *               current Date object.
 *
 * Postcondition: An integer value representing the number
 *                of days between the current date and the
 *                date being compared.
 *
 */

    public int daysBetween(Date date);
}
