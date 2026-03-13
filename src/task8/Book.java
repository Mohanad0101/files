package task8;

import java.time.Year;

// Record Book with compact constructor validation
public record Book(String title, String author, int year, Genre genre, double price) {

    // Compact constructor with validation
    public Book {
        // Validate title
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }

        // Validate author
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор не может быть пустым");
        }

        // Validate year (from 1450 to current year)
        int currentYear = Year.now().getValue();
        if (year < 1450 || year > currentYear) {
            throw new IllegalArgumentException("Год должен быть от 1450 до " + currentYear);
        }

        // Validate price
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }
}