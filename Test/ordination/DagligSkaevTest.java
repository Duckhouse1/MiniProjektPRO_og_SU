package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    @Test
    void opretDosis() {
        Laegemiddel l1 = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkaev dskæv = new DagligSkaev(l1, LocalDate.of(2024,03,22),LocalDate.of(2024,03,22));
        Patient patient = new Patient("121256-0512", "Jane Jensen", 63.4);

        assertEquals(dskæv,dskæv);

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