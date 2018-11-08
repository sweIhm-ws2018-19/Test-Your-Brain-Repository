package UML;

public class Geschäftskunde extends Kunde {
    private String firmenname;
    private Adresse domizilAdresse;

    public Geschäftskunde(Konto[] konto) {
        super(konto);
    }
}
