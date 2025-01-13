import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class Main {
    private static final String DELIMITER = ":";
    private static final String FILE_NAME2 = "jmena2.txt";
    private static final String FILE_NAME = "jmena.txt";

    public static void main(String[] args) {
        EvidenceZakazniku evidenceZakazniku = new EvidenceZakazniku();
        try {
            evidenceZakazniku.nactiOsobyZeSouboru(FILE_NAME, DELIMITER);
        } catch (IOException e) {
            System.err.println("Chyba: " + e.getMessage());
        }
        evidenceZakazniku.odebratPoslednihoZakaznika();
        evidenceZakazniku.vratitFiltrovane();
        System.out.println("Zakaznici s prodejem vetsim nez 10: ");
        for (Zakaznik zakaznik : evidenceZakazniku.getEvidence()) {
            System.out.println(zakaznik.getJmeno() + " " + zakaznik.getDatum() + " " + zakaznik.getMesto() + " " + zakaznik.getpocatProdeju());
        }
        int a = 0;
        int b = 0;
        for (Zakaznik zakaznik : evidenceZakazniku.getEvidence()) {
            if (Objects.equals(zakaznik.getMesto(), "Uherské Hradiště")){
                a = a + zakaznik.getpocatProdeju();
                b++;
            }
        }
        System.out.println("Prumer prodeju v UH je: " + a / b);
        try {
            evidenceZakazniku.zapisOsobyDoSouboru(FILE_NAME2, DELIMITER);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}