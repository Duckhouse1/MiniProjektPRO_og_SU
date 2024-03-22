package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    private Laegemiddel laegemiddel;
    private LocalDate startDen;
    private LocalDate slutDen;
    private DagligFast ordination;

    @BeforeEach
    public void setUp() {
        laegemiddel = new Laegemiddel("Ketamin", 1.0, 2.0, 3.0, "mg");
        startDen = LocalDate.of(2023, 1, 1);
        slutDen = LocalDate.of(2023, 1, 3);
    }

    @Test
    void samletDosis() {
        double morgenAntal = 3.0;
        double middagAntal = 0.0;
        double aftenAntal = 2.0;
        double natAntal = 0.0;

        long days = ChronoUnit.DAYS.between(startDen, slutDen)+1;
        DagligFast ordination = new DagligFast(laegemiddel, startDen, slutDen, morgenAntal, middagAntal, aftenAntal, natAntal);

        double expected = (morgenAntal + middagAntal + aftenAntal + natAntal) * days;
        double actual = ordination.samletDosis();

        assertEquals(expected, actual);
    }

    @Test
    void doegnDosis() {
        double morgenAntal = 3.0;
        double middagAntal = 0.0;
        double aftenAntal = 2.0;
        double natAntal = 0.0;

        DagligFast ordination = new DagligFast(laegemiddel, startDen, slutDen, morgenAntal, middagAntal, aftenAntal, natAntal);
        double expected = morgenAntal + middagAntal + aftenAntal + natAntal;
        double actual = ordination.doegnDosis();

        assertEquals(expected, actual);
    }
}