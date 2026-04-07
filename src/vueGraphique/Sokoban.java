package vueGraphique;
import modele.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sokoban extends JFrame {
    private Carte carte;
    private VueSokoban vue;
    private JLabel labelStatut;
    private int niveau;
    public Sokoban() {
        niveau = 1;
        initialiserNiveau();
    }
    private void initialiserNiveau() {
        String fichier = "map/map" + niveau + ".txt";
        if (!new java.io.File(fichier).exists()) {
            JOptionPane.showMessageDialog(this,
                "Tous les niveaux terminés ! Félicitations !",
                "Sokoban", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        carte = new Carte(fichier);
        vue   = new VueSokoban(carte);
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuJeu    = new JMenu("Jeu");
        JMenuItem itemRestart = new JMenuItem("Restart (R)");
        itemRestart.addActionListener(e -> restart());
        menuJeu.add(itemRestart);
        menuBar.add(menuJeu);
        setJMenuBar(menuBar);
        add(vue, BorderLayout.CENTER);

        // Statut
        labelStatut = new JLabel("Niveau " + niveau + " | Mouvements : 0");
        labelStatut.setHorizontalAlignment(SwingConstants.CENTER);
        labelStatut.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(labelStatut, BorderLayout.SOUTH);

        // Key Bindings sur le panneau vue
        InputMap im = vue.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = vue.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,    0), "haut");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,  0), "bas");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,  0), "gauche");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "droite");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R,     0), "restart");
        am.put("haut",    new AbstractAction() { public void actionPerformed(ActionEvent e) { deplacer(Direction.HAUT); } });
        am.put("bas",     new AbstractAction() { public void actionPerformed(ActionEvent e) { deplacer(Direction.BAS); } });
        am.put("gauche",  new AbstractAction() { public void actionPerformed(ActionEvent e) { deplacer(Direction.GAUCHE); } });
        am.put("droite",  new AbstractAction() { public void actionPerformed(ActionEvent e) { deplacer(Direction.DROITE); } });
        am.put("restart", new AbstractAction() { public void actionPerformed(ActionEvent e) { restart(); } });
        setTitle("Sokoban - Niveau " + niveau);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        revalidate();
        repaint();
    }
    private void deplacer(Direction dir) {
        boolean bouge = carte.deplacer(dir);
        if (bouge) {
            vue.mettreAJour();
            mettreAJourStatut();
            if (carte.finDePartie()) {
                int rep = JOptionPane.showConfirmDialog(this,
                    "Bravo ! Niveau " + niveau + " terminé en "
                    + carte.getNbMouvements() + " mouvements !\nNiveau suivant ?",
                    "Gagné !", JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    niveau++;
                    initialiserNiveau();
                }
            }
        }
    }
    private void mettreAJourStatut() {
        labelStatut.setText("Niveau " + niveau + " | Mouvements : " + carte.getNbMouvements());
    }
    private void restart() {
        carte.restart();
        vue.mettreAJour();
        mettreAJourStatut();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Sokoban().setVisible(true));
    }
}