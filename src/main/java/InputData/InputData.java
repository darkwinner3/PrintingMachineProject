package InputData;

import Employees.Employee;
import Employees.Manager;
import Enums.MachineColorModeEnum;
import Enums.PaperSizeEnum;
import Enums.PaperTypeEnum;
import Printing.Paper;
import Printing.PrintingHouse;
import Printing.PrintingMachine;
import Printing.Publication;
import Exception.CustomCheckedException;

public class InputData {
    public void initializePrintingHouse(PrintingHouse printingHouse) throws CustomCheckedException {
        initializeMachines(printingHouse);
        initializePapers(printingHouse);
        initializePublications(printingHouse);
        initializeEmployees(printingHouse);
    }

    private void initializeMachines(PrintingHouse printingHouse) throws CustomCheckedException {
        PrintingMachine machine = new PrintingMachine(5000, MachineColorModeEnum.COLOR);
        PrintingMachine machine2 = new PrintingMachine(6500, MachineColorModeEnum.GRAY);

        try{
            machine.loadPaper(2000);
            machine2.loadPaper(2000);
        }catch (CustomCheckedException e){
            throw new CustomCheckedException(e.getMessage());
        }

        printingHouse.addMachine(machine);
        printingHouse.addMachine(machine2);
    }

    private void initializePublications(PrintingHouse printingHouse){
        Paper paper1 = printingHouse.getPapers().get(0);
        Paper paper2 = printingHouse.getPapers().get(1);

        Publication publication1 = new Publication("Sample Book", 350);
        publication1.setPaper(paper1);
        Publication publication2 = new Publication("Sample Magazine", 100);
        publication2.setPaper(paper2);

        printingHouse.addPublication(publication1);
        printingHouse.addPublication(publication2);
    }

    private void initializeEmployees(PrintingHouse printingHouse){
        Manager manager = new Manager("Jane Doe", 10);
        Manager manager2 = new Manager("Test Manager 2", 5);
        Employee operator = new Employee("John Shoe");

        printingHouse.addEmployee(manager);
        printingHouse.addEmployee(manager2);
        printingHouse.addEmployee(operator);
    }

    private void initializePapers(PrintingHouse printingHouse){
        Paper paper = new Paper(PaperTypeEnum.REGULAR, PaperSizeEnum.A4);
        Paper paper2 = new Paper(PaperTypeEnum.GLOSSY, PaperSizeEnum.A5);

        printingHouse.addPaper(paper);
        printingHouse.addPaper(paper2);
    }

    public void simulatePrintingHouse(PrintingHouse printingHouse) {
        try {
            PrintingMachine machine = printingHouse.getMachines().get(0);
            PrintingMachine machine2 = printingHouse.getMachines().get(1);

            Publication publication1 = printingHouse.getPublications().get(0);
            Publication publication2 = printingHouse.getPublications().get(1);

            machine.printPublication(publication1, 5, MachineColorModeEnum.COLOR);
            machine2.printPublication(publication2, 5, MachineColorModeEnum.GRAY);

            double revenue1 = printingHouse.calculateRevenue(publication1.getCopyCount(), 1.00);
            double revenue2 = printingHouse.calculateRevenue(publication2.getCopyCount(), 0.15);

            double revenueSum = revenue1 + revenue2;

            Manager manager = printingHouse.getManagers().getFirst();
            Manager manager2 = printingHouse.getManagers().get(1);
            manager.calculateBonus(revenueSum, 40);
            manager2.calculateBonus(revenueSum, 40);

            printingHouse.calculateExpenses();

            String filename = "test_output.txt";
            printingHouse.saveToFile(filename);
        } catch (CustomCheckedException e) {
            throw new RuntimeException(e);
        }
    }
}
