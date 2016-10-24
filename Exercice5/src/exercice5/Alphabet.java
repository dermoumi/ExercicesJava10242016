package exercice5;

/**
 *
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class Alphabet implements Runnable{
    public void run() {
        for (char a = 'A'; a <= 'Z'; ++a) {
            System.out.print(a);
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {}
        }
        System.out.print("\n");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Alphabet A1 = new Alphabet();
        Thread T1 = new Thread(A1);
        Alphabet A2 = new Alphabet();
        Thread T2 = new Thread(A2);
        T1.start();
        T2.start();
    }
    
}
