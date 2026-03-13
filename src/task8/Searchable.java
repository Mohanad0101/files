package task8;

import java.util.*;
import java.util.stream.Collectors;

// Searchable interface with default and static methods
public interface Searchable {
    // Method to check if item matches query
    default boolean matches(String query) {
        if (query == null || query.isEmpty()) return true;

        String lowerQuery = query.toLowerCase();

        // This will be overridden by implementing classes
        return toString().toLowerCase().contains(lowerQuery);
    }

    // Static method to search any list of Searchable items
    static <T extends Searchable> List<T> search(List<T> items, String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>(items);
        }

        return items.stream()
                .filter(item -> item.matches(query))
                .collect(Collectors.toList());
    }
}