package Printing;

import Employees.Employee;
import Enums.PaperSizeEnum;
import Enums.PaperTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paper {
    private PaperTypeEnum paperType;
    private PaperSizeEnum paperSize;
    private double pricePerSheet;


    public Paper(PaperTypeEnum paperType, PaperSizeEnum paperSize) {
        this.paperType = paperType;
        this.paperSize = paperSize;
        this.pricePerSheet = calculatePrice();

    }

    private double calculatePrice() {
        double basePrice = paperType.getBasePrice();
        double multiplier = paperSize.getSizeMultiplier();
        return basePrice * multiplier;
    }

    public double getPricePerSheet() {
        return pricePerSheet;
    }

    public PaperTypeEnum getPaperType(){
        return paperType;
    }

    public PaperSizeEnum getPaperSize() {
        return paperSize;
    }
}

