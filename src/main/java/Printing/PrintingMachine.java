package Printing;

import Enums.MachineColorModeEnum;

import java.util.ArrayList;
import java.util.List;

public class PrintingMachine {
    private int maxSheets;
    private MachineColorModeEnum colorMode;
    private int loadedSheets;
    private int printedPages;

    public PrintingMachine(int maxSheets, MachineColorModeEnum colorMode) {
        this.maxSheets = maxSheets;
        this.colorMode = colorMode;
        this.loadedSheets = 0;
        this.printedPages = 0;
    }

    public void loadPaper(int sheets) throws Exception {
        if (sheets > maxSheets) {
            throw new Exception("Cannot load more sheets than the maximum capacity.");
        }
        this.loadedSheets += sheets;
    }

    public void printPublication(Publication publication, int copies, MachineColorModeEnum colorMode) throws Exception {
        if (!this.colorMode.equals(colorMode)) {
            throw new Exception("Color mode mismatch.");
        }
        int requiredSheets = copies * publication.getNumPages();
        if (requiredSheets > this.loadedSheets) {
            throw new Exception("Not enough paper loaded.");
        }
        this.loadedSheets -= requiredSheets;
        this.printedPages += requiredSheets;
    }

    public int getTotalPrintedPages() {
        return printedPages;
    }

    public int getTotalLoadedPages(){
        return  loadedSheets;
    }

    public double calculatePaperCosts(List<Publication> publications) {
        return publications.stream()
                .mapToDouble(p -> p.getCopyCount() * p.getNumPages() * p.getPaper().getPricePerSheet())
                .sum();
    }
}
