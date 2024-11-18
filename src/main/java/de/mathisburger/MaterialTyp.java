package de.mathisburger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MaterialTyp {

    private final long materialNr;

    private String beschreibung;

    private long minimalLagernd;

    private long kritischerBestand;

    private State state;

    /**
     * This is not used in the current state machine implementation.
     */
    private final Collection<MaterialExemplar> exemplare;

    public MaterialTyp(long materialNr, String beschreibung, long minimalLagernd, long kritischerBestand) {

        if (kritischerBestand >= minimalLagernd) {
            throw new RuntimeException("Der kritische Bestand muss kleiner sein, als der minimal lagernde bestand");
        }

        this.materialNr = materialNr;
        this.beschreibung = beschreibung;
        this.minimalLagernd = minimalLagernd;
        this.kritischerBestand = kritischerBestand;
        this.exemplare = new ArrayList<MaterialExemplar>();
        this.state = State.Locked;
    }

    public long getAnzahl() {
        return this.exemplare.size();
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

    public long getMaterialNr() {
        return materialNr;
    }

    public long getMinimalLagernd() {
        return minimalLagernd;
    }

    public void setMinimalLagernd(long minimalLagernd) {
        this.minimalLagernd = minimalLagernd;
    }

    public long getKritischerBestand() {
        return kritischerBestand;
    }

    public void setKritischerBestand(long kritischerBestand) {
        this.kritischerBestand = kritischerBestand;
    }

    public State getState() {
        return state;
    }

    public String toString() {
        return "Material: " + this.materialNr + "\nBeschreibung: " + this.beschreibung;
    }

    private void setState(State state) {
        this.state = state;
    }

    // --------------- Functionality implementations here

    public void lieferungEinlagern(Collection<MaterialExemplar> materialExemplare) {
        this.exemplare.addAll(materialExemplare);
        this.setStateAfterDelivery();
    }

    public void entnahmeDurchfuehren(long entnahmeAnzahl) {
        if (entnahmeAnzahl <= 0) {
            throw new RuntimeException("Negative Zahlen oder 0 sind nicht erlaubt");
        }
        if (entnahmeAnzahl > this.getAnzahl()) {
            throw new RuntimeException("So viel haben wir nicht im Bestand");
        }

        // Withdrawing items from stock is only allowed in "in stock" state
        if (this.state != State.InStock) {
            throw new RuntimeException("Es kann nichts aus dem Bestand entnommen werden");
        }

        // Removes material from the stock
        // NOTE: We need to use the approach of storing everything into a collection here, because
        //       of the internal comodification checker of java itself.
        Iterator<MaterialExemplar> iterator = this.exemplare.iterator();
        Collection<MaterialExemplar> toRemove = new ArrayList<MaterialExemplar>();
        for (int i=0; i<entnahmeAnzahl; i++) {
            toRemove.add(iterator.next());
        }
        this.exemplare.removeAll(toRemove);

        // Logic to check whether a state change is required
        if (this.getAnzahl() <= this.minimalLagernd && this.getAnzahl() > this.kritischerBestand) {
            this.setState(State.MinimalStock);
            this.placeRestockOrder();
        }
        if (this.getAnzahl() < this.kritischerBestand) {
            this.sendCriticalMail();
            this.placeRestockOrder();
            this.setState(State.Critial);
        }

    }

    /**
     * Sends the mail to notify someone about the critical stock
     */
    private void sendCriticalMail() {
        System.out.println("Wir befinden uns im kritischen Bestand!!!!");
        System.out.println(this);
    }

    /**
     * Prints the message that a restocking order has been placed.
     */
    private void placeRestockOrder() {
        System.out.println("Es wurde Material nachbestellt");
        System.out.println(this);
    }

    /**
     * Sets the state of the machine after a delivery conditionally depending on the current state and the current stock.
     */
    private void setStateAfterDelivery() {
        if (this.state == State.Critial && this.getAnzahl() > this.kritischerBestand) {
            this.setState(State.Locked);
        }
        if (this.state == State.MinimalStock && this.getAnzahl() > this.minimalLagernd) {
            this.setState(State.Locked);
        }
        if (this.state == State.Locked && this.getAnzahl() > this.minimalLagernd) {
            this.setState(State.InStock);
        }
    }
}
