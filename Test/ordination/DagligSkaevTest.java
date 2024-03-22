package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    @Test
    void DagligSkaev(){
        Laegemiddel l1 = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkaev dskæv = new DagligSkaev(l1, LocalDate.of(2024,03,22),LocalDate.of(2024,03,22));


        assertEquals("Skæv",dskæv.getType());
        assertEquals(LocalDate.of(2024,03,22),dskæv.getStartDen());
        assertEquals(l1,dskæv.getLaegemiddel());


    }

    @Test
    void opretDosis() {
        Laegemiddel l1 = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkaev dskæv = new DagligSkaev(l1, LocalDate.of(2024,03,22),LocalDate.of(2024,03,22));
        dskæv.opretDosis(LocalTime.of(22,30),2);

        assertEquals(2,dskæv.getDosis().get(0).getAntal());
        assertEquals(LocalTime.of(22,30),dskæv.getDosis().get(0).getTid());

        assertThrows(RuntimeException.class, () -> dskæv.opretDosis(LocalTime.of(21, 00), 0));


    }

    @Test
    void samletDosis() {
        Laegemiddel l1 = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkaev dskæv = new DagligSkaev(l1, LocalDate.of(2024,03,22),LocalDate.of(2024,03,22));

        dskæv.opretDosis(LocalTime.of(13,30),1);
        dskæv.opretDosis(LocalTime.of(19,30),1);
        dskæv.opretDosis(LocalTime.of(22,30),2);

        assertEquals(4,dskæv.samletDosis());

        Laegemiddel l2 =new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligSkaev dskæv2 = new DagligSkaev(l2, LocalDate.of(2024,03,22),LocalDate.of(2024,03,22));

        assertThrows(RuntimeException.class, () -> dskæv2.opretDosis(LocalTime.of(13, 30), -1));


    }

    @Test
    void doegnDosis() {
        ArrayList<Dosis> doser = new ArrayList<>();

        Laegemiddel l1 = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkaev dskæv = new DagligSkaev(l1, LocalDate.of(2024,03,22),LocalDate.of(2024,03,22));

        dskæv.opretDosis(LocalTime.of(13,30),1);
        dskæv.opretDosis(LocalTime.of(19,30),1);
        dskæv.opretDosis(LocalTime.of(22,30),2);
        assertEquals(4,dskæv.samletDosis());

        assertEquals(4,dskæv.doegnDosis());
    }
}