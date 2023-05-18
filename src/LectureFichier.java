import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class LectureFichier {

    private static String fileName;
    // Fonction pour lire les événements à partir d'un fichier et les jouer dans le jeu
    public static void readEventsFromFile(Joueur joueur0, Joueur joueur1) {
        int player;
        String cardName;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom du fichier : ");
        fileName = scanner.nextLine();

        try {
            File file = new File(fileName);

            Scanner fileScanner = new Scanner(file);

            int lineNumber = 1;
            while (fileScanner.hasNextLine()) {
                int tranceValue = 0;
                String line = fileScanner.nextLine();

                try {
                    // Extraction des données de la ligne
                    String[] parts = line.split(" ");
                    player = Integer.parseInt(parts[0]);
                    cardName = parts[1];

                    // Validation des données
                    verifJoueur(player);

                    if (!cardName.equals("Trance")) {
                        // La carte ne nécessite pas de nombre supplémentaire
                        if (parts.length != 2) {
                            throw new Exception("Format de ligne incorrect");
                        }
                        carteInvalide(player, cardName);
                    } else {
                        // La carte Trance nécessite un nombre supplémentaire
                        verifTranceCarte(parts);
                        tranceValue = Integer.parseInt(parts[2]);
                        verifTranceValeur(tranceValue);
                    }
                    carteIllumination(cardName);
                    // Traitement de l'événement
                    Cartes carte;
                    carte = vefifTypeCarte(cardName, tranceValue);
                    verifCarteJoueur(joueur0, joueur1, player, cardName, carte);
                } catch (Exception e) {
                    System.err.println("Erreur à la ligne " + lineNumber + " : " + e.getMessage());
                    System.exit(1);
                }

                lineNumber++;
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier n'existe pas");
            System.exit(1);
        }
    }
    /**
     *Vérifie si la carte jouée est la carte Illumination et affiche une ligne vide si c'est le cas.
     *@param cardName le nom de la carte jouée
     */
    private static void carteIllumination(String cardName) {
        if(cardName.equals("Illumination")){
            System.out.println("");
        }
    }

    /**
     *Vérifie si le joueur peut jouer la carte et l'ajoute à son jeu.
     *@param joueur0 le joueur 0
     *@param joueur1 le joueur 1
     *@param player le numéro du joueur actuel
     *@param cardName le nom de la carte jouée
     *@param carte la carte à ajouter au jeu du joueur
     *@throws Exception si le joueur n'a plus de carte ou n'a pas assez d'attaque pour jouer cette carte
     */
    private static void verifCarteJoueur(Joueur joueur0, Joueur joueur1, int player, String cardName, Cartes carte) throws Exception {
        if(player == 0){

            verifCarte(joueur0, joueur1, player, cardName, carte);
            joueur0.ajoutCarte(carte);

        }else {
            verifCarte(joueur1, joueur0, player, cardName, carte);
            joueur1.ajoutCarte(carte);

        }
    }

    /**
     *Vérifie le type de carte à partir de son nom et de sa valeur de transe.
     *Crée une instance de Cartes en fonction du type de carte et de sa valeur de transe.
     *@param cardName le nom de la carte
     *@param tranceValue la valeur de transe de la carte
     *@return une instance de Cartes créée en fonction du type de carte et de sa valeur de transe
     */
    private static Cartes vefifTypeCarte(String cardName, int tranceValue) {
        Cartes carte;
        if(Attaque.isCarteAttaque(cardName)){

            carte = new Attaque(cardName, tranceValue);

        }
        else if(Experience.isCarteExperience(cardName)){
            carte = new Experience(cardName);

        }
        else if (Dommage.isCarteDommage(cardName)){
            carte = new Dommage(cardName);

        } else {
            carte = new Riposte(cardName);
        }
        return carte;
    }

    /**
     *Vérifie si le nom de la carte jouée est valide ou non.
     *@param player numéro du joueur jouant la carte
     *@param cardName nom de la carte jouée
     *@throws Exception si le nom de la carte jouée est invalide
     */
    private static void carteInvalide(int player, String cardName) throws Exception {
        if(!Cartes.isNomValide(cardName)){
            throw new Exception("Joueur " + player + ", a jouer une carte inexistante : " + cardName);
        }
    }

    /**
     Vérifie si la valeur de la carte Trance est valide.
     @param tranceValue la valeur de la carte Trance à vérifier
     @throws Exception si la valeur de la carte Trance n'est pas comprise entre 0 et 4 inclus
     */
    private static void verifTranceValeur(int tranceValue) throws Exception {
        if (tranceValue < 0 || tranceValue > 4) {
            throw new Exception("Nombre suivant la carte Trance incorrect");
        }
    }

    /**
     Vérifie si une carte Trance a le bon format (3 éléments).
     @param parts tableau de chaînes de caractères contenant les éléments de la ligne
     @throws Exception si le format est incorrect (pas 3 éléments)
     */
    private static void verifTranceCarte(String[] parts) throws Exception {
        if (parts.length != 3) {
            throw new Exception("Format de ligne incorrect");
        }
    }

    /**
     Vérifie si le numéro de joueur est valide.
     @param player le numéro du joueur à vérifier.
     @throws Exception si le numéro de joueur est invalide.
     */
    private static void verifJoueur(int player) throws Exception {
        if (player < 0 || player > 1) {
            throw new Exception("Numéro de joueur incorrect");
        }
    }
    /**
     Vérifie si un joueur peut jouer une carte donnée en fonction de ses ressources et de l'effet de la carte.
     @param joueur0 le joueur courant
     @param joueur1 l'autre joueur
     @param player le numéro du joueur courant (0 ou 1)
     @param cardName le nom de la carte à jouer
     @param carte la carte à jouer
     @throws Exception si le joueur n'a plus de carte ou d'attaque pour jouer la carte donnée
     */
    private static void verifCarte(Joueur joueur0, Joueur joueur1, int player, String cardName, Cartes carte) throws Exception {
        boolean verifPasse = carte.premierEffet(joueur0, joueur1);
        if(!verifPasse && joueur0.getM() == 0){
            throw new Exception("Joueur " + player + ", carte " + cardName + ", le joueur n'avait plus de carte.");
        }else if(!verifPasse && joueur0.getA() == 0){
            throw new Exception("Joueur " + player + ", carte " + cardName + ", le joueur n'avait plus assez d'attaque pour jouer cette carte.");
        }
    }
    /**
     *Cette méthode permet d'obtenir l'ordre de jeu à partir du fichier de sauvegarde.
     *Elle lit le fichier et pour chaque ligne, elle récupère le numéro de joueur et le nom de la carte jouée.
     *En fonction du nom de la carte, elle met à jour l'ordre de jeu en ajoutant ou supprimant des joueurs.
     *L'ordre final est stocké dans une file (Deque) et renvoyé à la fin de la méthode.
     *@return une Deque<Integer> contenant l'ordre de jeu des joueurs
     *@throws FileNotFoundException si le fichier de sauvegarde n'existe pas
     */
    public static Deque<Integer> obtenirOrdreJeu() {
        Deque<Integer> ordreJeu = new ArrayDeque<>();

        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                int player = Integer.parseInt(parts[0]);
                String cardName = parts[1];

                ordreJeu.push(player);

                if (cardName.equals("Vitesse")){
                    ordreJeu.push(player);
                } else if (cardName.equals("Esquive")){
                    if (player==0) ordreJeu.removeLastOccurrence(1);
                    if (player==1) ordreJeu.removeLastOccurrence(0);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier n'existe pas");
            System.exit(1);
        }

        return ordreJeu;
    }
}


