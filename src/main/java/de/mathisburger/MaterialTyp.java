package de.mathisburger;

import java.util.ArrayList;
import java.util.Collection;

public class MaterialTyp {

    private long materialNr;

    private String beschreibung;

    private boolean minimalLagernd;

    private long anzahl;

    private Collection<MaterialExemplar> exemplare;

    public MaterialTyp() {
        this.materialNr = 0;
        this.beschreibung = "";
        this.minimalLagernd = true;
        this.anzahl = 0;
        this.exemplare = new ArrayList<MaterialExemplar>();
    }

    public long getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(long anzahl) {
        this.anzahl = anzahl;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Collection<MaterialExemplar> getExemplare() {
        return exemplare;
    }

    public void setExemplare(Collection<MaterialExemplar> exemplare) {
        this.exemplare = exemplare;
    }

    public void addExemplar(MaterialExemplar exemplar) {
        this.exemplare.add(exemplar);
    }

    public long getMaterialNr() {
        return materialNr;
    }

    public void setMaterialNr(long materialNr) {
        this.materialNr = materialNr;
    }

    public boolean isMinimalLagernd() {
        return minimalLagernd;
    }

    public void setMinimalLagernd(boolean minimalLagernd) {
        this.minimalLagernd = minimalLagernd;
    }
}
