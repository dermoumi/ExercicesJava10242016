package exercice6;

/**
 *
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class ProdConsom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Buffer c = new Buffer();
        
        Producteur p1 = new Producteur(c, "p1");
        Producteur p2 = new Producteur(c, "p2");
        Producteur p3 = new Producteur(c, "p3");
        
        Consommateur c1 = new Consommateur(c, "c1");
        Consommateur c2 = new Consommateur(c, "c2");
        Consommateur c3 = new Consommateur(c, "c3");
        
        p1.start(); p2.start(); p3.start();
        c1.start(); c2.start(); c3.start();
    }
    
}
