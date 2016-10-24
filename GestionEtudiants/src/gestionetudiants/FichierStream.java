package gestionetudiants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class FichierStream implements IFichier {
    private String filename;
    
    public FichierStream(String filename) {
        this.filename = filename;
    }
    
    public ArrayList<Etudiant> lireEtudiants() throws IOException {
        try {
            File file = new File(filename);
            if (!file.exists()) return new ArrayList<Etudiant>();
            
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Etudiant> liste = (ArrayList<Etudiant>)stream.readObject();
            stream.close();
            
            return liste;
        }
        catch (ClassNotFoundException c) {
            // Never going to reach here, I hope...
            return new ArrayList<Etudiant>();
        }
    }
    
    public void ecrireEtudiants(ArrayList<Etudiant> liste) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename));
        stream.writeObject(liste);
        stream.close();
    }
}
