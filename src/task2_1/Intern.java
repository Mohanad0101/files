package task2_1;

public class Intern extends Employee{

    public Intern(String n, Double sal) {
        super(n, sal);
    }

    @Override
    Double calculateBonus() {
        return 10000.0;
    }
}
