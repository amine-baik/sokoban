package vueGraphique;

import modele.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VueSokoban extends JPanel {
    private Carte carte;
    private Map<String, Image> images;
    private int taille;
    public VueSokoban(Carte carte) {
        this.carte = carte;
        chargerImages();
        calculerTaille();
        setPreferredSize(new Dimension(
            carte.getNbColonnes() * taille,
            carte.getNbLignes()   * taille
        ));
    }

    // Calcule automatiquement la taille des cases selon la carte
    private void calculerTaille() {
        Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
        int maxW = (int)(ecran.width  * 0.90);
        int maxH = (int)(ecran.height * 0.85);
        int tailleW = maxW / carte.getNbColonnes();
        int tailleH = maxH / carte.getNbLignes();
        taille = Math.min(tailleW, tailleH);
        if (taille < 16) taille = 16;
        if (taille > 64) taille = 64;
    }
    private void chargerImages() {
        images = new HashMap<>();
        String[] noms = {"mur", "sol", "but", "caisse1", "caisse2",
                         "Haut", "Bas", "Gauche", "Droite"};
        for (String nom : noms) {
            ImageIcon ic = new ImageIcon("img/" + nom + ".gif");
            images.put(nom, ic.getImage());
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int li = 0; li < carte.getNbLignes(); li++) {
            for (int co = 0; co < carte.getNbColonnes(); co++) {
                Element e = carte.getElement(co, li);
                int x = co * taille;
                int y = li * taille;
                if (e instanceof Vide) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, taille, taille);
                    continue;
                }
                if (e instanceof Mur) {
                    drawImage(g, "mur", x, y);
                } else if (e.estDestination()) {
                    drawImage(g, "but", x, y);
                    if (e.aCaisse()) {
                        drawImage(g, "caisse2", x, y);
                    } else if (e.aJoueur()) {
                        drawRobot(g, x, y);
                    }
                } else {
                    drawImage(g, "sol", x, y);
                    if (e.aCaisse()) {
                        drawImage(g, "caisse1", x, y);
                    } else if (e.aJoueur()) {
                        drawRobot(g, x, y);
                    }
                }
            }
        }
    }
    private void drawRobot(Graphics g, int x, int y) {
        String nom;
        switch (carte.getRobot().getDirection()) {
            case HAUT:   nom = "Haut";   break;
            case BAS:    nom = "Bas";    break;
            case GAUCHE: nom = "Gauche"; break;
            default:     nom = "Droite"; break;
        }
        drawImage(g, nom, x, y);
    }
    private void drawImage(Graphics g, String nom, int x, int y) {
        Image img = images.get(nom);
        if (img != null) g.drawImage(img, x, y, taille, taille, this);
    }
    public void mettreAJour() { repaint(); }
}