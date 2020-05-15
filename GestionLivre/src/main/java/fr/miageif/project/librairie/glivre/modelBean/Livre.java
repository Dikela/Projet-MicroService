package fr.miageif.project.librairie.glivre.modelBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

@Entity
public class Livre {

    @Id
    private String isbn;

    @Column(name = "auteur")
    private String auteur;

    @Column(name = "titre")
    private String titre;

    @Column(name = "editeur")
    private String editeur;

    @Column(name = "edition")
    private int edition;

    public Livre(){
    }
    public Livre(String isbn, String auteur, String titre, String editeur, int edition) {
        this.isbn = isbn;
        this.auteur = auteur;
        this.titre = titre;
        this.editeur = editeur;
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Override
    public String toString(){
        return "Le livre " + this.isbn + " correspond à " + this.titre + "d"+ '\''
                + this.auteur + " éditer par "+ this.editeur + "en "+this.edition;
    }
}
