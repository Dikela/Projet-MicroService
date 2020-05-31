package fr.miageif.project.librairie.microserviceemprunt.modelBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmpruntLivre {
    @Id
    public Integer id;

    @Column(name = "ISBN")
    public String ISBN;//  peut être un livre

    @Column(name = "lecteur")
    public String lecteur;

    @Column(name = "datepret")
    public String datepret;

    @Column(name = "dateretour")
    public String dateretour;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String isbnLivre) {
        this.ISBN = isbnLivre;
    }

    public String getLecteur() {
        return lecteur;
    }

    public void setLecteur(String idlecteur) {
        this.lecteur = idlecteur;
    }

    public String getDatepret() {
        return datepret;
    }

    public void setDatepret(String date_Pret) {
        this.datepret = date_Pret;
    }

    public String getDateretour() {
        return dateretour;
    }

    public void setDateretour(String date_Retour) {
        this.dateretour = date_Retour;
    }

    @Override
    public String toString(){
        return "A la date du " + this.datepret + " un exemplaire du livre " +
                "identifiés par l'ISBN " + this.ISBN + " , a été emprunté par" +
                "le lecteur identifié " + this.lecteur + " et a été rendu le " + this.dateretour;
    }
}
