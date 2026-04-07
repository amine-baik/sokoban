package vueTexte;
import modele.Carte;

public class SokobanTexte {
    public static void main(String[] args) {
        int niveau = 1;
        while (true) {
            String fichier = "map/map" + niveau + ".txt";
            java.io.File f = new java.io.File(fichier);
            if (!f.exists()) {
                System.out.println("Tous les niveaux terminés ! Félicitations !");
                break;
            }
            System.out.println("\n=== Niveau " + niveau + " ===");
            Carte carte = new Carte(fichier);
            ModeTexte vue = new ModeTexte(carte);
            vue.jouer();
            niveau++;
        }
    }
}