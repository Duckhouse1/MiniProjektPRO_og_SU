package ordination;

import controller.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{
    private ArrayList<Dosis> dosis = new ArrayList<>();
    public DagligSkaev(Laegemiddel laegemiddel,LocalDate startDen, LocalDate slutDen) {
        super(laegemiddel,startDen, slutDen);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid,antal);
        this.dosis.add(dosis);
    }

    /**
     * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
     *
     * @return
     */
    @Override
    public double samletDosis() {
        double sum = 0;
        for (Dosis d : dosis){
            sum += d.getAntal();
        }
        return sum;
    }

    /**
     * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen er gyldig
     *
     * @return
     */
    @Override
    public double doegnDosis() {
        long dage = ChronoUnit.DAYS.between(super.getStartDen(),super.getSlutDen());
        return samletDosis() / dage;
    }

    /**
     * Returnerer ordinationstypen som en String
     *
     * @return
     */
    @Override
    public String getType() {
        return "Skæv";
    }

    public ArrayList<Dosis> getDosis() {
        return new ArrayList<>(dosis);
    }


}
