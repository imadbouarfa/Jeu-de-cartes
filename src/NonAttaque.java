    /**
    *Cette classe abstraite NonAttaque hérite de la classe Cartes et définit les comportements communs à toutes les cartes non-attaques.
    */

public abstract class NonAttaque extends Cartes {

    /**
     * Le constructeur de la classe appelle le constructeur de la classe parent Cartes pour initialiser le nom de la carte.
      */
    public NonAttaque(String nom) {
        super(nom);
    }

    /**
     *Cette méthode implémente le premier effet de la carte non-attaque.
     *Elle vérifie si la condition pour jouer la carte est remplie, puis dépose la carte sur la pile de cartes du joueur.
     *Elle retourne vrai si le joueur a pu jouer la carte, faux sinon.
      */
    @Override
    public boolean premierEffet(Joueur Jc, Joueur Ja) {
        if (verification(Jc)) {
            depot(Jc);
            return true;
        }
        return false;
    }

    /**
     *Cette méthode abstraite définit le deuxième effet de la carte non-attaque et doit être implémentée dans les classes filles.
     */
    @Override
    public abstract void deuxiemeEffet(Joueur Jc, Joueur Ja);

    /**
     *Cette méthode privée vérifie si le joueur a assez de carte pour jouer la carte non-attaque.
     */
    private boolean verification(Joueur Jc) {
        return Jc.getM() > 0;
    }

    /**
     *Cette méthode privée enlève 1 carte de la main du joueur après qu'il ait joué la carte non-attaque.
     */
    private void depot(Joueur Jc) {
        Jc.setM(Jc.getM() - 1);
    }
}
