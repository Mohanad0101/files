package part2.part2_3;

public class StringProcessor {
    private static final String VOWELS = "aeiouyаеёиоуыэюя";

    public static int countVowels(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            if (VOWELS.indexOf(lower.charAt(i)) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static boolean isPalindrome(String text) {
        String lower = text.toLowerCase();
        int left = 0;
        int right = lower.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(lower.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(lower.charAt(right))) right--;
            if (lower.charAt(left) != lower.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static String reverse(String text) {
        char[] src = text.toCharArray();
        char[] out = new char[src.length];
        int left = 0;
        int right = src.length - 1;
        while (left <= right) {
            out[left] = src[right];
            out[right] = src[left];
            left++;
            right--;
        }
        return new String(out);
    }

    public static String findLongestWord(String sentence) {
        String[] words = sentence.split("[^\\p{L}\\p{N}]+");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        String s1 = "Привет, Java-разработчик!";
        String s2 = "топот";
        String s3 = "Madam";
        String s4 = "hello";
        String s5 = "А роза упала на лапу Азора";
        String s6 = "The quick brown fox jumps over the lazy dog";

        System.out.println("Гласные: " + countVowels(s1));
        System.out.println("Палиндром (топот): " + isPalindrome(s2));
        System.out.println("Палиндром (Madam): " + isPalindrome(s3));
        System.out.println("Реверс (hello): " + reverse(s4));
        System.out.println("Палиндром (фраза): " + isPalindrome(s5));
        System.out.println("Самое длинное слово: " + findLongestWord(s6));
    }
}
