package part3.part3_2;

public class BankAccountDemo {
    private static String bankName;

    static {
        bankName = "Java Bank";
        System.out.println("Банковская система инициализирована");
    }

    public static void main(String[] args) {
        System.out.println("Банк: " + bankName);
        BankAccount acc = new BankAccount("RU-123", "Ivan Petrov", "1234", 120_000);
        System.out.println(acc);
        System.out.println("Снятие (неверный PIN): " + acc.withdraw("1111", 1000));
        System.out.println("Снятие (верный PIN): " + acc.withdraw("1234", 1000));
        System.out.println(acc);
    }
}
