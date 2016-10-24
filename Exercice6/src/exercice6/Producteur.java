package exercice6;


/**
 *
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class Producteur extends Thread {
    private Buffer buf;
    
    public Producteur(Buffer buf, String id) {
        super(id);
        this.buf = buf;
    }
    
    public void run() {
        for (int i = 0; i < 10; ++i) {
            buf.mettre(i);
        }
        try {
            sleep((int)(Math.random() * 1000));
        }
        catch (InterruptedException e) {}
    }
}
