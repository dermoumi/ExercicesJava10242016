package exercice6;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Une classe buffer qui gère l'échange des éléments entre
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class Buffer {
    private boolean available = false;
    private Queue<Integer> file = new LinkedList<Integer>();
    
    /**
     * Récupère un élément de la file, et ne la libère que lorsque celle-ci soit entièrement vidée.
     */
    public synchronized void prendre() {
        while (!available) {
            try {wait();}
            catch (InterruptedException e) {}
        }
        
        System.out.println("Consommateur #" + Thread.currentThread().getName() + " prend : " + file.poll());
        
        if (file.size() == 0) {
            available = false;
            notifyAll();
        }
    }
    
    /**
     * Ajoute un élément à la file, et ne la libère aux client que lorsque celle-ci soit entièrement remplie.
     * @param val 
     */
    public synchronized void mettre(int val) {
        while (available) {
            try {wait();}
            catch (InterruptedException e) {}
        }
        
        file.offer(val);
        System.out.println("Producteur #" + Thread.currentThread().getName() + " met : " + val);
        
        if (file.size() >= 5) {
            available = true;
            notifyAll();
        }
    }
}
