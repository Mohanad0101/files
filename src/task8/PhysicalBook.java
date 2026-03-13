package task8;

// Physical book record
public record PhysicalBook(Book book, String shelf) implements LibraryItem {

    public PhysicalBook {
        if (shelf == null || shelf.trim().isEmpty()) {
            throw new IllegalArgumentException("Полка не может быть пустой");
        }
    }

    @Override
    public String getInfo() {
        return String.format("[ФИЗИЧЕСКАЯ] %s | Полка: %s",
                formatBookInfo(), shelf);
    }

    private String formatBookInfo() {
        return String.format("%s - %s (%d, %s) - %.2f руб.",
                book.title(), book.author(), book.year(),
                book.genre().getRussianName(), book.price());
    }
}