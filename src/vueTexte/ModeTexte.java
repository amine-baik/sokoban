package vueTexte;
import modele.Carte;
import modele.Direction;
import java.util.HashMap;
import java.util.Map;
public class ModeTexte {
    private Carte carte;
    private Map<Character, Direction> touches;
    public ModeTexte(Carte carte) {
        this.carte = carte;
        touches = new HashMap<>();
        touches.put('z', Direction.HAUT);
        touches.put('s', Direction.BAS);
        touches.put('q', Direction.GAUCHE);
        touches.put('d', Direction.DROITE);
    }
    private char lireCommande() {
        char c;
        do {
            System.out.print("Commande (z/s/q/d | r=restart) : ");
            c = Outil.lireCaractere();
        } while (!touches.containsKey(c) && c != 'r');
        return c;
    }
    public void jouer() {
        System.out.println("=== SOKOBAN ===");
        System.out.println("z=haut  s=bas  q=gauche  d=droite  r=restart");
        while (!carte.finDePartie()) {
            System.out.println(carte);
            System.out.println("Mouvements : " + carte.getNbMouvements());
            char cmd = lireCommande();
            if (cmd == 'r') {
                carte.restart();
            } else {
                carte.deplacer(touches.get(cmd));
            }
        }
        System.out.println(carte);
        System.out.println("Bravo ! Niveau terminé en " + carte.getNbMouvements() + " mouvements !");
    }
}ç