package task2_3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Interface with default, static, and private methods
interface Loggable {

    // Abstract method - must be implemented by classes
    String getComponentName();

    // Private method (Java 9+) - formats current time
    private String formatTimestamp() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }

    // Default method - logs regular message
    default void log(String message) {
        System.out.println("[" + formatTimestamp() + "] [" + getComponentName() + "] " + message);
    }

    // Default method - logs error message with prefix
    default void logError(String message) {
        System.out.println("[" + formatTimestamp() + "] [" + getComponentName() + "] ОШИБКА: " + message);
    }

    // Static method - returns log level
    static String getLogLevel() {
        return "INFO";
    }
}