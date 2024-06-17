package Enums;

public enum PaperSizeEnum {
    A5(0.5),
    A4(1.0),
    A3(1.5),
    A2(2.0),
    A1(3.0);

    private final double sizeMultiplier;

    PaperSizeEnum(double sizeMultiplier){
        this.sizeMultiplier = sizeMultiplier;
    }

    public double getSizeMultiplier(){
        return sizeMultiplier;
    }
}
