package modele;

public class Sol extends Element {
    public boolean estDestination() { return false; }
    public boolean estPraticable() { return true; }
    public char toChar() {
        if (aJoueur) return '@';
        if (aCaisse) return '$';
        return ' ';
    }
}