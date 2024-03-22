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
        if (givesDen.isAfter(getStartDen().minusDays(1))  && givesDen.isBefore(getSlutDen().plusDays(1))){
            gemteDatoer.add(givesDen);
            dosisGivet = true;
        }
        return dosisGivet;
    }

    public double doegnDosis() {
        long antalDageIAlt;
        if (gemteDatoer.size() == 0){
            return 0;
        }else {
            LocalDate startdato = gemteDatoer.get(0);
            LocalDate slutDato = gemteDatoer.get(0);
            int antalDatoer = 0;
            for (LocalDate dato : gemteDatoer){
                if (dato.isAfter(slutDato)){
                    slutDato = dato;
                } else if (dato.isBefore(startdato)){
                    startdato = dato;
                }
            }
            antalDageIAlt = ChronoUnit.DAYS.between(startdato,slutDato.plusDays(1));
        }
        return samletDosis() / antalDageIAlt;
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

    public ArrayList<LocalDate> getGemteDatoer() {
        return gemteDatoer;
    }

    public int getAntalGange() {
        return antalGange;
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
}
