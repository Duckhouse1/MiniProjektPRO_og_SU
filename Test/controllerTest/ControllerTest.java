package controllerTest;

import controller.Controller;
import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private PN pn;
    private Laegemiddel laegemiddel;
    private Patient patient;
    private Controller controller;
    private DagligFast dagligFast;
    private DagligSkaev dagligSkaev;
    private LocalDate startDato = LocalDate.of(2024, 03, 01);
    private LocalDate slutDato = LocalDate.of(2024, 03, 11);

    @BeforeEach
    void setUp() {
        LocalDate startDato = LocalDate.of(2024, 03, 01);
        LocalDate slutDato = LocalDate.of(2024, 03, 11);
        controller = Controller.getTestController();
        patient = controller.opretPatient("1201992059", "Dennis", 77.6);
        laegemiddel = controller.opretLaegemiddel("Acetylsalicylsyre", 10, 20, 30, "stk");
        pn = controller.opretPNOrdination(startDato, slutDato, patient, laegemiddel, 5);
        dagligFast = controller.opretDagligFastOrdination(startDato,slutDato,patient,laegemiddel,2,3,0,1);

    }

    @Test
    void TC1opretPNOrdinationTest() {
        PN nypn = controller.opretPNOrdination(LocalDate.of(2024, 03, 12), LocalDate.of(2024, 03, 22),
                patient, laegemiddel, 3);
        assertTrue(patient.getOrdinationList().contains(nypn));
    }
    @Test
    void TC2ExcepionopretPNOrdinationTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PN nypn = controller.opretPNOrdination(LocalDate.of(2024, 03, 24), LocalDate.of(2024, 03, 22),
                    patient, laegemiddel, 3);
        });

        assertEquals("Start dato skal komme før slut dato",exception.getMessage());
    }

    @Test
    void TC1opretDagligFastOrdination() {
        DagligFast nyDagligFast = controller.opretDagligFastOrdination(startDato,slutDato,patient,laegemiddel,
                2,3,0,1);
        assertTrue(patient.getOrdinationList().contains(nyDagligFast));
    }
    @Test
    void TC2ExceptionOpretDagligFastOrdination() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DagligFast nyDagligFast = controller.opretDagligFastOrdination(startDato.plusDays(12),slutDato,patient,laegemiddel,
                    2,3,0,1);
        });

        assertEquals("start dato skal være før slut dato",exception.getMessage());
    }
    @Test
    void TC1opretDagligSkaevOrdination() {
        LocalTime tid1 = LocalTime.of(07,00);
        LocalTime tid2 = LocalTime.of(10,00);
        LocalTime tid3 = LocalTime.of(13,30);
        LocalTime tid4 = LocalTime.of(16,00);

        double tal[] = {2.0,4.0,1.0,2.0};

        LocalTime[] klokkeslet = {tid1,tid2,tid3,tid4};
        DagligSkaev nyDagligSkæv = controller.opretDagligSkaevOrdination(startDato,slutDato,patient,laegemiddel,klokkeslet, tal);

        assertTrue(patient.getOrdinationList().contains(nyDagligSkæv));
    }
    @Test
    void TC2ExceptionopretDagligSkaevOrdination() {
        LocalTime tid1 = LocalTime.of(07,00);
        LocalTime tid2 = LocalTime.of(10,00);
        LocalTime tid3 = LocalTime.of(13,30);
        LocalTime tid4 = LocalTime.of(16,00);
        LocalTime[] klokkeslet = {tid1,tid2,tid3,tid4};

        double tal[] = {2.0,4.0,1.0,2.0};
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            DagligSkaev nyDagligSkæv = controller.opretDagligSkaevOrdination(startDato.plusDays(12),slutDato,patient,laegemiddel,klokkeslet, tal);
        });
        assertEquals("Start dato skal være før slut dato",exception.getMessage());
    }

    @Test
    void ordinationPNAnvendt() {
        LocalDate valgtDag = LocalDate.of(2024, 03, 01);
        controller.ordinationPNAnvendt(pn, valgtDag);

        assertTrue(pn.getGemteDatoer().contains(valgtDag));
    }
    @Test
    void anbefaletDosisPrDoegn() {
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
        double result = controller.antalOrdinationerPrVægtPrLægemiddel(-1, 100, laegemiddel);
        assertEquals(2, result);
    }
}