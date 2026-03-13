public class Developer  extends Employee{


    private String language;

    public Developer(String n, Double sal,String lang) {
        super(n, sal);
        this.language=lang;
    }

    @Override
    Double calculateBonus() {
        return baseSalary * 0.12;
    }
    public String getLanguage() {
        return language;
    }
}
