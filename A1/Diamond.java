public class Diamond {
    public static void main(String[] args) {}
    public static void printDiamond(int k) {
        for (int line = 1; line <= k; line++) {
            int star = 2 * line - 1;
            int hash = ((2 * k) + 1) - star;
            for (int i = 0; i < hash / 2; i++) {
                System.out.printf("#");
            }
            for (int i = 0; i < star; i++) {
                System.out.printf("*");
            }
            for (int i = 0; i < hash / 2; i++) {
                System.out.printf("#");
            }
            System.out.printf("\n");
        }
        for (int line = k - 1; line > 0; line--) {
            int star = 2 * line - 1;
            int hash = ((2 * k) + 1) - star;
            for (int i = 0; i < hash / 2; i++) {
                System.out.printf("#");
            }
            for (int i = 0; i < star; i++) {
                System.out.printf("*");
            }
            for (int i = 0; i < hash / 2; i++) {
                System.out.printf("#");
            }
            System.out.printf("\n");
        }
    }
}
