import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvidenceZakazniku {
    private List<Zakaznik> evidence = new ArrayList<>();

    public void pridani(Zakaznik e) {
        evidence.add(e);
    }

    public void odstraneni(Zakaznik e) {
        evidence.remove(e);
    }

    public void odebratPoslednihoZakaznika() {
        if (!evidence.isEmpty()) {
            evidence.remove(evidence.size() - 1);
        }
    }

    public List<Zakaznik> getEvidence() {
        return new ArrayList<>(evidence);
    }


    public void zapisOsobyDoSouboru(String nazevSouboru, String oddelovac) throws IOException{
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(nazevSouboru)))) {
            for(Zakaznik zakaznik : evidence) {
                writer.println(zakaznik.getJmeno() + oddelovac + zakaznik.getDatum() + oddelovac + zakaznik.getMesto() + oddelovac + zakaznik.getpocatProdeju());
            }
        } catch (IOException exception) {
            throw new IOException("Chyba při zapisu do souboru: " + nazevSouboru + exception.getLocalizedMessage());
        }
    }
    public List<Zakaznik> vratitFiltrovane(){
        List<Zakaznik> zakaznici = new ArrayList<>();
        for(Zakaznik zakaznik : evidence){

            if(zakaznik.getpocatProdeju() > 10){
                zakaznici.add(zakaznik);
            }
        }
        return zakaznici;
    }
    public void nactiOsobyZeSouboru(String nazevSouboru, String oddelovac) throws IOException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(nazevSouboru)))) {
            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                pridani(parseOsoba(radek, oddelovac));
            }
        } catch (FileNotFoundException exception) {
            throw new IOException("Soubor: " + nazevSouboru + " nebyl nalezen: " + exception.getLocalizedMessage());
        } catch (IOException e) {
            throw new IOException("Chyba při načítání souboru: " + e.getMessage());
        }
    }

    private Zakaznik parseOsoba(String radek, String oddelovac) throws IOException {
        String[] polozky = radek.split(oddelovac);
        if (polozky.length != 4) {
            throw new IOException("Chybný počet údajů v řádku: " + radek);
        }
        try {
            String jmeno = polozky[0].trim();
            LocalDate datum = LocalDate.parse(polozky[1].trim());
            String mesto = polozky[2].trim();
            int pocatProdeju = Integer.parseInt(polozky[3].trim());
            return new Zakaznik(jmeno, datum, mesto, pocatProdeju);
        } catch (NumberFormatException e) {
            throw new IOException("Chybný formát čísla v řádku: " + radek);
        } catch (DateTimeParseException e) {
            throw new IOException("Chybný formát datumu v řádku: " + radek);
        }
    }

}