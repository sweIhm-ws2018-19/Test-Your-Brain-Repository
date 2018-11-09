package UML;

public abstract class Kunde {
    private Konto[] konto;

    public Kunde(Konto[] konto) {
        if(konto.length<1){
            throw new IllegalArgumentException();
        }
        this.konto = konto;
    }
}
