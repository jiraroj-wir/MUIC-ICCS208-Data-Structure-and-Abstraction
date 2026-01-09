public class Hidden {
    public static void main(String[] args) {
        assert isHidden("welcometothehotelcalifornia", "melon");
        assert !isHidden("welcometothehotelcalifornia", "space");
        assert isHidden("TQ89MnQU3IC7t6", "MUIC");
        assert !isHidden("VhHTdipc07", "htc");
        assert isHidden("VhHTdipc07", "hTc");
    }
    public static boolean isHidden(String s, String t) {
        int j = 0;
        for (int i = 0; i < s.length() && j < t.length(); i++) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
        }
        return (j == t.length());
    }
}
