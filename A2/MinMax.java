public class MinMax {
    public static void main(String[] args) {}
    /*
     * A rough calculation of the code 'comparison complexity' gives ⌊n/2⌋ + n = 3n/2 - c --- with some deduction along
     * the way.
     *
     * Throughly calculstion gives ...
     *
     * odd cases: 0 + (n - 1)/2 + n - 1 = 3n/2 - 3/2
     * ; 0 comparision when we check for even length
     * ; (n - 1)/2 comparisions for in-pair comparision
     * ; n - 1 comparisions for local-my Min/ Max comparisons
     *
     * even cases: 1 + (n - 2)/2 + n - 2 = 3n/2 - 2
     * ; 1 comparison when we check for even length --- it's even
     * ; (n - 2)/2 comparisons for in-pair comparison
     * ; n - 2 comparisons for local-my Min/ Max comparisons
     *
     * *** `(numbers.length & 0x1) == 0` and `i < numbers.length` are not counted as a 'comparision'
     */
    public static double minMaxAverage(int[] numbers) {
        // your code goes here

        int myMin;
        int myMax;
        int i;

        // check for even length
        if ((numbers.length & 0x1) == 0) {
            if (numbers[0] > numbers[1]) { // 1 comprison
                myMin = numbers[1];
                myMax = numbers[0];
            } else {
                myMin = numbers[0];
                myMax = numbers[1];
            }

            i = 2;
        } else { // 0 comparison
            myMin = numbers[0];
            myMax = numbers[0];
            i = 1;
        }

        while (i < numbers.length) {
            int localMin;
            int localMax;

            if (numbers[i] > numbers[i + 1]) {
                localMin = numbers[i + 1];
                localMax = numbers[i];
            } else {
                localMin = numbers[i];
                localMax = numbers[i + 1];
            }

            if (localMin < myMin) {
                myMin = localMin;
            }
            if (localMax > myMax) {
                myMax = localMax;
            }

            i += 2;
        }
        return (myMin + myMax) / 2.0;
    }
}
