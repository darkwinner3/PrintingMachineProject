package Enums;

public enum PaperTypeEnum {
    REGULAR(0.10),
    GLOSSY(0.20),
    NEWSPAPER(0.05);

    private final double basePrice;

    PaperTypeEnum(double basePrice){
        this.basePrice = basePrice;
    }

    public double getBasePrice(){
        return basePrice;
    }
}
