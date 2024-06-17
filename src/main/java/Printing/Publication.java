package Printing;

import Enums.PaperSizeEnum;

public class Publication {
    private String title;
    private int numPages;
    private Paper paper;
    private int copyCount;

    public Publication(String title, int numPages, Paper paper) {
        this.title = title;
        this.numPages = numPages;
        this.paper = paper;
        this.copyCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public int getNumPages() {
        return numPages;
    }

    public Paper getPaper() {
        return paper;
    }

    public int getCopyCount(){
        return copyCount;
    }
}

