package gestionetudiants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author Dermoumi S. (sdermoumi@gmail.com)
 */
public class GestionEtudiants {
    private static IFichier fichier;

    public static void listerEtudiants() {
        try {
            ArrayList<Etudiant> etudiants = fichier.lireEtudiants();
            
            for (int i = 0; i < etudiants.size(); ++i) {
                Etudiant e = etudiants.get(i);
                
                System.out.println(e.getNum() + "\t" + e.getNote() + "\t"
                    + e.getNom() + "\t" + e.getPrenom() + "\t" + e.getGenre());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void ajouterEtudiant(Scanner stdin) {
        Etudiant e = new Etudiant();
        
        System.out.print("Num : ");     e.setNum(stdin.nextInt());
        System.out.print("Nom : ");     e.setNom(stdin.next());
        System.out.print("Prenom : ");  e.setPrenom(stdin.next());
        System.out.print("Genre : ");   e.setGenre(stdin.next().charAt(0));
        System.out.print("Note : ");    e.setNote(stdin.nextFloat());
        
        try {
            ArrayList<Etudiant> liste = fichier.lireEtudiants();
            
            // On vérifie si l'utilisateur existe déjà
            boolean exists = false;
            for (int i = 0; i < liste.size(); ++i) {
                if (liste.get(i).getNum() == e.getNum()) {
                    exists = true;
                    break;
                }
            }
            
            if (exists) {
                System.out.println("Un étudiant avec le méme numéro existe déjà.");
            }
            else {
                liste.add(e);
                fichier.ecrireEtudiants(liste);
                System.out.println("L'étudiant a été ajouté avec succès.");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void modifierEtudiant(Scanner stdin) {
        System.out.print("Entrez le numéro de l'étudiant à modifier : ");
        int num = stdin.nextInt();
        
        try {
            ArrayList<Etudiant> liste = fichier.lireEtudiants();
            
            Etudiant e = null;
            for (int i = 0; i < liste.size(); ++i) {
                if (liste.get(i).getNum() == num) {
                    e = liste.get(i);
                    break;
                }
            }
            
            if (e == null) {
                System.out.println("Aucun étudiant avec ce numéro.");
            }
            else {
                stdin.nextLine(); // Pour consommer le retour à la ligne déviant
                
                System.out.print("Nom (" + e.getNom() + ") : ");
                String nom = stdin.nextLine();
                if (!nom.isEmpty()) e.setNom(nom);
                
                System.out.print("Prénom (" + e.getPrenom() + ") : ");
                String prenom = stdin.nextLine();
                if (!prenom.isEmpty()) e.setPrenom(prenom);
                
                System.out.print("Genre (" + e.getGenre() + ") : ");
                String genre = stdin.nextLine();
                if (!genre.isEmpty()) e.setGenre(genre.charAt(0));
                
                System.out.print("Note (" + e.getNote() + ") : ");
                String note = stdin.nextLine();
                if (!note.isEmpty()) e.setNote(Float.parseFloat(note));
                
                fichier.ecrireEtudiants(liste);
                
                System.out.println("L'étudiant a été modifié avec succès.");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void supprimerEtudiant(Scanner stdin) {
        System.out.print("Entrez le numéro de l'étudiant à supprimer : ");
        int num = stdin.nextInt();
        
        try {
            ArrayList<Etudiant> liste = fichier.lireEtudiants();
            liste.removeIf(new Predicate<Etudiant>() {
                public boolean test(Etudiant e) {
                    return e.getNum() == num;
                }
            });
            
            fichier.ecrireEtudiants(liste);
            System.out.println("L'étudiant a été supprimé avec succès.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void listerValides(Scanner stdin) {
        System.out.print("Entrez le seuil de validation (12.00) : ");
        stdin.nextLine();  // Pour consommer le retour à la ligne déviant
        String seuilStr = stdin.nextLine();
        float seuil = seuilStr.isEmpty() ? 12.f : Float.parseFloat(seuilStr);
        
        try {
            ArrayList<Etudiant> etudiants = fichier.lireEtudiants();
            
            for (int i = 0; i < etudiants.size(); ++i) {
                Etudiant e = etudiants.get(i);
                
                if (e.getNote() >= seuil) {
                    System.out.println(e.getNum() + "\t" + e.getNote() + "\t"
                        + e.getNom() + "\t" + e.getPrenom() + "\t" + e.getGenre());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void trierListe(Scanner stdin) {
        System.out.println("Trier par ...");
        System.out.println("[1] Nom.");
        System.out.println("[2] Prénom.");
        System.out.println("[3] Genre.");
        System.out.println("[4] Note.");
        System.out.println("[0] Annuler.");
        
        try {
            ArrayList<Etudiant> liste = fichier.lireEtudiants();
            int choix = stdin.nextInt();
            switch (choix) {
                case 1:
                    liste.sort(new Comparator<Etudiant>() {
                        public int compare(Etudiant a, Etudiant b) {
                            return a.getNom().compareTo(b.getNom());
                        }
                    });
                    break;
                case 2:
                    liste.sort(new Comparator<Etudiant>() {
                        public int compare(Etudiant a, Etudiant b) {
                            return a.getPrenom().compareTo(b.getPrenom());
                        }
                    });
                    break;
                case 3:
                    liste.sort(new Comparator<Etudiant>() {
                        public int compare(Etudiant a, Etudiant b) {
                            return (int)b.getGenre() - (int)a.getGenre();
                        }
                    });
                    break;
                case 4:
                    liste.sort(new Comparator<Etudiant>() {
                        public int compare(Etudiant a, Etudiant b) {
                            return (int)(b.getNote() * 100.f) - (int)(a.getNote() * 100.f);
                        }
                    });
                    break;
            }
            
            fichier.ecrireEtudiants(liste);
            System.out.println("La liste des étudiants à été triée avec succès.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static int afficherMenu(Scanner stdin) {
        System.out.println("\n--------------------");
        System.out.println("Que voulez vous faire?");
        System.out.println("[1] Afficher tous les étudiants.");
        System.out.println("[2] Ajouter un nouvel étudiant.");
        System.out.println("[3] Modifier un étudiant.");
        System.out.println("[4] Supprimmr un étudiant.");
        System.out.println("[5] Lister les étudiants ayant validé.");
        System.out.println("[6] Trier la liste des étudiants.");
        System.out.println("[0] Quitter.");
        System.out.print("\nEntrez le numéro de l'operation: ");
        
        return stdin.nextInt();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        fichier = new FichierStream("etudiants.str");
        
        int choix;
        while ((choix = afficherMenu(stdin)) != 0) {
            switch (choix) {
                case 1:  listerEtudiants();         break;
                case 2:  ajouterEtudiant(stdin);    break;
                case 3:  modifierEtudiant(stdin);   break;
                case 4:  supprimerEtudiant(stdin);  break;
                case 5:  listerValides(stdin);      break;
                case 6:  trierListe(stdin);         break;
            }
        }
    }
    
}
