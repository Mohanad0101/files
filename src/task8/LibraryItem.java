package task8;

// Sealed interface for library items
public sealed interface LibraryItem permits PhysicalBook, EBook {
    String getInfo();
}