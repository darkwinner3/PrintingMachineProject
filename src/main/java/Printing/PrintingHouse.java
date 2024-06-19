package Printing;

import Employees.Employee;
import Employees.Manager;
import Enums.PaperSizeEnum;
import Enums.PaperTypeEnum;
import Exception.CustomCheckedException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrintingHouse {
    private List<Employee> employees;
    private List<PrintingMachine> machines;
    private List<Publication> publications;
    private List<Paper> papers;
    private double expenses;
    private double revenue;

    public PrintingHouse() {
        this.employees = new ArrayList<>();
        this.machines = new ArrayList<>();
        this.publications = new ArrayList<>();
        this.papers = new ArrayList<>();
        this.expenses = 0.0;
        this.revenue = 0.0;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addMachine(PrintingMachine machine) {
        machines.add(machine);
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public void addPaper(Paper paper) {
        papers.add(paper);
    }

    public double calculateExpenses() {
        double salaryExpenses = employees.stream().mapToDouble(Employee::getSalary).sum();
        double paperExpenses = machines.stream().mapToDouble(PrintingMachine::calculatePaperCosts).sum();
        this.expenses = salaryExpenses + paperExpenses;
        return this.expenses;
    }

    public double calculateRevenue(int copies, double discount) {
        double pricePerCopy = 10; // Example price per copy
        double totalRevenue = copies * pricePerCopy * (1 - discount / 100);
        this.revenue += totalRevenue;
        return Double.parseDouble(String.format("%.2f", this.revenue));
    }

    public void saveToFile(String filename) throws CustomCheckedException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Revenue: " + String.format("%.2f", this.revenue) + "\n");
            writer.write("Expenses: " + String.format("%.2f", this.expenses) + "\n");
            for (Publication publication : publications) {
                writer.write(publication.getTitle() + ", " + publication.getNumPages() + " pages\n");
            }
        }catch (IOException e){
            throw new CustomCheckedException("Could not save data to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) throws CustomCheckedException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            this.revenue = Double.parseDouble(reader.readLine().split(": ")[1]);
            this.expenses = Double.parseDouble(reader.readLine().split(": ")[1]);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String title = parts[0];
                int pages = Integer.parseInt(parts[1].split(" ")[0]);
                Publication publication = new Publication(title, pages);
                this.publications.add(publication);
            }
        }
        catch (IOException e){
            throw new CustomCheckedException("Could not load data from file: " + e.getMessage());
        }
    }

    public List<Manager> getManagers(){
        List<Manager> managers = new ArrayList<>();

        for (Employee employee : employees){
            if (employee instanceof Manager){
                managers.add((Manager) employee);
            }
        }

        return managers;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getExpenses() {
        return expenses;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public List<PrintingMachine> getMachines() {
        return machines;
    }
}

