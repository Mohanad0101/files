package task8;

import java.util.*;
import java.util.stream.Collectors;

// Library class
public class Library {
    private List<LibraryItem> items = new ArrayList<>();

    // Add item to library
    public void add(LibraryItem item) {
        if (item != null) {
            items.add(item);
        }
    }

    // Print catalog using pattern matching switch
    public void printCatalog() {
        System.out.println("=== КАТАЛОГ БИБЛИОТЕКИ ===");

        for (int i = 0; i < items.size(); i++) {
            LibraryItem item = items.get(i);
            System.out.print((i + 1) + ". ");

            // Pattern matching switch (Java 21+)
            switch (item) {
                case PhysicalBook p -> System.out.println(p.getInfo());
                case EBook e -> System.out.println(e.getInfo());
            }
        }
        System.out.println();
    }

    // Group items by genre using EnumMap
    public Map<Genre, List<LibraryItem>> groupByGenre() {
        Map<Genre, List<LibraryItem>> grouped = new EnumMap<>(Genre.class);

        // Initialize with empty lists for each genre
        for (Genre genre : Genre.values()) {
            grouped.put(genre, new ArrayList<>());
        }

        // Group items by genre
        for (LibraryItem item : items) {
            Book book = switch (item) {
                case PhysicalBook p -> p.book();
                case EBook e -> e.book();
            };
            grouped.get(book.genre()).add(item);
        }

        return grouped;
    }

    // Calculate total value using Stream + Reduce
    public double totalValue() {
        return items.stream()
                .mapToDouble(item -> {
                    return switch (item) {
                        case PhysicalBook p -> p.book().price();
                        case EBook e -> e.book().price();
                    };
                })
                .sum();
    }

    // Find most expensive book
    public Optional<LibraryItem> mostExpensive() {
        return items.stream()
                .max(Comparator.comparingDouble(item -> {
                    return switch (item) {
                        case PhysicalBook p -> p.book().price();
                        case EBook e -> e.book().price();
                    };
                }));
    }

    // Get unique authors by genre, sorted alphabetically
    public List<String> authorsByGenre(Genre genre) {
        return items.stream()
                .map(item -> {
                    return switch (item) {
                        case PhysicalBook p -> p.book();
                        case EBook e -> e.book();
                    };
                })
                .filter(book -> book.genre() == genre)
                .map(Book::author)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // Search items by query
    public List<LibraryItem> search(String query) {
        String lowerQuery = query.toLowerCase();
        return items.stream()
                .filter(item -> {
                    String info = switch (item) {
                        case PhysicalBook p -> p.getInfo();
                        case EBook e -> e.getInfo();
                    };
                    return query == null || query.isEmpty() ||
                            info.toLowerCase().contains(lowerQuery);
                })
                .collect(Collectors.toList());
    }
}
