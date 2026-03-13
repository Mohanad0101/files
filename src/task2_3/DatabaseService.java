package task2_3;

// DatabaseService implementation
class DatabaseService implements Loggable {

    @Override
    public String getComponentName() {
        return "DatabaseService";
    }

    // Method to connect to database
    public void connect(String url) {
        log("Подключение к " + url);
        // Simulate connection logic
        log("Подключение установлено");
    }
}
