package exercice6;


/**
 * Consomme des éléments du buffer donné
 * 
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class Consommateur extends Thread {
    private Buffer buf;
    
    /**
     * Constructeur
     * 
     * @param buf Buffer duquelle le consommateur peut consommer.
     * @param id Nom identificateur du consommateur.
     */
    public Consommateur(Buffer buf, String id) {
        super(id);
        this.buf = buf;
    }
    
    public void run() {
        for (int i = 0; i < 10; ++i) {
            buf.prendre();
        }
    }
}
