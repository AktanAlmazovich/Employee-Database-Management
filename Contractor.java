package com.example.employee_managment_system;
public class Contractor extends Employee {
    private double hourlyRate;
    private int maxHours;

    public Contractor(String name, double hourlyRate, int maxHours) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * maxHours; // Fixed monthly hours multiplied by rate
    }

    @Override
    public String toString() {
        return "Contractor: " + getName() + ", Salary: " + calculateSalary();
    }
}
