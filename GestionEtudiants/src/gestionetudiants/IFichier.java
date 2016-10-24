package gestionetudiants;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public interface IFichier {
    public ArrayList<Etudiant> lireEtudiants() throws IOException;
    public void ecrireEtudiants(ArrayList<Etudiant> liste) throws IOException;
}
