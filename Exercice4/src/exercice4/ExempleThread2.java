package exercice4;

/**
 * Réponses aux questions:
 * <ol>
 *  <li>5 Threads s'executent (sans compter le thread principal)</li>
 *  <li>La première ligne affiché serait "Programme démarre..."
 *    Dernière ligne affiché serait "Thread numéro X termine." dans la plupart
 *      des cas où X est un nombre entre 0 et 4 mais très très rarement
 *      "Programme principal termine" sur un anciènne machine.</li>
 *  <li>Le résultat est différent parceque sans sleep, il y'aura moins de temps
 *      entre les deux println pour que d'autres thread puisse s'executer</li>
 * </ol>
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class ExempleThread2 extends Thread {

    /**
     * Nombre de threads crées
     */
    private static int numThread = 0;
    
    /**
     * Numéro du thread
     */
    private int numero;
    
    /**
     * Variable partagée
     */
    private static int partage = 0;
    
    /**
     * Constructeur
     */
    ExempleThread2() {
        numero = numThread;
        numThread = numThread + 1;
        System.out.println("Thread numero " + numero + " cree.");
    }
    
    public void run() {        
        System.out.println("Thread numero " + numero + " demarre.");
        try {
            Thread.sleep(1);
        }
        catch (Exception e) {
            return;
        }
        System.out.println("Thread numero " + numero + " termine.");
        
        // Question 4
        int acc = partage;
        try { Thread.sleep(1); } catch (Exception e) {};
        acc = acc + 1;
        partage = acc;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Programme démarre..");
        // Création de 5 threads
        for (int i = 0; i < 5; ++i) {
            Thread unThread = new ExempleThread2();
            unThread.start();
        }
        System.out.println("Programme principal terminé.");
        
        // Question 4
        System.out.println("partage = " + partage);
    }
    
}
