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

    public DagligFast(Laegemiddel laegemiddel, LocalDate startDen, LocalDate slutDen, double morgenAntal, double middagAntal, double aftenAntal, double natAntal) {
        super(laegemiddel,startDen, slutDen);
        dosis[0] = new Dosis(LocalTime.of(7,00),this.morgenAntal = morgenAntal);
        dosis[1] = new Dosis(LocalTime.of(12,00),this.middagAntal = middagAntal);
        dosis[2] = new Dosis(LocalTime.of(18,00),this.aftenAntal = aftenAntal);
        dosis[3] = new Dosis(LocalTime.of(00,00),this.natAntal = natAntal);
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

    public Dosis[] getDosis() {
        return dosis;
    }

    @Override
    public LocalDate getStartDen() {
        return super.getStartDen();
    }

    @Override
    public LocalDate getSlutDen() {
        return super.getSlutDen();
    }

    @Override
    public Laegemiddel getLaegemiddel() {
        return super.getLaegemiddel();
    }

    public double getMorgenAntal() {
        return morgenAntal;
    }

    public double getMiddagAntal() {
        return middagAntal;
    }

    public double getAftenAntal() {
        return aftenAntal;
    }

    public double getNatAntal() {
        return natAntal;
    }
}
