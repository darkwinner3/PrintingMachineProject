package Employees;

public class Employee {
    protected static final double BASE_SALARY = 2000;
    private String name;
    private double salary;

    public Employee(String name) {
        this.name = name;
        this.salary = BASE_SALARY;
    }

    public double getSalary() {
        return salary;
    }

    protected void setSalary(double salary){
        this.salary = salary;
    }
}

