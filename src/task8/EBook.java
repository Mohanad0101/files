package task8;

// E-book record
public record EBook(Book book, String format, double sizeMB) implements LibraryItem {

    public EBook {
        if (format == null || format.trim().isEmpty()) {
            throw new IllegalArgumentException("Формат не может быть пустым");
        }
        if (sizeMB <= 0) {
            throw new IllegalArgumentException("Размер должен быть положительным");
        }
    }

    @Override
    public String getInfo() {
        return String.format("[ЭЛЕКТРОННАЯ] %s | Формат: %s, Размер: %.2f MB",
                formatBookInfo(), format, sizeMB);
    }

    private String formatBookInfo() {
        return String.format("%s - %s (%d, %s) - %.2f руб.",
                book.title(), book.author(), book.year(),
                book.genre().getRussianName(), book.price());
    }
}
