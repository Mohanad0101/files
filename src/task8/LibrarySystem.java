package task8;

import java.util.*;

// Main class
public class LibrarySystem {
    public static void main(String[] args) {
        System.out.println("=== БИБЛИОТЕЧНАЯ СИСТЕМА ===\n");

        try {
            // Create library
            Library library = new Library();

            // Create books
            Book book1 = new Book("Война и мир", "Лев Толстой", 1869, Genre.FICTION, 1200.0);
            Book book2 = new Book("Краткая история времени", "Стивен Хокинг", 1988, Genre.SCIENCE, 850.0);
            Book book3 = new Book("История России", "Николай Карамзин", 1818, Genre.HISTORY, 950.0);
            Book book4 = new Book("Java для начинающих", "Герберт Шилдт", 2022, Genre.PROGRAMMING, 1500.0);
            Book book5 = new Book("Мона Лиза", "Вальтер Айзексон", 2017, Genre.ART, 1100.0);
            Book book6 = new Book("Преступление и наказание", "Федор Достоевский", 1866, Genre.FICTION, 800.0);
            Book book7 = new Book("Алгоритмы на Java", "Роберт Седжвик", 2021, Genre.PROGRAMMING, 2000.0);
            Book book8 = new Book("Эпоха Возрождения", "Джорджо Вазари", 1550, Genre.ART, 1300.0);

            // Add physical and electronic books to library
            library.add(new PhysicalBook(book1, "A1-01"));
            library.add(new EBook(book2, "PDF", 5.2));
            library.add(new PhysicalBook(book3, "B2-03"));
            library.add(new EBook(book4, "EPUB", 8.7));
            library.add(new PhysicalBook(book5, "C3-05"));
            library.add(new EBook(book6, "MOBI", 3.8));
            library.add(new PhysicalBook(book7, "D4-02"));
            library.add(new EBook(book8, "PDF", 12.3));

            // Print catalog
            library.printCatalog();

            // Group by genre
            System.out.println("=== КНИГИ ПО ЖАНРАМ ===");
            Map<Genre, List<LibraryItem>> byGenre = library.groupByGenre();
            byGenre.forEach((genre, items) -> {
                if (!items.isEmpty()) {
                    System.out.println(genre.getRussianName() + ": " + items.size() + " книг");
                    items.forEach(item -> {
                        String title = switch (item) {
                            case PhysicalBook p -> "  - " + p.book().title();
                            case EBook e -> "  - " + e.book().title() + " [Э]";
                        };
                        System.out.println(title);
                    });
                }
            });
            System.out.println();

            // Total value
            System.out.printf("=== ОБЩАЯ СТОИМОСТЬ ===\n%.2f руб.\n\n", library.totalValue());

            // Most expensive book
            System.out.println("=== САМАЯ ДОРОГАЯ КНИГА ===");
            library.mostExpensive().ifPresentOrElse(
                    item -> {
                        switch (item) {
                            case PhysicalBook p -> System.out.println(p.getInfo());
                            case EBook e -> System.out.println(e.getInfo());
                        }
                    },
                    () -> System.out.println("Библиотека пуста")
            );
            System.out.println();

            // Authors by genre
            System.out.println("=== АВТОРЫ В ЖАНРЕ ПРОГРАММИРОВАНИЕ ===");
            List<String> programmingAuthors = library.authorsByGenre(Genre.PROGRAMMING);
            programmingAuthors.forEach(author -> System.out.println("  - " + author));
            System.out.println();

            // Search functionality
            System.out.println("=== ПОИСК КНИГ ПО ЗАПРОСУ 'Java' ===");
            List<LibraryItem> searchResults = library.search("Java");
            searchResults.forEach(item -> {
                switch (item) {
                    case PhysicalBook p -> System.out.println("  " + p.getInfo());
                    case EBook e -> System.out.println("  " + e.getInfo());
                }
            });
            System.out.println();

            // Test validation
            System.out.println("=== ТЕСТ ВАЛИДАЦИИ ===");
            try {
                Book invalidBook = new Book("", "Автор", 2023, Genre.FICTION, 100);
            } catch (IllegalArgumentException e) {
                System.out.println("  Ошибка при создании книги: " + e.getMessage());
            }

            try {
                PhysicalBook invalidPhysical = new PhysicalBook(book1, "");
            } catch (IllegalArgumentException e) {
                System.out.println("  Ошибка при создании физической книги: " + e.getMessage());
            }

            try {
                EBook invalidEBook = new EBook(book2, "", -5);
            } catch (IllegalArgumentException e) {
                System.out.println("  Ошибка при создании электронной книги: " + e.getMessage());
            }

            // Test Genre.fromString
            System.out.println("\n=== ТЕСТ ПОИСКА ЖАНРА ПО РУССКОМУ НАЗВАНИЮ ===");
            String searchGenre = "Наука";
            try {
                Genre found = Genre.fromString(searchGenre);
                System.out.println("  Жанр '" + searchGenre + "' соответствует: " + found);
            } catch (IllegalArgumentException e) {
                System.out.println("  " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}

/*

┌───────────┐          ┌───────────┐
│   Genre   │◄─────────┤   Book    │
│ (enum)    │    has   │ (record)  │
└───────────┘          └─────┬─────┘
                             │
                             │ is part of
                             ▼
                    ┌─────────────────┐
                    │  LibraryItem    │
                    │   (interface)   │
                    └────────┬────────┘
                             │
            ┌────────────────┼────────────────┐
            │ extends        │ extends        │
            ▼                 ▼                 ▼
    ┌───────────────┐  ┌───────────────┐  ┌───────────────┐
    │ PhysicalBook  │  │    EBook      │  │  Searchable   │
    │   (record)    │  │   (record)    │  │  (interface)  │
    └───────────────┘  └───────────────┘  └───────────────┘
            │                  │                  │
            └──────────────────┼──────────────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Library        │
                    │      (class)        │
                    │  contains items     │
                    └─────────────────────┘
 */
