import static org.junit.jupiter.api.Assertions.*;

import Employees.Manager;
import Enums.MachineColorModeEnum;
import Enums.PaperSizeEnum;
import Enums.PaperTypeEnum;
import Printing.Paper;
import Employees.Employee;

import Printing.PrintingHouse;
import Printing.PrintingMachine;
import Printing.Publication;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UnitTests {

    @Test
    public void testPaperPrice() {
        Paper paper = new Paper(PaperTypeEnum.REGULAR, PaperSizeEnum.A4);
        assertEquals(0.1, paper.getPricePerSheet(), 0.001);
    }

    @Test
    public void testEmployeeSalary() {
        Employee operator = new Employee("John Doe");
        assertEquals(2000, operator.getSalary());
    }

    @Test
    public void testManagerBonus() {
        Manager manager = new Manager("Jane Doe", 10);
        double oldSalary = manager.getSalary();

        manager.calculateBonus(5000, 3000);
        double newSalary = manager.getSalary();

        double expectedNewSalary = oldSalary + (oldSalary * 0.10);

        assertEquals(expectedNewSalary, newSalary, 0.001);
    }

    @Test
    public void testPrintingMachineLoad() {
        PrintingMachine machine = new PrintingMachine(1000, MachineColorModeEnum.COLOR);
        try {
            machine.loadPaper(500);
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
        assertEquals(500, machine.getTotalLoadedPages());
    }

    @Test
    public void testPrintingMachinePrint() {
        PrintingMachine machine = new PrintingMachine(1000, MachineColorModeEnum.COLOR);
        Paper paper = new Paper(PaperTypeEnum.NEWSPAPER, PaperSizeEnum.A4);
        Publication publication = new Publication("Sample Book", 100, paper);
        try {
            machine.loadPaper(1000);
            machine.printPublication(publication, 5, MachineColorModeEnum.COLOR);
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
        assertEquals(500, machine.getTotalPrintedPages());
    }

    @Test
    public void testPaperExpenses() {
        PrintingHouse printingHouse = new PrintingHouse();
        PrintingMachine machine = new PrintingMachine(1000, MachineColorModeEnum.COLOR);
        Paper paper = new Paper(PaperTypeEnum.REGULAR, PaperSizeEnum.A4);
        printingHouse.addMachine(machine);

        Publication publication = new Publication("Sample Book", 100, paper);

        try {
            machine.loadPaper(1000);
            machine.printPublication(publication, 5, MachineColorModeEnum.COLOR);
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }

        assertEquals(50.0, printingHouse.calculateExpenses(printingHouse.getPublications()));  // 500 pages * 0.15 per sheet
    }

    @Test
    public void testCalculateRevenue() {
        PrintingHouse printingHouse = new PrintingHouse();
        Paper paper = new Paper(PaperTypeEnum.REGULAR, PaperSizeEnum.A4);
        Publication publication = new Publication("Sample Book", 100, paper);

        double revenue = printingHouse.calculateRevenue(10, 0);
        assertEquals(50.0, revenue, 0.001);

        revenue = printingHouse.calculateRevenue(10, 10);
        assertEquals(95.0, revenue, 0.001);
    }

    @Test
    public void testSaveToFile() throws IOException {
        PrintingHouse printingHouse = new PrintingHouse();
        Paper paper = new Paper(PaperTypeEnum.REGULAR, PaperSizeEnum.A4);
        Publication publication1 = new Publication("Sample Book", 100, paper);
        Publication publication2 = new Publication("Sample Magazine", 50, paper);

        printingHouse.addPublication(publication1);
        printingHouse.addPublication(publication2);

        printingHouse.calculateRevenue(10, 0);
        printingHouse.calculateExpenses(printingHouse.getPublications());

        String filename = "test_output.txt";
        printingHouse.saveToFile(filename);

        List<String> lines = Files.readAllLines(Paths.get(filename));
        assertEquals("Revenue: 50.0", lines.get(0));
        assertTrue(lines.get(1).startsWith("Expenses: "));
        assertEquals("Sample Book, 100 pages", lines.get(2));
        assertEquals("Sample Magazine, 50 pages", lines.get(3));

        // Clean up
        Files.delete(Paths.get(filename));
    }

    @Test
    public void testLoadFromFile() throws IOException {
        String filename = "test_input.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Revenue: 100.0\n");
            writer.write("Expenses: 50.0\n");
            writer.write("Sample Book, 100 pages\n");
            writer.write("Sample Magazine, 50 pages\n");
        }

        PrintingHouse printingHouse = new PrintingHouse();
        printingHouse.loadFromFile(filename);

        assertEquals(100.0, printingHouse.getRevenue(), 0.001);
        assertEquals(50.0, printingHouse.getExpenses(), 0.001);
        assertEquals(2, printingHouse.getPublications().size());
        assertEquals("Sample Book", printingHouse.getPublications().get(0).getTitle());
        assertEquals(100, printingHouse.getPublications().get(0).getNumPages());
        assertEquals("Sample Magazine", printingHouse.getPublications().get(1).getTitle());
        assertEquals(50, printingHouse.getPublications().get(1).getNumPages());

        // Clean up
        Files.delete(Paths.get(filename));
    }
}
