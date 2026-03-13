package Task2_1;

public class Manager extends Employee{


    private int teamSize;


    public Manager(String n, Double sal,int ts) {
        super(n, sal);
        this.teamSize=ts;
    }

    @Override
    Double calculateBonus() {
        return baseSalary * 0.15 + teamSize * 5000;
    }

    public int getTeamSize() {
        return teamSize;
    }
}
