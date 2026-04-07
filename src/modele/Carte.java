package modele;
import java.util.*;

public class Carte {
    private Element[][] grille;
    private int nbLignes;
    private int nbColonnes;
    private Robot robot;
    private List<int[]> destinations;
    private int nbMouvements;

    // Pour restart
    private List<String> lignesInitiales;
    public Carte(String nomFichier) {
        Lecture lect = new Lecture(nomFichier);
        lignesInitiales = new ArrayList<>(lect.getLignes());
        chargerDepuisLignes(lignesInitiales);
    }
    private void chargerDepuisLignes(List<String> lignes) {
        nbLignes = lignes.size();
        int maxLen = 0;
        for (String l : lignes) maxLen = Math.max(maxLen, l.length());
        nbColonnes = maxLen;
        grille= new Element[nbLignes][nbColonnes];
        destinations = new ArrayList<>();
        robot = null;
        nbMouvements = 0;
        for (int li = 0; li < nbLignes; li++) {
            String ligne = lignes.get(li);
            for (int co = 0; co < nbColonnes; co++) {
                char c = (co < ligne.length()) ? ligne.charAt(co) : '/';
                switch (c) {
                    case '#':
                        grille[li][co] = new Mur();
                        break;
                    case '/':
                        grille[li][co] = new Vide();
                        break;
                    case ' ':
                        grille[li][co] = new Sol();
                        break;
                    case '$':
                        grille[li][co] = new Sol();
                        grille[li][co].setCaisse(true);
                        break;
                    case '.':
                        grille[li][co] = new Destination();
                        destinations.add(new int[]{co, li});
                        break;
                    case '*':
                        grille[li][co] = new Destination();
                        grille[li][co].setCaisse(true);
                        destinations.add(new int[]{co, li});
                        break;
                    case '@':
                        grille[li][co] = new Sol();
                        grille[li][co].setJoueur(true);
                        robot = new Robot(co, li);
                        break;
                    case '+':
                        grille[li][co] = new Destination();
                        grille[li][co].setJoueur(true);
                        destinations.add(new int[]{co, li});
                        robot = new Robot(co, li);
                        break;
                    default:
                        grille[li][co] = new Vide();
                }
            }
        }
    }

    //Accesseurs 
    public int getNbLignes()                        { return nbLignes; }
    public int getNbColonnes()                      { return nbColonnes; }
    public int getNbMouvements()                    { return nbMouvements; }
    public Robot getRobot()                         { return robot; }
    public Element getElement(int col, int ligne)   { return grille[ligne][col]; }

    //Déplacement 
    public boolean deplacer(Direction dir) {
        int rc = robot.getCol();
        int rl = robot.getLigne();
        int nc = rc + dir.getDx();
        int nl = rl + dir.getDy();
        robot.setDirection(dir);
        if (!estDansGrille(nc, nl)) return false;
        Element cible = grille[nl][nc];
        if (!cible.estPraticable()) return false;
        if (cible.aCaisse()) {
            int cc = nc + dir.getDx();
            int cl = nl + dir.getDy();
            if (!estDansGrille(cc, cl)) return false;
            Element dest = grille[cl][cc];
            if (!dest.estPraticable() || dest.aCaisse()) return false;
            cible.setCaisse(false);
            dest.setCaisse(true);
        }
        grille[rl][rc].setJoueur(false);
        cible.setJoueur(true);
        robot.setPosition(nc, nl);
        nbMouvements++;
        return true;
    }
    public void restart() {
        chargerDepuisLignes(lignesInitiales);
    }

    //Fin de partie
    public boolean finDePartie() {
        for (int[] dest : destinations) {
            if (!grille[dest[1]][dest[0]].aCaisse()) return false;
        }
        return true;
    }

    //toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int li = 0; li < nbLignes; li++) {
            for (int co = 0; co < nbColonnes; co++) {
                sb.append(grille[li][co].toChar());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    private boolean estDansGrille(int col, int ligne) {
        return col >= 0 && col < nbColonnes && ligne >= 0 && ligne < nbLignes;
    }
}