package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {
    @Test
    void PNConstructer(){
        Laegemiddel l1 = new Laegemiddel("MDMA",10,20,30,"Styk");
        LocalDate startDato = LocalDate.of(2024,03,01);
        LocalDate slutDato = LocalDate.of(2024,03,11);
        PN pn = new PN(6,l1,startDato,slutDato);

        assertEquals(6,pn.getAntalEnheder());
        assertEquals(startDato,pn.getStartDen());
        assertEquals(slutDato,pn.getSlutDen());
        assertEquals(l1,pn.getLaegemiddel());
    }
    @Test
    void givDosis() {
        Laegemiddel l1 =new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        LocalDate dagStart1 = LocalDate.of(2024,03,20);
        LocalDate dagSlut2 = LocalDate.of(2024,03,24);
        PN pn = new PN(2,l1,dagStart1,dagSlut2);

//        pn.givDosis(LocalDate.of(2024,03,21));

        assertEquals(true,pn.givDosis(LocalDate.of(2024,03,21)));

//        pn.givDosis(LocalDate.of(2024,03,25));

        assertEquals(false,pn.givDosis(LocalDate.of(2024,03,25)));

        assertEquals(false,pn.givDosis(LocalDate.of(2024,03,5)));
    }

    @Test
    void doegnDosis() {
        Laegemiddel l1 =new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        LocalDate dagStart1 = LocalDate.of(2024,03,20);
        LocalDate dagSlut2 = LocalDate.of(2024,03,24);
        PN pn = new PN(5,l1,dagStart1,dagSlut2);
        pn.givDosis(LocalDate.of(2024,03,20));
        pn.givDosis(LocalDate.of(2024,03,21));
        pn.givDosis(LocalDate.of(2024,03,22));

        assertEquals(5,pn.doegnDosis());

        pn.givDosis(LocalDate.of(2024,03,22));
        pn.givDosis(LocalDate.of(2024,03,23));

        assertEquals(6.25,pn.doegnDosis());

    }

    @Test
    void samletDosis() {
        Laegemiddel l1 =new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        LocalDate dagStart1 = LocalDate.of(2024,03,20);
        LocalDate dagSlut2 = LocalDate.of(2024,03,24);
        PN pn = new PN(5,l1,dagStart1,dagSlut2);
        pn.givDosis(LocalDate.of(2024,03,20));
        pn.givDosis(LocalDate.of(2024,03,21));
        pn.givDosis(LocalDate.of(2024,03,22));

        assertEquals(5,pn.getAntalEnheder());

        assertEquals(3,pn.getGemteDatoer().size());

        assertEquals(15,pn.samletDosis());

    }
}