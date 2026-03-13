package Task2_1;

public abstract class Employee {

   // Fields: name(protected), baseSalary(protected)
protected String name;

    abstract Double calculateBonus();

   //Normal method double totalCompensation()- returnsbaseSalary + calculateBonus()
   public Double totalCompensation(){
      return baseSalary + calculateBonus();
   }
   // Override toString() with required format
   @Override
   public String toString() {
      double bonus = calculateBonus();  // Get bonus from concrete class
      double total = baseSalary + bonus;
      return String.format("%s | Оклад: %.1f | Бонус: %.1f | Итого: %.1f",
              name, baseSalary, bonus, total);
   }


   protected Double baseSalary;
public Employee(String n, Double sal){
   name=n;
   baseSalary=sal;
}

   public Double getBaseSalary() {
      return baseSalary;
   }

   public String getName() {
      return name;
   }
}
