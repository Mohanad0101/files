package task6_1b;
// Class RegistrationForm with annotated fields
class RegistrationForm {
    @NotEmpty(message = "Имя обязательно")
    private String name;

    @NotEmpty
    private String email;

    @Range(min = 18, max = 120, message = "Возраст должен быть от 18 до 120")
    private int age;

    // Constructor
    public RegistrationForm(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // Getters (optional, but good practice)
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}
