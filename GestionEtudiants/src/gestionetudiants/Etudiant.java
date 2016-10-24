package gestionetudiants;

import java.io.Serializable;

/**
 *
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class Etudiant implements Serializable {
    private int num;
    private String nom;
    private String prenom;
    private char genre;
    private float note;
    
    public int getNum() {
        return num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public char getGenre() {
        return genre;
    }
    
    public void setGenre(char genre) {
        this.genre = genre;
    }
    
    public float getNote() {
        return note;
    }
    
    public void setNote(float note) {
        this.note = note;
    }
}
