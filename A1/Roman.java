public class Roman {
    public static void main(String[] args) {
        assert romanToInt("I") == 1;
        assert romanToInt("V") == 5;
        assert romanToInt("VII") == 7;
        assert romanToInt("MCMLIV") == 1954;
        assert romanToInt("MCMXC") == 1990;
        assert romanToInt("III") == 3;
        assert romanToInt("XCIX") == 99;
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
