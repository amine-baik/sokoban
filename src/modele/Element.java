package modele;

public abstract class Element {
    protected boolean aJoueur;
    protected boolean aCaisse;
    public Element() {
        this.aJoueur = false;
        this.aCaisse = false;
    }
    public boolean aJoueur() { return aJoueur; }
    public boolean aCaisse() { return aCaisse; }
    public void setJoueur(boolean j) { this.aJoueur = j; }
    public void setCaisse(boolean c) { this.aCaisse = c; }
    public abstract boolean estDestination();
    public abstract boolean estPraticable();
    public abstract char toChar();
}