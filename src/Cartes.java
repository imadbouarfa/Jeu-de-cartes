public abstract class Cartes {

    // Liste des noms de cartes valides
    private static String[] cartesValides = {"Inspiration", "NouvelleEnergie", "Illumination", "RegardeUneDistraction", "CalmeAvantLaTempete",
            "TousPourUn", "PetitVoleur", "PetitePause", "BotteSecrete", "ApprendreParMesErreurs", "CoupDroit",
            "Fouette", "Fleche", "Oups", "JaiCompris", "Esquive", "Vitesse"};

    private String nom;

    public Cartes(String nom) {
        this.nom = nom;
    }

    // Getter et Setter pour les attributs nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Vérifie si le nom de la carte est valide.
     * @param nomCarte le nom de la carte à vérifier
     * @return true si le nom est valide, false sinon
     */
    public static boolean isNomValide(String nomCarte){
        for (String nom : cartesValides){
            if (nomCarte.equals(nom)){
                return true;
            }
        }
        return false;
    }

    /**
     * Applique le premier effet de la carte.
     * @param Jc le joueur courant
     * @param Ja le joueur adverse
     * @return true si l'effet a été appliqué, false sinon
     */
    public abstract boolean premierEffet(Joueur Jc, Joueur Ja);

    /**
     * Applique le deuxième effet de la carte.
     * @param Jc le joueur courant
     * @param Ja le joueur adverse
     */
    public abstract void deuxiemeEffet(Joueur Jc, Joueur Ja);

}
