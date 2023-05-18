/**
 * La classe Attaque représente une carte d'attaque dans le jeu.
 * Elle hérite de la classe Cartes et implémente ses méthodes abstraites.
 * Elle contient des effets spécifiques pour chaque carte d'attaque valide.
 */
public class Attaque extends Cartes {

    // Tableau contenant les noms des cartes d'attaque valides
    private static String[] cartesValides = {"Inspiration", "NouvelleEnergie", "Illumination", "RegardeUneDistraction", "CalmeAvantLaTempete",
            "TousPourUn", "PetitVoleur", "PetitePause", "BotteSecrete", "ApprendreParMesErreurs", "Trance"};

    // Le nombre N de la carte d'attaque Trance, qui peut varier
    private int N;

    /**
     * Constructeur de la classe Attaque.
     * @param nom Le nom de la carte d'attaque.
     * @param N Le nombre N de la carte d'attaque Trance.
     */
    public Attaque(String nom, int N) {
        super(nom);
        this.N = N;
    }

    /**
     * Implémentation de la méthode premierEffet de la classe Cartes.
     * Effectue le premier effet de la carte d'attaque sur les joueurs.
     * @param Jc Le joueur qui joue la carte d'attaque.
     * @param Ja Le joueur ciblé par la carte d'attaque.
     * @return true si l'effet a été appliqué avec succès, false sinon.
     */
    @Override
    public boolean premierEffet(Joueur Jc, Joueur Ja){
        if (verification(Jc)){
            depot(Jc);

            switch (getNom()){
                case "Inspiration":
                    Jc.setM(Jc.getM()+2);
                    break;
                case "NouvelleEnergie":
                    Jc.setA(Jc.getA()+2);
                    break;
                case "Illumination":
                    if(Jc.getM()<7){Jc.setM(7);}
                    break;
                case "RegardeUneDistraction":
                    Jc.setM(Jc.getM()+1);
                    Jc.setA(Jc.getA()+1);
                    break;
                case "CalmeAvantLaTempete":
                    Jc.setM(Jc.getM() + 1);
                    Ja.setA(Ja.getA() + 1);
                    break;
                case "TousPourUn":
                    Jc.setM(0);
                    break;
                case "PetitVoleur":
                    if(Ja.getM()>3){Ja.setA(Ja.getM() - 3);}
                    else{Ja.setA(0);}
                    break;
                case "PetitePause":
                    Jc.setM(Jc.getM() + 1);
                    Ja.setM(Ja.getM() - 1);
                    break;
                case "Trance":
                    Jc.setM(Jc.getM() - N);
                    break;
            }

            return true;
        }
        return false;
    }

    /**
     * Implémentation de la méthode deuxiemeEffet de la classe Cartes.
     * Effectue le deuxieme effet de la carte d'attaque sur les joueurs.
     * @param Jc Le joueur qui joue la carte d'attaque.
     * @param Ja Le joueur ciblé par la carte d'attaque.
     * @return true si l'effet a été appliqué avec succès, false sinon.
     */
    @Override
    public void deuxiemeEffet(Joueur Jc, Joueur Ja) {

        switch (getNom()){
            case "CalmeAvantLaTempete":
                Jc.setE(Jc.getE() + 1);
                Jc.enleverCarte();
                break;
            case "TousPourUn":
                Jc.setE(Jc.getE() + 3);
                Jc.enleverCarte();
                break;
            case "PetitVoleur":
                Jc.setE(Jc.getE() + 1);
                Jc.enleverCarte();
                break;
            case "ApprendreParMesErreurs":
                Jc.setE(Jc.getE() + 3);
                Jc.setD(Jc.getD() + 1);
                Jc.enleverCarte();
                break;
            case "BotteSecrete":
                Jc.setD(Jc.getD() + 1);
                Ja.setD(Ja.getD() + 3);
                Jc.enleverCarte();
                break;
            case "Inspiration":
            case "NouvelleEnergie":
            case "Illumination":
            case "RegardeUneDistraction":
            case "PetitePause":
            case "Trance":
                Jc.enleverCarte();
                break;
        }
    }

    private void depot(Joueur Jc) {
        Jc.setM(Jc.getM()-1);
        Jc.setA(Jc.getA()-1);
    }

    private boolean verification(Joueur Jc) {
        return Jc.getM()>this.N && Jc.getA()>0;
    }


    public static boolean isCarteAttaque(String nomCarte){
        for (String nom : cartesValides){
            if (nomCarte.equals(nom)){
                return true;
            }
        }
        return false;
    }
}

