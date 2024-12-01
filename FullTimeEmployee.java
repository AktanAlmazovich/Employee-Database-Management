

public class FullTimeEmployee extends Employee {
    private double annualSalary;

    public FullTimeEmployee(String name, double annualSalary) {
        super(name);
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculateSalary() {
        return annualSalary;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee: " + getName() + ", Salary: " + calculateSalary();
    }
}
