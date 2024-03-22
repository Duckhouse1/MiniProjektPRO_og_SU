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

    public void opretDosis(LocalTime klokkeSlet, double antalEnheder) {
        if (antalEnheder < 0){
            throw new RuntimeException();
        }
        Dosis dosis = new Dosis(klokkeSlet, antalEnheder);
        this.dosis.add(dosis);
    }

    /**
     * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
     *
     * @return
     */
    @Override
    public double samletDosis() {return super.antalDage()*doegnDosis();}

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
        return "Skæv";
    }

    public ArrayList<Dosis> getDosis() {
        return new ArrayList<>(dosis);
    }


}
