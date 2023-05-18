import java.util.ArrayDeque;

public class Joueur {

    private int m; // nombre de points de mouvement
    private int a; // nombre d'actions restantes
    private int d; // nombre de points de dégâts infligés
    private int e; // nombre de points d'expérience
    private ArrayDeque<Cartes> pileCarte; // la pile de cartes du joueur

    public Joueur() {
        this.m = 5; // le joueur commence avec 5 points de mouvement
        this.a = 1; // le joueur commence avec 1 action
        this.d = 0; // le joueur commence avec 0 points de dégâts infligés
        this.e = 0; // le joueur commence avec 0 points d'expérience
        this.pileCarte = new ArrayDeque<>(); // initialisation de la pile de cartes
    }

    // renvoie le nombre de cartes dans la pile du joueur
    public int nbrElementPile(){
        return pileCarte.size();
    }

    // ajoute une carte à la pile du joueur
    public void ajoutCarte(Cartes carte){
        pileCarte.push(carte);
    }

    // enlève la carte en haut de la pile du joueur et la renvoie
    public Cartes enleverCarte(){
        return pileCarte.pop();
    }

    // ajoute une copie de la carte en haut de la pile du joueur au-dessus de la pile
    public void doublerCarteSommet(){
        pileCarte.push(pileCarte.peek());
    }

    // joue la carte en haut de la pile du joueur et applique son deuxième effet
    public void jouerCarteSommet(Joueur jc, Joueur ja, int index){
        Cartes carte = pileCarte.peek(); // récupère la carte en haut de la pile
        // si la carte en haut de la pile n'est pas une riposte ou si l'index de l'action est différent de 0
        if(!(index == 0 && carte instanceof Riposte)){
            carte.deuxiemeEffet(jc, ja); // applique le deuxième effet de la carte
        }else {
            enleverCarte(); // sinon enlève la carte de la pile
        }
    }

    // getters et setters pour les attributs de la classe
    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

}
