package Printing;

import Employees.Employee;
import Enums.PaperSizeEnum;
import Enums.PaperTypeEnum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrintingHouse {
    private List<Employee> employees;
    private List<PrintingMachine> machines;
    private List<Publication> publications;
    private List<Paper> papers;
    double expenses;
    double revenue;

    public PrintingHouse() {
        this.employees = new ArrayList<>();
        this.machines = new ArrayList<>();
        this.publications = new ArrayList<>();
        this.papers = new ArrayList<>();
        this.expenses = 0;
        this.revenue = 0;
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
        double paperExpenses = machines.stream().mapToDouble(PrintingMachine::calculatePaperCosts()).sum();
        this.expenses = salaryExpenses + paperExpenses;
        return this.expenses;
    }

    public double calculateRevenue(int copies, double discount) {
        double pricePerCopy = 5; // Example price per copy
        double totalRevenue = copies * pricePerCopy * (1 - discount / 100);
        this.revenue += totalRevenue;
        return this.revenue;
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Revenue: " + this.revenue + "\n");
            writer.write("Expenses: " + this.expenses + "\n");
            for (Publication publication : publications) {
                writer.write(publication.getTitle() + ", " + publication.getNumPages() + " pages\n");
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            this.revenue = Double.parseDouble(reader.readLine().split(": ")[1]);
            this.expenses = Double.parseDouble(reader.readLine().split(": ")[1]);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String title = parts[0];
                int pages = Integer.parseInt(parts[1].split(" ")[0]);
                Paper paper = new Paper(PaperTypeEnum.REGULAR, PaperSizeEnum.A4);
                Publication publication = new Publication(title, pages, paper);
                this.publications.add(publication);
            }
        }
    }

    public List<Employee> getEmployees(){
        return employees;
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

