
package matriceselems;

/**
 * Calcule le produit de deux matrices élément par élément
 * 
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class CalculElem implements Runnable {

    /**
     * Constantes pour la largeur et l'hauteur des matrices à calculer
     */
    public static final int MAX_LIG = 50,       // Lignes
                            MAX_DIM_COM = 50,   // Dimensions communes
                            MAX_COL = 50;       // Colonnes
    
    /**
     * Deux matrices d'entrée et matrice produit
     */
    public static double[][] mat1, mat2, matResultat;
    
    /**
     * Ligne courante que doit calculer la méthode run()
     */
    private int ligne, colonne;
    
    /**
     * Methode d'initialisation des attributs de classe
     * 
     * fonction nommée au lieu de bloc static afin de contrôler
     * le moment d'initialisation
     */
    public static void init() {
        // On créé les tables
        mat1 = new double[MAX_LIG][MAX_DIM_COM];
        mat2 = new double[MAX_DIM_COM][MAX_LIG];
        matResultat = new double[MAX_LIG][MAX_COL];
       
        // On remplie les deux matrices d'entrée avec des valeurs aléatoires
        for (int i = 0; i < MAX_LIG; ++i) {
            for (int j = 0; j < MAX_DIM_COM; ++j) {
                mat1[i][j] = Math.random() * 500;
            }
        }
       
        for (int i = 0; i < MAX_DIM_COM; ++i) {
            for (int j = 0; j < MAX_COL; ++j) {
                mat2[i][j] = Math.random() * 500;
            }
        }
    }
   
    /**
     * Constructeur
     * @param ligne ligne que doit calculer l'objet
     * @param colonne colonne que doit calculer l'objet
     */
    public CalculElem(int ligne, int colonne) {
       this.ligne = ligne;
       this.colonne = colonne;
    }
   
    /**
     * Méthode run à démarrer dans un autre thread (par exemple)
     */
    public void run() {
        matResultat[ligne][colonne] = 0.0;
        for (int k = 0; k < MAX_DIM_COM; ++k) {
            matResultat[ligne][colonne] += mat1[ligne][k] * mat2[k][colonne];
        }
    }
   
    /**
     * Méthode pour calculer le produit des matrices directement
     */
    public static void calculProd() {
        for (int i = 0; i < MAX_LIG; ++i) {
            for (int j = 0; j < MAX_COL; ++j) {
                matResultat[i][j] = 0.0;
                for (int k = 0; k < MAX_DIM_COM; ++k) {
                    matResultat[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
    }
    
    /**
     * Affiche la matrice résultat
     */
    public static void affiche() {
        for (int i = 0; i < MAX_LIG; ++i) {
            for (int j = 0; j < MAX_COL; ++j) {
                System.out.print(matResultat[i][j] + '\t');
            }
            System.out.print('\n');
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // On initialise les matrices à part pour ne pas les compter
        init();
        
        // On commence le compteur pour le calcul multithread
        long t1 = System.currentTimeMillis();
        
        // Un groupe de thread pour compter les thread actifs
        ThreadGroup thGrp = new ThreadGroup("groupe");
        
        // On calcule la matrice
        for (int i = 0; i < MAX_LIG; ++i) {
            for (int j = 0; j < MAX_COL; ++j) {
                Thread th = new Thread(thGrp, new CalculElem(i, j));
                th.start();
            }
        }
        
        // On attend jusqu'à ce que tous les threads soient terminés
        while (thGrp.activeCount() > 0);
        
        // On note le temps d'éxécution
        long t2 = System.currentTimeMillis();
        System.out.println("Temps d'éxecution avec les threads: " + (t2-t1));
        
        // On commonce le compteur pour le calcul monothread
        t1 = System.currentTimeMillis();
        
        // On calcule le produit
        calculProd();
        
        // On note le temps d'éxécution
        t2 = System.currentTimeMillis();
        System.out.println("Temps d'éxécution sans threads: " + (t2-t1));
    }
    
}
