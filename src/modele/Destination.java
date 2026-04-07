package modele;

public class Destination extends Element {
    public boolean estDestination() { return true; }
    public boolean estPraticable() { return true; }
    public char toChar() {
        if (aJoueur) return '+';
        if (aCaisse) return '*';
        return '.';
    }
}