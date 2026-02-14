import java.util.ArrayList;
import java.util.Arrays;

/*
Problem 1: Locations of Capital Letters
Inside the public class AllCaps, write a static method

public static int[] allCapLocations(String st)

which takes as input a String st and returns all indices where the letter there is a capital letter (A-Z). However, the
input string may contain symbols other than the English alphabet.

Examples:
allCapLocations("a@bAaCt99q") == {3, 5}
allCapLocations("@82968*%(*@^ttt)") == {}
allCapLocations("PQRssssS") == {0, 1, 2, 7}
*/

public class AllCaps {
    public static int[] allCapLocations(String st) {
        ArrayList<Integer> lst = new ArrayList<>();

        for (int i = 0; i < st.length(); i++) {
            char ch = st.charAt(i);
            if (Character.isUpperCase(ch)) {
                lst.add((Integer)i);
            }
        }

        int[] lst_prim = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            lst_prim[i] = lst.get(i);
        }

        return lst_prim;
    }

    public static void main(String[] args) {
        int[] t1 = {3, 5};
        assert Arrays.equals(allCapLocations("a@bAaCt99q"), t1);

        int[] t2 = {};
        assert Arrays.equals(allCapLocations("@82968*%(*@^ttt)"), t2);

        int[] t3 = {0, 1, 2, 7};
        assert Arrays.equals(allCapLocations("PQRssssS"), t3);
    }
}
