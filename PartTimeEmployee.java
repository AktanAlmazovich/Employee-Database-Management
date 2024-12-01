public class PartTimeEmployee extends Employee {
    private double hourlyWage;
    private int hoursWorked;

    public PartTimeEmployee(String name, double hourlyWage, int hoursWorked) {
        super(name);
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyWage * hoursWorked; // Salary based on hours worked
    }

    @Override
    public String toString() {
        return "PartTimeEmployee: " + getName() + ", Salary: " + calculateSalary();
    }
}
