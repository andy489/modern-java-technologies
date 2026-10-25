public class MinSwaps {
    public static int minSwaps(String s1, String s2) {
        int differences = 0;
        int len = s1.length();

        for (int i = 0; i < len; i++) {
            differences += s1.charAt(i) == s2.charAt(i) ? 0 : 1;
        }

        return differences / 2;
    }

    public static void main(String... args) {
        System.out.println(MinSwaps.minSwaps("1100", "1001"));   // 1
        System.out.println(MinSwaps.minSwaps("110011", "010111")); // 1
        System.out.println(MinSwaps.minSwaps("10011001", "01100110")); // 4
    }
}
