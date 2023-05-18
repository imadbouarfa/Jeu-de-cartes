public class Dommage extends NonAttaque{

    // Liste des noms de cartes de dommage valides
    private static String[] cartesValides = {"CoupDroit", "Fouette", "Fleche"};

    /**
     * Vérifie si le nom de la carte est un nom de carte de dommage valide.
     * @param cardName le nom de la carte à vérifier
     * @return true si le nom est valide, false sinon
     */
    public static boolean isCarteDommage (String cardName) {
        for(String nom : cartesValides){
            if(cardName.equals(nom)){
                return true;
            }
        }
        return false;
    }

    /**
     * Constructeur de la classe Dommage.
     * @param nom le nom de la carte de dommage
     */
    public Dommage(String nom) {
        super(nom);
    }

    /**
     * Applique le deuxième effet de la carte de dommage.
     * @param Jc le joueur courant
     * @param Ja le joueur adverse
     */
    @Override
    public void deuxiemeEffet(Joueur Jc, Joueur Ja) {
        switch (getNom()){
            case "CoupDroit":
                Ja.setD(Ja.getD() + 1);
                Jc.enleverCarte();
                break;
            case "Fouette":
                Ja.setD(Ja.getD() + 2);
                Jc.enleverCarte();
                break;
            case "Fleche":
                Ja.setD(Ja.getD() + 3);
                Jc.enleverCarte();
                break;
        }
    }
}
