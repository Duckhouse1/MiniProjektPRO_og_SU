package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination    {

    private double antalEnheder;
    private ArrayList<LocalDate> gemteDatoer = new ArrayList<>();
    private int antalGange;

    public PN(double antalEnheder,Laegemiddel laegemiddel, LocalDate startDen, LocalDate slutDen) {
        super(laegemiddel, startDen, slutDen);
        this.antalEnheder = antalEnheder;
        antalGange++;
    }
    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean dosisGivet = false;
        if (givesDen.isAfter(getStartDen())  && givesDen.isBefore(getSlutDen().plusDays(1))){
            gemteDatoer.add(givesDen);
            dosisGivet = true;
        }
        return dosisGivet;
    }

    public double doegnDosis() {
        return  samletDosis() / gemteDatoer.size();
    }

    @Override
    public String getType() {
        String type = "PN";
        return type;
    }


    public double samletDosis() {
        return gemteDatoer.size() * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return gemteDatoer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
