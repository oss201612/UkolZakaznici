import java.math.BigDecimal;
import java.time.LocalDate;

public class Zakaznik {
    private String jmeno;
    private LocalDate datum;
    private String mesto;
    private int pocatProdeju;

    public Zakaznik(String jmeno, LocalDate datum, String mesto, int pocatProdeju) {
        this.datum = datum;
        this.pocatProdeju = pocatProdeju;
        this.mesto = mesto;
        this.jmeno = jmeno;
    }

    public String getJmeno() {
        return jmeno;
    }

    public int getpocatProdeju() {
        return pocatProdeju;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getMesto() {
        return mesto;
    }
    public void zvysProdej(int pocet) throws ZaporneCisloException {
        if (pocet < 0) {
            throw new ZaporneCisloException("Pocet nesmi byt zaporny");
        }
        pocatProdeju += pocet;
    }

}