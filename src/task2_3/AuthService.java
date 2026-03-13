package task2_3;

// AuthService implementation
class AuthService implements Loggable {

    @Override
    public String getComponentName() {
        return "AuthService";
    }

    // Method to handle login attempts
    public void login(String username, boolean success) {
        if (success) {
            log("Вход пользователя: " + username + " — успешно");
        } else {
            logError("Вход пользователя: " + username + " — отказано");
        }
    }
}