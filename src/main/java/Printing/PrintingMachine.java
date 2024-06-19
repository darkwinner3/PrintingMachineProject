package Printing;

import Enums.MachineColorModeEnum;
import Exception.CustomCheckedException;

import java.util.ArrayList;
import java.util.List;

public class PrintingMachine {
    private int maxSheets;
    private MachineColorModeEnum colorMode;
    private int loadedSheets;
    private int printedPages;
    private List<Publication> publications;

    public PrintingMachine(int maxSheets, MachineColorModeEnum colorMode) {
        this.maxSheets = maxSheets;
        this.colorMode = colorMode;
        this.loadedSheets = 0;
        this.printedPages = 0;
        this.publications = new ArrayList<>();
    }

    public void loadPaper(int sheets) throws CustomCheckedException {
        if (sheets > maxSheets) {
            throw new CustomCheckedException("Cannot load more sheets than the maximum capacity.");
        }
        this.loadedSheets += sheets;
    }

    public void printPublication(Publication publication, int copies, MachineColorModeEnum colorMode) throws CustomCheckedException {
        if (!this.colorMode.equals(colorMode)) {
            throw new CustomCheckedException("Color mode mismatch.");
        }
        int copyCount = 0;
        for (int i = 0; i < copies; i++){
            int requiredSheets = publication.getNumPages();

            if (requiredSheets > this.loadedSheets){
                System.err.println("Not enough paper loaded for more than " + copyCount + " copies of " + publication.getTitle() + ".");
                break;
            }
            else {
                this.loadedSheets -= requiredSheets;
                this.printedPages += requiredSheets;
                copyCount++;
            }
        }
        if (copyCount > 0){
            publication.setPublicationCopyCount(copyCount);
            this.publications.add(publication);
        }
    }

    public int getTotalPrintedPages() {
        return printedPages;
    }

    public int getTotalLoadedPages(){
        return  loadedSheets;
    }

    public double calculatePaperCosts() {
        return publications.stream()
                .mapToDouble(p -> p.getCopyCount() * p.getNumPages() * p.getPaper().getPricePerSheet())
                .sum();
    }
}
