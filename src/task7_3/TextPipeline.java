package task7_3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextPipeline {

    public static void main(String[] args) {
        System.out.println("=== ЧАСТЬ A: Функциональный конвейер ===\n");

        // Part A: Function composition pipeline

        // Define individual functions
        Function<String, String> trim = String::trim;

        Function<String, String> lower = String::toLowerCase;

        Function<String, String> removeExtraSpaces = s -> s.replaceAll("\\s+", " ");

        Function<String, String> capitalize = s -> {
            if (s == null || s.isEmpty()) return s;
            return s.substring(0, 1).toUpperCase() + s.substring(1);
        };

        // Combine functions into a pipeline using andThen()
        Function<String, String> normalize = trim
                .andThen(lower)
                .andThen(removeExtraSpaces)
                .andThen(capitalize);

        // Test the pipeline on multiple lines
        List<String> rawLines = Arrays.asList(
                "  hello    world   ",
                "  JAVA    PROGRAMMING  IS  FUN  ",
                "   functional   programming   with   streams   ",
                "   local    classes   are   COOL   "
        );

        System.out.println("Исходные строки:");
        rawLines.forEach(line -> System.out.println("  \"" + line + "\""));

        System.out.println("\nПосле нормализации:");
        List<String> normalizedLines = rawLines.stream()
                .map(normalize)
                .collect(Collectors.toList());

        normalizedLines.forEach(line -> System.out.println("  \"" + line + "\""));

        System.out.println("\n=== ЧАСТЬ B: Локальный класс WordCounter ===\n");

        // Part B: Local class inside method
        class WordCounter {
            private final String text;
            private final String[] words;

            // Constructor
            public WordCounter(String text) {
                this.text = text;
                // Split into words (by spaces) and filter empty strings
                this.words = text.split("\\s+");
            }

            // Method to count word frequencies
            public Map<String, Integer> count() {
                Map<String, Integer> frequency = new HashMap<>();

                for (String word : words) {
                    frequency.put(word, frequency.getOrDefault(word, 0) + 1);
                }

                return frequency;
            }

            // Method to find most frequent word
            public String mostFrequent() {
                Map<String, Integer> freq = count();

                if (freq.isEmpty()) {
                    return null;
                }

                return freq.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse(null);
            }

            // Optional: get all words
            public String[] getWords() {
                return words.clone();
            }

            // Optional: get word count
            public int getTotalWords() {
                return words.length;
            }

            @Override
            public String toString() {
                return String.format("WordCounter[текст: \"%s\", слов: %d]",
                        text.length() > 30 ? text.substring(0, 27) + "..." : text,
                        words.length);
            }
        }

        // Process each normalized line with WordCounter
        for (int i = 0; i < normalizedLines.size(); i++) {
            String line = normalizedLines.get(i);
            WordCounter counter = new WordCounter(line);

            System.out.println("Строка " + (i + 1) + ": \"" + line + "\"");
            System.out.println("  Всего слов: " + counter.getTotalWords());

            Map<String, Integer> frequencies = counter.count();
            System.out.println("  Частота слов: " + frequencies);

            String mostFreq = counter.mostFrequent();
            System.out.println("  Самое частое слово: \"" + mostFreq + "\" (встречается " +
                    frequencies.get(mostFreq) + " раз(а))");
            System.out.println();
        }

        // Additional test: Combine all normalized text and analyze together
        System.out.println("--- Анализ всего текста вместе ---");

        // Join all normalized lines
        String fullText = String.join(" ", normalizedLines);
        WordCounter fullCounter = new WordCounter(fullText);

        System.out.println("Полный текст: \"" + fullText + "\"");
        System.out.println("Всего слов: " + fullCounter.getTotalWords());

        Map<String, Integer> fullFrequencies = fullCounter.count();
        System.out.println("Частота всех слов: " + fullFrequencies);

        String mostFrequentAll = fullCounter.mostFrequent();
        System.out.println("Самое частое слово во всем тексте: \"" + mostFrequentAll +
                "\" (встречается " + fullFrequencies.get(mostFrequentAll) + " раз(а))");

        // Find words that appear more than once
        System.out.println("\nСлова, встречающиеся более одного раза:");
        fullFrequencies.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println("  \"" + e.getKey() + "\": " + e.getValue() + " раз"));

        // Demonstrate function composition with additional operations
        System.out.println("\n=== Дополнительная демонстрация функциональной композиции ===");

        // Create more complex pipeline
        Function<String, String> removePunctuation = s -> s.replaceAll("[^\\sa-zA-Zа-яА-Я0-9]", "");
        Function<String, String> toCamelCase = s -> {
            String[] words2 = s.split("\\s+");
            StringBuilder result = new StringBuilder();
            for (String w : words2) {
                if (!w.isEmpty()) {
                    result.append(Character.toUpperCase(w.charAt(0)))
                            .append(w.substring(1).toLowerCase());
                }
            }
            return result.toString();
        };

        // Extended pipeline
        Function<String, String> extendedPipeline = normalize
                .andThen(removePunctuation)
                .andThen(toCamelCase);

        System.out.println("Исходная строка: \"  hello   world!   \"");
        System.out.println("После extendedPipeline: \"" +
                extendedPipeline.apply("  hello   world!   ") + "\"");
    }
}