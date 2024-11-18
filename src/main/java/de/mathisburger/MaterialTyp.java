package de.mathisburger;

import java.util.ArrayList;
import java.util.Collection;

public class MaterialTyp {

    private final long materialNr;

    private String beschreibung;

    private long minimalLagernd;

    private long kritischerBestand;

    private long anzahl;

    private State state;

    /**
     * This is not used in the current state maschine implementation.
     */
    private Collection<MaterialExemplar> exemplare;

    public MaterialTyp(long materialNr, String beschreibung, long minimalLagernd, long anzahl, long kritischerBestand) {

        if (kritischerBestand >= minimalLagernd) {
            throw new RuntimeException("Der kritische Bestand muss kleiner sein, als der minimal lagernde bestand");
        }

        this.materialNr = materialNr;
        this.beschreibung = beschreibung;
        this.minimalLagernd = minimalLagernd;
        this.kritischerBestand = kritischerBestand;
        this.anzahl = anzahl;
        this.exemplare = new ArrayList<MaterialExemplar>();
        this.state = State.Locked;
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

    public long isMinimalLagernd() {
        return minimalLagernd;
    }

    public void setMinimalLagernd(long minimalLagernd) {
        this.minimalLagernd = minimalLagernd;
    }

    public long getKritischerBestand() {
        return kritischerBestand;
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public void setKritischerBestand(long kritischerBestand) {
        this.kritischerBestand = kritischerBestand;
    }

    // --------------- Functionality implementations here

    public void lieferungEinlagern(long anzahl) {
        if (anzahl <= 0) {
            throw new RuntimeException("Negative numbers not allowed");
        }
        this.anzahl += anzahl;
        if (this.anzahl > this.kritischerBestand) {
            this.setState(State.Locked);
        }
        if (this.anzahl > this.minimalLagernd) {
            this.setState(State.InStock);
        }
    }

    public void entnahmeDurchfuehren(long anzahl) {
        if (anzahl <= 0) {
            throw new RuntimeException("Negative numbers not allowed");
        }
        if (this.state != State.InStock) {
            throw new RuntimeException("Es kann nichts aus dem Bestand entnommen werden");
        }
        this.anzahl -= anzahl;
        if (this.anzahl <= this.minimalLagernd && this.anzahl > this.kritischerBestand) {
            this.state = State.MinimalStock;
            this.placeRestockOrder();
        }
        if (this.anzahl < this.kritischerBestand) {
            this.sendCriticalMail();
            this.placeRestockOrder();
            this.state = State.Critial;
        }

    }

    private void sendCriticalMail() {
        System.out.println("Wir befinden uns im kritischen Bestand!!!!");
    }

    private void placeRestockOrder() {
        System.out.println("Es wurde Material nachbestellt");
    }
}
