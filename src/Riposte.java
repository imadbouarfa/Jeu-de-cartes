/**
 * Cette classe Riposte hérite de la classe NonAttaque et définit les comportements de la carte Riposte.
 */
public class Riposte extends NonAttaque{

    /**
     * Le constructeur de la classe appelle le constructeur de la classe parent NonAttaque pour initialiser le nom de la carte.
     */
    public Riposte(String nom) {
        super(nom);
    }

    /**
     * Cette méthode implémente le deuxième effet de la carte Riposte en fonction de son nom.
     * Si le nom est "Esquive", elle enlève la carte sommet du joueur attaquant (Ja) et celle du joueur en cours (Jc).
     * Si le nom est "Vitesse", elle enlève la carte sommet du joueur en cours (Jc) et double son effet.
     */
    @Override
    public void deuxiemeEffet(Joueur Jc, Joueur Ja) {
        switch (getNom()){
            case "Esquive":
                Ja.enleverCarte();
                Jc.enleverCarte();
                break;
            case "Vitesse":
                Jc.enleverCarte();
                Jc.doublerCarteSommet();
                break;
        }
    }
}
