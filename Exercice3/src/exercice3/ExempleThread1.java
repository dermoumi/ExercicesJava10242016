package exercice3;

/**
 * <p>Compte jusqu'à un nombre donné</p>
 * 
 * Réponses aux questions :
 * <ol>
 * <li>Supposé afficher :
 *    <pre>
 *      <code>
 *        En train de compter (n = 0)
 *        Le compteur est à 1
 *        Le compteur est à 2
 *        Le compteur est à 3
 *        ...
 *        En train de compter (n = 3) (?)
 *      </code>
 *    </pre>(première et deuxième lignes sont permutable)
 * </li>
 * <li>En plus de changer la durée entre chaque message (et donc d'execution),
 * changer les delais change les taux auquelles les deux thread s'alternent</li>
 * </ol>
 * 
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class ExempleThread1 implements Runnable {

    /**
     * Le nombre maximal jusqu'auquel le compteur va compter
     */
    private int nombre_max;
    
    /**
     * Constructeur
     * 
     * @param nombre_max le nombre maximal jusqu'auquel le compteur va compter
     */
    public ExempleThread1 (int nombre_max) {
        this.nombre_max = nombre_max;
    }
    
    /**
     * La méthode qui va compter
     */
    public void run() {
        for (int nombre = 1; nombre < nombre_max; ++nombre) {
            System.out.println("le compteur est à " + nombre + ".");
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                return;
            }
        }
    }
    
    /**
     * Fonction principale
     * 
     * Compte en intervales jusqu'à ce que le compteur lancé sur un thread
     * finisse de compter (atteigne sa valeur maximale)
     * 
     * @param args arguments passés à l'application
     */
    public static void main(String[] args) {
        Thread leCompteur = new Thread(new ExempleThread1(10));
        
        leCompteur.start();
        int n = 0;
        while (leCompteur.isAlive()) {
            System.out.println("en train de compter (n=" + n + ")");
            n = n + 1;
            
            
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                // Nothing to do
            }
        }
    }

}
