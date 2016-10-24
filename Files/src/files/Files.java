package files;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Dermoumi S.(<sdermoumi@gmail.com)
 */
public class Files {
    
    static public class A implements Serializable {
        public int a = 32;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            A a = new A();
            A b = a;
            
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("temp.dat"));
            output.writeObject(a);
            a.a = 21;
            output.writeObject(b);

            output.close();

            
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("temp.dat"));
            A a2 = (A)input.readObject();
            A b2 = (A)input.readObject();
            
            input.close();
            
            System.out.println(a2.a + " " + b2.a);
        }
        catch (Exception e) {
            
        }
    }
    
}
