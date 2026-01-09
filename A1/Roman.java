public class Roman {
    public static void main(String[] args) {
        /*
        System.out.printf("" + romanToInt("I") + "\n");
        System.out.printf("" + romanToInt("V") + "\n");
        System.out.printf("" + romanToInt("VII") + "\n");
        System.out.printf("" + romanToInt("MCMLIV") + "\n");
        System.out.printf("" + romanToInt("MCMXC") + "\n");
        System.out.printf("" + romanToInt("III") + "\n");
        System.out.printf("" + romanToInt("XCIX") + "\n");
        */
    }

    /*
     * *** I assume that all the input were valid roman numbers
     */
    public static int romanToInt(String romanNum) {
        int sum = 0;
        int romanNum_length = romanNum.length();
        for (int i = 0; i < romanNum_length; i++) {
            char ch = romanNum.charAt(i);
            int ch_val = get_value(ch);
            int flag = 1;

            if (i + 1 < romanNum_length && get_value(romanNum.charAt(i + 1)) > ch_val) {
                flag = -1;
            }

            sum += flag * ch_val;
        }

        return sum;
    }

    /*
     * returns the roman character's value as int
     */
    public static int get_value(char ch) {
        switch (ch) {
        case 'I':
            return 1;
        case 'V':
            return 5;
        case 'X':
            return 10;
        case 'L':
            return 50;
        case 'C':
            return 100;
        case 'D':
            return 500;
        case 'M':
            return 1000;
        default:
            throw new IllegalArgumentException("Unexpected character: " + ch);
        }
    }
}
