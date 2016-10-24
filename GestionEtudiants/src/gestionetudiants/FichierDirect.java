package gestionetudiants;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class FichierDirect implements IFichier {
    private String filename;
    
    public FichierDirect(String filename) {
        this.filename = filename;
    }
    
    public ArrayList<Etudiant> lireEtudiants() throws IOException {
        File f = new File(filename);
        if (!f.exists()) return new ArrayList<Etudiant>();
        
        RandomAccessFile file = new RandomAccessFile(f, "r");
        
        ArrayList<Etudiant> liste = new ArrayList<Etudiant>();
        while (file.getFilePointer() < file.length()) {
            Etudiant e = new Etudiant();
            e.setNum(file.readInt());
            e.setNom(file.readUTF());
            e.setPrenom(file.readUTF());
            e.setGenre(file.readChar());
            e.setNote(file.readFloat());
            liste.add(e);
        }
        
        file.close();
        
        return liste;
    }
    
    public void ecrireEtudiants(ArrayList<Etudiant> liste) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filename, "rw");
        
        for (int i = 0; i < liste.size(); ++i) {
            Etudiant e = liste.get(i);
            
            file.writeInt(e.getNum());
            file.writeUTF(e.getNom());
            file.writeUTF(e.getPrenom());
            file.writeChar(e.getGenre());
            file.writeFloat(e.getNote());
        }
        
        file.close();
    }
}
