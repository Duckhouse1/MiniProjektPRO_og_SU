package ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligFast extends Ordination {

    double morgenAntal;
    double middagAntal;
    double aftenAntal;
    double natAntal;
    private Dosis[] dosis = new Dosis[4];

    public DagligFast(Patient patient, LocalDate startDen, LocalDate slutDen, double morgenAntal, double middagAntal, double aftenAntal, double natAntal, Dosis[] dosis) {
        super(patient, startDen, slutDen);
        dosis[0] = new Dosis(LocalTime.of(7,00),morgenAntal);
        dosis[1] = new Dosis(LocalTime.of(12,00),middagAntal);
        dosis[2] = new Dosis(LocalTime.of(18,00),aftenAntal);
        dosis[3] = new Dosis(LocalTime.of(00,00),natAntal);
        this.dosis = dosis;
    }

    /**
     * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
     *
     * @return
     */
    @Override
    public double samletDosis() {
        return super.antalDage()*doegnDosis();
    }

    /**
     * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen er gyldig
     *
     * @return
     */
    @Override
    public double doegnDosis() {
        double dagsdosis = 0;
        for (Dosis doser : dosis){
            dagsdosis += doser.getAntal();
        }
        return dagsdosis;
    }

    /**
     * Returnerer ordinationstypen som en String
     *
     * @return
     */
    @Override
    public String getType() {
        return "Fast";
    }
}
