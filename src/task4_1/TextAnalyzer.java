package task4_1;

public class TextAnalyzer {

    private String text;

    // Constructor
    public TextAnalyzer(String text) {
        this.text = text;
    }

    // Override toString to return the text
    @Override
    public String toString() {
        return text;
    }

    // Method to count words (separated by spaces)
    public int wordCount() {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        // Split by one or more spaces and count
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Method to find the longest word
    public String longestWord() {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        String[] words = text.trim().split("\\s+");
        String longest = words[0];

        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }

        return longest;
    }

    // Method to reverse word order (not letters)
    public String reverseWords() {
        if (text == null || text.trim().isEmpty()) {
            return text;
        }

        String[] words = text.trim().split("\\s+");
        StringBuilder reversed = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" ");
            }
        }

        return reversed.toString();
    }

    // Method to count occurrences of substring (case-insensitive)
    public int countOccurrences(String target) {
        if (text == null || target == null || target.isEmpty()) {
            return 0;
        }

        String lowerText = text.toLowerCase();
        String lowerTarget = target.toLowerCase();

        int count = 0;
        int index = 0;

        while ((index = lowerText.indexOf(lowerTarget, index)) != -1) {
            count++;
            index += lowerTarget.length();
        }

        return count;
    }

    // Method to check if text is a palindrome
    public boolean isPalindrome() {
        if (text == null) {
            return false;
        }

        // Remove all non-letter characters (keep only letters)
        String cleaned = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toLowerCase();

        if (cleaned.isEmpty()) {
            return false;
        }

        // Check if cleaned string is palindrome
        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // Alternative palindrome check using StringBuilder
    public boolean isPalindrome2() {
        if (text == null) {
            return false;
        }

        String cleaned = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();

        return cleaned.equals(reversed);
    }

    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer("Java Programming is Fun and Java is Powerful");

        System.out.println("Текст: " + ta);
        System.out.println("Слов: " + ta.wordCount());
        System.out.println("Самое длинное слово: " + ta.longestWord());
        System.out.println("Слова наоборот: " + ta.reverseWords());
        System.out.println("'Java' встречается: " + ta.countOccurrences("java") + " раз(а)");
        System.out.println("'is' встречается: " + ta.countOccurrences("is") + " раз(а)");
        System.out.println("Палиндром: " + ta.isPalindrome());

        System.out.println();

        TextAnalyzer palindrome = new TextAnalyzer("А роза упала на лапу Азора");
        System.out.println("Текст: " + palindrome);
        System.out.println("Палиндром: " + palindrome.isPalindrome());

        // Additional test cases
        System.out.println("\n--- Дополнительные тесты ---");

        TextAnalyzer empty = new TextAnalyzer("");
        System.out.println("Пустой текст, слов: " + empty.wordCount());

        TextAnalyzer single = new TextAnalyzer("Привет");
        System.out.println("Одно слово наоборот: " + single.reverseWords());

        TextAnalyzer palindrome2 = new TextAnalyzer("Madam, I'm Adam");
        System.out.println("'Madam, I'm Adam' палиндром? " + palindrome2.isPalindrome());
    }
}