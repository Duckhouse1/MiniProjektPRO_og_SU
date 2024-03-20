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
        if (givesDen.isAfter(getStartDen().plusDays(1))  && givesDen.isBefore(getSlutDen().plusDays(1))){
            gemteDatoer.add(givesDen);
            dosisGivet = true;
        }
        return dosisGivet;
    }

    public double doegnDosis() {
        return  (antalGange * antalEnheder) / antalDage();
    }

    @Override
    public String getType() {
        return getType();
    }


    public double samletDosis() {
        return antalGange * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return antalGange;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
