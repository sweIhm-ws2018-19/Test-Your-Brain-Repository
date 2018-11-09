package UML;

public class Konto {
    private String bezeichnung;
    private Kunde[] zeichnungsberechtigter;

    public Konto(Kunde[] zeichnungsberechtigter) {
        if(zeichnungsberechtigter.length<1){
            throw new IllegalArgumentException();
        }
        this.zeichnungsberechtigter = zeichnungsberechtigter;
    }

    public Geldbetrag saldo(){
        return null;
    }

    public void einzahlen(Geldbetrag eingangsBetrag){

    }
}
