package de.mathisburger;

public class MaterialExemplar {

    private String lagerort;

    private long inventarNr;

    public MaterialExemplar(String lagerort, long inventarNr) {
        this.lagerort = lagerort;
        this.inventarNr = inventarNr;
    }

    public long getInventarNr() {
        return inventarNr;
    }

    public void setInventarNr(long inventarNr) {
        this.inventarNr = inventarNr;
    }

    public String getLagerort() {
        return lagerort;
    }

    public void setLagerort(String lagerort) {
        this.lagerort = lagerort;
    }
}
