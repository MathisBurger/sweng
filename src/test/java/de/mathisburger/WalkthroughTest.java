package de.mathisburger;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class WalkthroughTest {

    @Test
    public void testWalkthrough() {
        MaterialTyp typ = new MaterialTyp(1, "Beschreibung", 10, 5);
        assertSame(typ.getMaterialNr(), 1L);
        assertSame(typ.getBeschreibung(), "Beschreibung");
        assertSame(typ.getMinimalLagernd(), 10L);
        assertSame(typ.getKritischerBestand(), 5L);

        typ.setKritischerBestand(1L);
        assertSame(typ.getKritischerBestand(), 1L);
        typ.setMinimalLagernd(3L);
        assertSame(typ.getMinimalLagernd(), 3L);

        assertSame(typ.getState(), State.Locked);
        typ.lieferungEinlagern(this.getMockExemplare(10));
        assertSame(typ.getAnzahl(), 10L);
        assertSame(typ.getState(), State.InStock);

        typ.entnahmeDurchfuehren(8);
        assertSame(typ.getAnzahl(), 2L);
        assertSame(typ.getState(), State.MinimalStock);

        typ.lieferungEinlagern(this.getMockExemplare(2));
        assertSame(typ.getAnzahl(), 4L);
        assertSame(typ.getState(), State.InStock);

        assertThrows(RuntimeException.class, () -> typ.entnahmeDurchfuehren(10));
        assertThrows(RuntimeException.class, () -> typ.entnahmeDurchfuehren(-10));
        typ.entnahmeDurchfuehren(4);
        assertSame(typ.getAnzahl(), 0L);
        assertSame(typ.getState(), State.Critial);

        typ.lieferungEinlagern(this.getMockExemplare(1));
        assertThrows(RuntimeException.class, () -> typ.entnahmeDurchfuehren(1));
    }


    private Collection<MaterialExemplar> getMockExemplare(int amount) {
        Collection<MaterialExemplar> exemplares = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            exemplares.add(new MaterialExemplar("Ingolstadt Station City", i));
        }
        return exemplares;
    }
}
