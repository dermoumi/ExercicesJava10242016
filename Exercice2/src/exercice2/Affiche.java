package exercice2;

/**
 * Classe compteur
 * 
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class Affiche extends Thread {

    /**
     * Valeur initiale du compteur (0 pour nombres pairs, 1 pour impairs)
     */
    private int offset;
    
    /**
     * Construit l'objet compteur avec la valeur initiale
     * 
     * @param offset valeur initiale du compteur (0 pour pairs, 1 pour impairs)
     */
    public Affiche(int offset) {
        this.offset = offset;
    }
    
    /**
     * Fonction appelée par chaque thread pour compter les nombres
     */
    public void run() {
        for (int i = offset; i <= 100; i += 2) {
            System.out.println(i);
            
            // Un petit delai entre chaque element
            try { sleep(10); } catch(Exception e) {};
        }
    }
    
    /**
     * Programme principale qui lance deux threads pour calcule
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Affiche pairs = new Affiche(0);
        Affiche impairs = new Affiche(1);
        
        System.out.println("Début");
        
        // On démarre les threads
        pairs.start();
        impairs.start();
        
        // On attend que les threads terminent
        try {
            pairs.join();
            impairs.join();
        }
        catch (Exception e) {
            // Nothing to do
        }
        
        System.out.println("Fin");
    }
    
}
