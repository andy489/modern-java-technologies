public class PrefixExtractor {
    public static String getLongestCommonPrefix(String[] words) {

        if (words == null || words.length == 0 || words[0].isEmpty()) {
            return "";
        }

        if (words.length == 1) {
            return words[0];
        }

        int minLen = Integer.MAX_VALUE;

        for (String currWord : words) {
            minLen = Math.min(currWord.length(), minLen);
        }

        for (int i = 0; i < minLen; i++) {
            
            char currChar = words[0].charAt(i);

            for (String currWord : words) {
                if (currWord.charAt(i) != currChar) {
                    return words[0].substring(0, i);
                }
            }
        }

        return words[0].substring(0, minLen);
    }

    public static void main(String... args) {
        System.out.println(PrefixExtractor.getLongestCommonPrefix(new String[]{"flower", "flow", "flight"})); // "fl"
        System.out.println(PrefixExtractor.getLongestCommonPrefix(new String[]{"dog", "racecar", "car"}));   // ""
        System.out.println(PrefixExtractor.getLongestCommonPrefix(new String[]{"cat"}));                     // "cat"
        System.out.println(PrefixExtractor.getLongestCommonPrefix(new String[]{"protein", "protest", "protozoa"})); // "prot"
    }
}
