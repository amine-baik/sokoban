package modele;
import java.io.*;
import java.util.*;

public class Lecture {
    private List<String> lignes;
    private int nbLignes;
    private int tailleLigne;
    public Lecture(String nomFichier) {
        lignes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String l;
            while ((l = br.readLine()) != null) {
                lignes.add(l);
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier : " + nomFichier);
        }
        nbLignes    = lignes.size();
        tailleLigne = nbLignes > 0 ? lignes.get(0).length() : 0;
    }
    public List<String> getLignes()  { return lignes; }
    public int getNbLignes()         { return nbLignes; }
    public int getTailleLigne()      { return tailleLigne; }
}