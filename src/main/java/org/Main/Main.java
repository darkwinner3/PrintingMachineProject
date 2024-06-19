package org.Main;

import InputData.InputData;
import Printing.PrintingHouse;
import Exception.CustomCheckedException;

public class Main {

    public static void main(String[] args) throws CustomCheckedException {
        PrintingHouse printingHouse = new PrintingHouse();
        InputData inputData = new InputData();

        inputData.initializePrintingHouse(printingHouse);

        // Simulate the printing house operations
        inputData.simulatePrintingHouse(printingHouse);
    }

}