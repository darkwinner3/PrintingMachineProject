package Employees;

public class Manager extends Employee {
    private double bonusPercentage;

    public Manager(String name, double bonusPercentage) {
        super(name);
        this.bonusPercentage = bonusPercentage;
    }

    public void calculateBonus(double revenue, double threshold) {
        if (revenue > threshold) {
            double bonus = getSalary() * (bonusPercentage / 100);
            setSalary(getSalary() + bonus);
        }
    }
}