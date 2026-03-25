// (Optional) Practice Exercise
/*
 * Inside a class named UsefulNumbers, write a program that prints out the number of seconds in a century (i.e., 100
 * years). To practice declaring variables in Java, your code will perform exactly the following steps:
 */

public class UsefulNumbers {
    public static void main(String[] args) {
        /* Declare a variable secondsInAMinute of an appropriate type and set it to the correct value. Remember that
         * there are 60 seconds in a minute.
         */
        long secondsInAMinute = 60;

        /* Declare a variable secondsInAnHour of an appropriate type and set it to the correct value by referring to
         * the variable above. Remember that there are 60 minutes in an hour.
         */
        long secondsInAnHour = secondsInAMinute * 60;

        /* Declare a variable secondsInADay of an appropriate type and set it to the correct value by referring to
         * the variable above. Remember that there are 24 hours in a day.
         */
        long secondsInADay = secondsInAnHour * 24;

        /* Declare a variable secondsInAYear of an appropriate type and set it to the correct value by referring to
         * the variable above. Remember that there are 365 days in a year. (NB: We're cheating. Let's pretend we
         * don't care about leap years!)
         */
        long secondsInAYear = secondsInADay * 365;

        /* Declare a variable secondsInACentury of an appropriate type and set it to the correct value by referring
         * to the variable above. Remember that there are 100 years in a century.
         */
        long secondsInACentury = secondsInAYear * 100;

        // Print the final value out using printf
        // Your awesome computation leading up to
        // secondsInACentury

        System.out.printf("One century contains %d seconds.\n", secondsInACentury);

        // (Hint: The answer is 3153600000)
    }
}
