public class Experience extends NonAttaque{

    /**
     *Liste des noms de cartes d'expérience valides
     */
    private static String[] cartesValides = {"Oups", "JaiCompris"};

    /**
     * Vérifie si le nom de la carte est un nom de carte d'expérience valide.
     * @param cardName le nom de la carte à vérifier
     * @return true si le nom est valide, false sinon
     */
    public static boolean isCarteExperience(String cardName) {
        for (String nom : cartesValides){
            if (cardName.equals(nom)){
                return true;
            }
        }
        return false;
    }

    /**
     * Constructeur de la classe Experience.
     * @param nom le nom de la carte d'expérience
     */
    public Experience(String nom) {
        super(nom);
    }

    /**
     * Applique le deuxième effet de la carte d'expérience.
     * @param Jc le joueur courant
     * @param Ja le joueur adverse
     */
    @Override
    public void deuxiemeEffet(Joueur Jc, Joueur Ja) {
        switch (getNom()){
            case "Oups":
                Jc.setE(Jc.getE() + 1);
                Jc.enleverCarte();
                break;
            case "JaiCompris":
                Jc.setE(Jc.getE() + 2);
                Jc.enleverCarte();
                break;
        }
    }
}
