package modele;

public class Vide extends Element {
    public boolean estDestination() { return false; }
    public boolean estPraticable() { return false; }
    public char toChar() { return '/'; }
}