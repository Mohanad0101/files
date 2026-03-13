public class BankAccount {
    private String owner;
    private Double balance;
    private String accountNumber;
    private static int totalAccounts;
    private static String bankName;
    static{
        bankName = "Java Bank";
        System.out.println("Банковская система инициализирована");

    }

    {
        totalAccounts++;
    }
    BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
        this.accountNumber = "ACC-" + totalAccounts;
    }
    public void deposit(double amount){
        if (amount< balance){
        System.out.println("Ошибка: сумма должна быть положительной")   ;}
        else{
        balance=balance+amount;}
    }
    public void withdraw(double amount){
        if (balance >= amount)
        balance-= amount;
        else
        System.out.println("Ошибка: недостаточно средств");
    }
    public static int getTotalAccounts(){
        return totalAccounts;

    }

    public String toString() {
        return String.format("[%s] %s: %.2f руб.", accountNumber, owner, balance);
    }
    // Напишите весь класс самостоятельно

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount("Алиса", 1000);
        BankAccount a2 = new BankAccount("Борис", 500);

        System.out.println(a1);
        System.out.println(a2);

        a1.deposit(500);
        System.out.println("После пополнения: " + a1);

        a1.withdraw(200);
        System.out.println("После снятия: " + a1);

        a1.withdraw(5000);

        a2.deposit(-100);

        System.out.println("Всего счетов: " + BankAccount.getTotalAccounts());
    }
}

