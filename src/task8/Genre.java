package task8;

// Enum Genre with Russian names
public enum Genre {
    FICTION("Художественная литература"),
    SCIENCE("Наука"),
    HISTORY("История"),
    PROGRAMMING("Программирование"),
    ART("Искусство");

    private final String russianName;

    Genre(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }

    // Static method to find genre by Russian name
    public static Genre fromString(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.russianName.equalsIgnoreCase(name) ||
                    genre.name().equalsIgnoreCase(name)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Неизвестный жанр: " + name);
    }
}
