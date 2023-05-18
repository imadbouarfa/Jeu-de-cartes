/**
 * Cette classe Main est la classe principale qui contient la méthode main pour exécuter le programme.
 * @author Imad Bouarfa Shaker
 * Courriel: bouarfa.imad@courrier.uqam.ca
 * Cours: INF2120-10
 * @version 2023-03-30
 */

import java.util.Deque;

public class Main {

    public static void main(String[] args) {

        // On crée deux objets Joueur.
        Joueur joueur0 = new Joueur();
        Joueur joueur1 = new Joueur();

        // On lit les événements du fichier à l'aide de la classe LectureFichier et on les ajoute aux joueurs.
        LectureFichier.readEventsFromFile(joueur0, joueur1);

        // On récupère l'ordre de jeu à partir de la classe LectureFichier.
        Deque<Integer> ordreJeu = LectureFichier.obtenirOrdreJeu();

        // On initialise l'index du tour à 0.
        int index = 0;

        // Tant qu'il reste des joueurs dans l'ordre de jeu, on les fait jouer.
        while (!ordreJeu.isEmpty()) {

            // On récupère le numéro du joueur à jouer.
            int numJoueur = ordreJeu.pop();

            // Si c'est le joueur 0, on fait jouer la carte du sommet de sa pile de cartes.
            if (numJoueur == 0) {
                joueur0.jouerCarteSommet(joueur0, joueur1, index);
            }
            // Sinon, c'est le joueur 1 qui joue.
            else {
                joueur1.jouerCarteSommet(joueur1, joueur0, index);
            }
            // On incrémente l'index de tour après chaque tour.
            index++;
        }

        // On affiche les dommages et l'expérience de chaque joueur.
        System.out.println("Joueur 0 dommage " + joueur0.getD() + " experience " + joueur0.getE());
        System.out.println("Joueur 1 dommage " + joueur1.getD() + " experience " + joueur1.getE());

    }
}
