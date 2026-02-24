public class Midway {
    public static long stepsRemaining(int[] diskPos) {
        // return -1; // TODO: change me
        int len = diskPos.length;

        return recurrence(len) - solve_hanoi(diskPos, len, 0, 1, 2);
    }

    private static long solve_hanoi(int[] diskPos, int n, int from, int to, int aux) {
        if (n == 0) {
            return 0;
        }

        int largest = diskPos[n - 1];

        // moves so far
        if (largest == from) { // disk n still is on the left or right
            // we move disc 1 to (n-1) to another side (left or right) first, so we can move disk n
            return solve_hanoi(diskPos, n - 1, from, aux, to);
        } else if (largest == to) { // disc k in the middle
            // so we addup T(n-1) --- as the recurrence computation is cheap --- only bitwise shift left and addition
            // +1 move for disc n
            // with the remaining moves until the middle tower is complete
            return recurrence(n - 1) + 1 + solve_hanoi(diskPos, n - 1, aux, to, from);
        } else {
            throw new IllegalArgumentException("this disk configuration cannot be solved!");
        }
    }

    private static long recurrence(int n) {
        if (n == 63) {
            return 0x7FFFFFFFFFFFFFFFL; // 2^63 - 1
        }

        return (1L << n) - 1; // T(n) = 2^n - 1}
    }

    /*
     * Ps. I'm surprised on how useful the given solve_hanoi function was.
     */
    // test
    public static void main(String[] args) {
        assert stepsRemaining(new int[] {0}) == 1;
        assert stepsRemaining(new int[] {2, 2, 1}) == 3;
        assert stepsRemaining(new int[] {2, 2, 0}) == 4;
        assert stepsRemaining(new int[] {1, 2, 0}) == 5;
        assert stepsRemaining(new int[] {2, 2, 1, 1, 2, 2, 1}) == 51;
    }
