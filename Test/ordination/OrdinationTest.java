package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {


    @Test
    void antalDage(){
        LocalDate dagStart1 = LocalDate.of(2024,03,20);
        LocalDate dagSlut2 = LocalDate.of(2024,03,24);

        DagligSkaev d1 = new DagligSkaev(null,dagStart1,dagSlut2);

        assertEquals(5,d1.antalDage());


        DagligSkaev d2 = new DagligSkaev(null,dagSlut2,dagStart1);
        assertThrows(RuntimeException.class,() -> d2.antalDage());
    }
}