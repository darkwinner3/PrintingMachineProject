package org.Main;

import InputData.InputData;
import Printing.PrintingHouse;

public class Main {

    public static void main(String[] args) {
        PrintingHouse printingHouse = new PrintingHouse();
        InputData inputData = new InputData();

        // Initialize the printing house
        inputData.initializePrintingHouse(printingHouse);

        // Simulate the printing house operations
        inputData.simulatePrintingHouse(printingHouse);
    }

}